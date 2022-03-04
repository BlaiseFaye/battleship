package model;

import java.util.ArrayList;
import java.util.Random;

import configuration.CaseShotResponse;
import configuration.ConfigGlobal;
import helper.Coordinate;
import helper.MyRandom;
import model.Ship.OrientationType;

public class GridModel {
	public static final int MAX_CASE = ConfigGlobal.getMaxCase();
	
	private ArrayList<Ship> p1Ships;
	private ArrayList<Ship> p2Ships;
	private ArrayList<Coordinate> p1CasesShoted;
	private ArrayList<Coordinate> p2CasesShoted;
	
	public GridModel()
	{
		this.p1Ships = new ArrayList<Ship>();
		this.p2Ships = new ArrayList<Ship>();
		this.p1CasesShoted = new ArrayList<Coordinate>();
		this.p2CasesShoted = new ArrayList<Coordinate>();
	}
	
	/**
	 * 
	 * @param coordClick
	 * @param isP1GridClicked
	 * @return CaseShotResponse.ALREADY_CLICKED if already clicked || CaseShotResponse.NOT_CLICKED_YET if not clicked yet
	 *
	 */
	public CaseShotResponse isCaseAlreadyClicked(Coordinate coordClick, boolean isP1GridClicked)
	{
		ArrayList<Coordinate> casesShoted = isP1GridClicked ? this.p1CasesShoted : this.p2CasesShoted;
		
		for(Coordinate coordAlreadyShoted : casesShoted)
		{
			if(coordAlreadyShoted.getX() == coordClick.getX() && coordAlreadyShoted.getY() == coordClick.getY())
			{
				return CaseShotResponse.ALREADY_CLICKED;
			}
		}
		
		return CaseShotResponse.NOT_CLICKED_YET;
	}
	
	/**
	 * 
	 * @return SUNK if the case clicked is the last piece of ship
	 *		   HIIT if the case clicked is a piece from a ship
	 *		   MISS if the case clicked have no piece of ship
	 */
	public CaseShotResponse checkFieldStatus(Coordinate coord, boolean isP1Grid)
	{
		ArrayList<Ship> ships = this.getShipsByPlayerGrid(isP1Grid);
		
		for(Ship ship : ships)
		{
			for(Coordinate coordPiece : ship.getPiecesLocation())
			{
				if(coordPiece.getX() == coord.getX() && coordPiece.getY() == coord.getY())
				{	
					ship.removePiece(coordPiece);
					
					if(ship.isSunk())
					{
						return CaseShotResponse.SUNK;
					}
					
					return CaseShotResponse.HIIT;
				}
			}
		}
		
		return CaseShotResponse.MISS;
	}
	
	public ArrayList<Coordinate> getCoordOfPiecesShips(boolean forP1Ships)
	{
		ArrayList<Ship> ships = null;
		
		if(forP1Ships)
		{
			ships = this.getShipsByPlayerGrid(true);
		}
		else
		{
			ships = this.getShipsByPlayerGrid(false);
		}
		
		ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
		
		for(Ship ship : ships)
		{
			for(Coordinate coord : ship.getPiecesLocation())
			{
				coords.add(coord);
			}
		}
		
		return coords;
	}
	
	/**
	 * Create ship with random coordinate and place him in the grid randomly
	 */
	public void createRandomPlacement()
	{
		this.reset();
		
		for(int i = 0; i < 2; i++)
		{
			boolean isPlayerOne = false;
			
			if(i == 0)
			{
				isPlayerOne = true;
			}
			
			ArrayList<Ship> ships = this.getShipsByPlayerGrid(isPlayerOne);
			
			for(int j = 0; j < ConfigGlobal.MAX_SHIP; j++)
			{
				boolean isOutOfBoard = true;
				
				while(isOutOfBoard)
				{
					OrientationType orientation = MyRandom.getRandomNumber(2) == 0 ? OrientationType.HORIZONTAL : OrientationType.VERTICAL;
					Coordinate coordPieceShip = MyRandom.getRandomCoordinate(GridModel.MAX_CASE, GridModel.MAX_CASE);
					
					int nbPieces = Ship.NB_CASE_SHIPS[j];
					
					Ship ship = new Ship(Ship.shipTypes[j], nbPieces, orientation, coordPieceShip);		

					try
					{
						ship.setPiecesLocation(coordPieceShip, orientation, nbPieces);
						
						if(this.canPlaceShip(ship, isPlayerOne))
						{
							ships.add(ship);
							isOutOfBoard = false;	
						}
					}
					catch(OutOfBoardException e)
					{
		
					}	
				}
			}
		}
	}
	
	public ArrayList<Ship> getShipsByPlayerGrid(boolean isP1Grid)
	{
		return isP1Grid ? this.p1Ships : this.p2Ships;
	}
	
	public ArrayList<Coordinate> getCasesShotedByPlayerGrid(boolean isP1Grid)
	{
		return isP1Grid ? this.p1CasesShoted : this.p2CasesShoted;
	}
	
	public void addCasesShoted(Coordinate coord, boolean inP1Grid)
	{
		ArrayList<Coordinate> casesShoted = inP1Grid ? this.p1CasesShoted : this.p2CasesShoted;
		
		casesShoted.add(coord);
	}
	
	/**
	 * 
	 * @param ship
	 * @param isPlayerOne
	 * @return true if the ship don't touch existing ship false if he does
	 */
	public boolean canPlaceShip(Ship ship, boolean isPlayerOne)
	{
		ArrayList<Ship> ships = this.getShipsByPlayerGrid(isPlayerOne);
		
		for(Ship shipAlready : ships)
		{
			// maybe un petit bug ici, car parfois les bateaux se touchent je penses
			for(Coordinate coordAlready : shipAlready.getPiecesLocation())
			{
				for(Coordinate coordWant : ship.getPiecesLocation())
				{
					if(coordWant.getX() == coordAlready.getX() + 1 && coordWant.getY() == coordAlready.getY())
					{
						return false;
					}
					if(coordWant.getX() == coordAlready.getX() && coordWant.getY() == coordAlready.getY() + 1)
					{
						return false;
					}
					if(coordWant.getX() == coordAlready.getX() - 1 && coordWant.getY() == coordAlready.getY())
					{
						return false;
					}
					if(coordWant.getX() == coordAlready.getX() && coordWant.getY() == coordAlready.getY() - 1)
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param isPlayerOne
	 * @return true if all ship are sunk or false if not
	 * 
	 */
	public boolean isGameOver(boolean isPlayerOne)
	{	
		ArrayList<Ship> ships = this.getShipsByPlayerGrid(isPlayerOne);
		int nbShipSunk = 0;
		
		for(Ship ship : ships)
		{
			if(ship.getPiecesLocation().isEmpty())
			{	
				nbShipSunk++;
			}
		}
		
		if(nbShipSunk == ConfigGlobal.MAX_SHIP)
		{
			return true;
		}
		
		return false;
	}
	
	public void reset()
	{
		this.p1Ships = new ArrayList<Ship>();
		this.p2Ships = new ArrayList<Ship>();
		this.p1CasesShoted = new ArrayList<Coordinate>();
		this.p2CasesShoted = new ArrayList<Coordinate>();
	}
}
