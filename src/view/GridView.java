package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import configuration.ActionGameType;
import configuration.CaseShotResponse;
import configuration.ConfigGlobal;
import configuration.ConstantTexts;
import configuration.GameState;
import helper.Coordinate;

public class GridView extends GuiView {
	private JPanel panelGame, p1Panel, p2Panel;
	private JButton[][] p1Grid, p2Grid;
	
	private JPanel panelButtons;
	private JButton randomizePlacement, startGame, resetGame, soundEffect, soundMain, quit;

	private JPanel panelInfos;
	private JLabel infosShot;
	
	private JPanel panelGameOver;
	private JLabel backgroundState;
	
	private CaseShotResponse caseShotResponse;
	private ActionGameType actionGameType;
	
	private final ImageIcon iconWater;
	private final ImageIcon iconShipGreen, iconShipRed;
	
	private final static Font btnFont = ConfigGlobal.FONT_PRINCIPAL.deriveFont(16f);

	public GridView()
	{
		this.setSize(ConfigGlobal.WIDTH_WINDOW, ConfigGlobal.HEIGHT_WINDOW);
		this.setLocation(ConfigGlobal.X_LOCATION_WINDOW, ConfigGlobal.Y_LOCATION_WINDOW);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		iconWater = new ImageIcon("src/public/img/water.jpg");
		iconShipGreen =  new ImageIcon("src/public/img/shipmod.jpg");
		iconShipRed =  new ImageIcon("src/public/img/shipmod1.jpg");
		
		this.render();
	}
	
	public void render()
	{
		this.drawPanelGame(null, null);
		this.drawPanelButtons();
		this.drawPanelInfos();
	}

	public void display(boolean visible)
	{
		this.setVisible(visible);
	} 
	
	public void drawPanelButtons()
	{
		this.panelButtons = new JPanel();
		this.panelButtons.setLayout(new GridLayout(0, 1));
			this.startGame = AddFrameComponent.addButton(this, this.panelButtons, "Débuter la partie", ConstantColors.C_BLUE_WATER, ConstantColors.C_WHITE, GridView.btnFont);
			this.resetGame = AddFrameComponent.addButton(this, this.panelButtons, "Recommencer une partie", ConstantColors.C_BLUE_WATER, ConstantColors.C_WHITE, GridView.btnFont);
			this.resetGame.setEnabled(false);
			this.randomizePlacement = AddFrameComponent.addButton(this, this.panelButtons, "Placement aléatoire", ConstantColors.C_BLUE_WATER, ConstantColors.C_WHITE, GridView.btnFont);
			this.soundEffect = AddFrameComponent.addButton(this, this.panelButtons, ConstantTexts.GeneralTexts.get("soundEffectOn"), ConstantColors.C_BLUE_WATER, ConstantColors.C_WHITE, GridView.btnFont, ConfigGlobal.SOUND_ON_ICON);
			this.soundMain = AddFrameComponent.addButton(this, this.panelButtons, ConstantTexts.GeneralTexts.get("soundGameOn"), ConstantColors.C_BLUE_WATER, ConstantColors.C_WHITE, GridView.btnFont, ConfigGlobal.SOUND_ON_ICON);
			this.quit = AddFrameComponent.addButton(this, this.panelButtons, "Quitter la partie", ConstantColors.C_BLUE_WATER, ConstantColors.C_WHITE, GridView.btnFont);
		this.add(this.panelButtons, BorderLayout.LINE_START);
	}
	
	public void drawPanelInfos()
	{
		this.panelInfos = new JPanel();
		this.panelInfos.setLayout(new GridLayout(0, 1));
		this.panelInfos.setPreferredSize(new Dimension(ConfigGlobal.WIDTH_WINDOW, 150));
			this.infosShot = AddFrameComponent.addLabel(this, ConstantTexts.GeneralTexts.get("putForPlay"), ConstantColors.C_BLACK, JLabel.CENTER, ConfigGlobal.FONT_PRINCIPAL);
			this.panelInfos.add(this.infosShot);
		this.add(this.panelInfos, BorderLayout.PAGE_END);
	}
	
