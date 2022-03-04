package model;

import java.util.ArrayList;

import configuration.ShipType;
import helper.Coordinate;

public class Ship {	
	public static final int[] NB_CASE_SHIPS = new int[] {4, 3, 3, 2};
	public static final ShipType[] shipTypes = new ShipType[] {ShipType.AIRCRAFT_CARRIER, ShipType.DESTROYER, ShipType.SUBMARINE, ShipType.PATROL_BOAT};
	
	private ShipType shipType;
	private int nbPieces;
	private OrientationType orientation;
	private ArrayList<Coordinate> piecesLocation; 
	
	public Ship(ShipType shipType, int nbPieces, OrientationType orientation, Coordinate firstPiece)
	{
		this.shipType = shipType;
		this.nbPieces = nbPieces;
		this.orientation = orientation;
		this.piecesLocation = new ArrayList<Coordinate>();
	}
	
	public enum OrientationType
	{
		HORIZONTAL,
		VERTICAL
	}
	
	public ArrayList<Coordinate> getPiecesLocation()
	{
		return this.piecesLocation;
	}
	
	/**
	 * Set the pieces of ship with specified first piece coordinate
	 * He gonna place piece one by one depend to orientation and nbOfPiece
	 * Placement is to the right
	 * @param firstPiece
	 * @param orientation
	 * @param nbPieces
	 * @throws OutOfBoardException if the ship out of board
	 */
	public void setPiecesLocation(Coordinate firstPiece, OrientationType orientation, int nbPieces) throws OutOfBoardException
	{
		int x = firstPiece.getX();
		int y = firstPiece.getY();
		
		for(int i = 0; i < nbPieces; i++)
		{	
			this.piecesLocation.add(new Coordinate(x, y));
			
			if(orientation == OrientationType.HORIZONTAL)
			{	
				if(this.isOutOfBoard(x, nbPieces))
				{
					throw new OutOfBoardException();
				}
				else
				{
					x++;
				}
			}
			else
			{
				if(this.isOutOfBoard(y, nbPieces))
				{
					throw new OutOfBoardException();
				}
				else
				{
					y++;	
				}
			}
		}
	}
	
	public boolean isSunk()
	{
		if(this.piecesLocation.isEmpty())
		{
			return true;
		}
		
		return false;
	}
	
	public void removePiece(Coordinate coordPiece)
	{
		for(int i = 0; i < this.piecesLocation.size(); i++)
		{
			if(this.piecesLocation.get(i).getX() == coordPiece.getX() && this.piecesLocation.get(i).getY() == coordPiece.getY())
			{
				this.piecesLocation.remove(i);
			}
		}
	}
	
	public boolean isOutOfBoard(int coord, int nbPieces)
	{
		if(coord + nbPieces - 1 > GridModel.MAX_CASE - 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