	public void drawPanelGameFinish(GameState gameState, boolean isP1Grid)
	{
		this.panelGame.setVisible(false);
		this.panelGameOver = new JPanel();
		this.panelGameOver.setPreferredSize(new Dimension(ConfigGlobal.WIDTH_WINDOW + 500, ConfigGlobal.HEIGHT_WINDOW ));
			try {
				if(isP1Grid)
				{
					this.backgroundState = new JLabel(new ImageIcon(ImageIO.read(new File("src/public/img/gameOver.jpg"))));	
				}
				else
				{
					this.backgroundState = new JLabel(new ImageIcon(ImageIO.read(new File("src/public/img/win.jpg"))));
				}
				this.backgroundState.setPreferredSize(new Dimension(ConfigGlobal.WIDTH_WINDOW, ConfigGlobal.HEIGHT_WINDOW + 300));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.panelGameOver.add(this.backgroundState);
		this.add(this.panelGameOver, BorderLayout.CENTER);
	}
	
	public void changeViewByStatus(Coordinate coord, boolean isPlayerOne)
	{
		// Getting the specified button coordonate
		JButton[][] grid = getGridByPlayer(isPlayerOne);
		JButton button = grid[coord.getX()][coord.getY()];

		ImageIcon imageHiit = isPlayerOne ? this.iconShipGreen : this.iconShipRed;
		ImageIcon imageMiss = this.iconWater;
		ImageIcon image = null;
		
		switch (this.caseShotResponse) {
		case HIIT:
		case SUNK:
			imageMiss = null;
			image = imageHiit;
			break;
		case MISS:
			imageHiit = null;
			image = imageMiss;
			break;
		}
		
		button.setIcon(image);
		button.setEnabled(false);
		
		if(imageHiit != null && imageMiss == null)
		{
			button.setDisabledIcon(image);
		}
	}
	
	public JButton[][] getGridByPlayer(boolean isPlayerOne)
	{
		return isPlayerOne ? this.p1Grid : this.p2Grid;
	}
	
	/**
	 * If u wanna set visible the ship in specific grid, pass the arraylist of coordinates in the parameter 
	 * or null if you don't wanna set the ship visible
	 * @param p1Coords
	 * @param p2Coords
	 */	
	public void drawPanelGame(ArrayList<Coordinate> p1Coords, ArrayList<Coordinate> p2Coords)
	{		
		if(this.panelGameOver != null)
		{
			this.panelGameOver.setVisible(false);
		}
		
		if(this.panelGame != null)
        {
            this.panelGame.setVisible(false);
        }
		
		this.p1Grid = new CaseGrid[ConfigGlobal.ROW][ConfigGlobal.COL];
		this.p2Grid = new CaseGrid[ConfigGlobal.ROW][ConfigGlobal.COL];
		
		this.panelGame = new JPanel();
		this.panelGame.setLayout(new GridLayout(0, 2));
		
		this.p1Panel = new JPanel();
		this.p1Panel.setLayout(new GridLayout(ConfigGlobal.ROW, ConfigGlobal.COL));
		
			for(int x = 0; x < ConfigGlobal.ROW; x++)
			{
				for(int y = 0; y < ConfigGlobal.COL; y++)
				{
					this.p1Grid[x][y] = new CaseGrid(new Coordinate(x, y));
					
                    this.p1Grid[x][y].setIcon(this.iconWater);
					//this.p1Grid[x][y].setBackground(ConstantColors.C_ABSTRACT);
					if(p1Coords != null)
					{
						for(Coordinate coord : p1Coords)
						{	
							if(coord.getX() == x && coord.getY() == y)
							{
								this.p1Grid[x][y].setIcon(this.iconShipGreen);
							}
						}	
					}
					this.p1Grid[x][y].addActionListener(this);
					this.p1Grid[x][y].setEnabled(true);
					this.p1Panel.add(this.p1Grid[x][y]);
				}
			}
		this.panelGame.add(this.p1Panel);
		
		this.p2Panel = new JPanel();
		this.p2Panel.setLayout(new GridLayout(ConfigGlobal.ROW, ConfigGlobal.COL));
			for(int x = 0; x < ConfigGlobal.ROW; x++)
			{
				for(int y = 0; y < ConfigGlobal.COL; y++)
				{
					this.p2Grid[x][y] = new CaseGrid(new Coordinate(x, y));
					//this.p2Grid[x][y].setBackground(ConstantColors.C_ABSTRACT);
					
                    this.p2Grid[x][y].setIcon(this.iconWater);
					if(p2Coords != null)
					{
						for(Coordinate coord : p2Coords)
						{							
							if(coord.getX() == x && coord.getY() == y)
							{
								this.p2Grid[x][y].setIcon(this.iconShipRed);
							}
						}	
					}
					this.p2Grid[x][y].addActionListener(this);
					this.p2Grid[x][y].setEnabled(true);
					this.p2Panel.add(this.p2Grid[x][y]);
				}
			}
		this.panelGame.add(this.p2Panel);
		
		this.add(panelGame, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonCaseSource = (JButton)e.getSource();
		
		if(buttonCaseSource instanceof CaseGrid)
		{
			boolean isP1GridClicked = false;
			
			int x = ((CaseGrid)buttonCaseSource).getCoord().getX();
			int y = ((CaseGrid)buttonCaseSource).getCoord().getY();
			
			Coordinate coord = new Coordinate(x, y);
			
			if(buttonCaseSource.getParent() == this.p1Panel)
			{
				isP1GridClicked = true;
			}
			
			this.controller.caseClicked(coord, isP1GridClicked);
		}
		else
		{		
			switch(buttonCaseSource.getText())
			{
			case "Débuter la partie":
				this.actionGameType = ActionGameType.START;
				break;
			case "Recommencer une partie":
				this.actionGameType = ActionGameType.RESET;
				break;
			case "Placement aléatoire":
				this.actionGameType = ActionGameType.RANDOMIZE_PLACEMENT;
				break;
			case "Sound Effect : On":
			case "Sound Effect : Off":
				this.actionGameType = ActionGameType.SOUND_EFFECT_OFF_ON;
				break;
			case "Sound Game : On":
			case "Sound Game : Off":
				this.actionGameType = ActionGameType.SOUND_GAME_OFF_ON;
				break;
			case "Quitter la partie":
				this.actionGameType = ActionGameType.QUIT;
				break;
			}
			
			this.controller.actionButtonGrid();
		}
	}

	public CaseShotResponse getCaseShotResponse() {
		return caseShotResponse;
	}

	public void setCaseShotResponse(CaseShotResponse csr) {
		this.caseShotResponse = csr;
	}

	public ActionGameType getActionGameType() {
		return actionGameType;
	}
	
	public JButton getRandomizePlacement() {
		return randomizePlacement;
	}

	public JButton getStartGame() {
		return startGame;
	}

	public JButton getResetGame() {
		return resetGame;
	}

	public JButton getQuit() {
		return quit;
	}

	public JButton getSoundEffect() {
		return soundEffect;
	}
	
	public JLabel getInfosShot()
	{
		return this.infosShot;
	}

	public JButton getSoundMain() {
		return soundMain;
	}

}
