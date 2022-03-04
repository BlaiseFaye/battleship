package controller;

import model.GridModel;
import model.Player;
import view.GridView;
import view.MenuDifficultyView;
import view.MenuHowToPlay;
import view.MenuPrincipalView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import configuration.ActionMenuType;
import configuration.CaseShotResponse;
import configuration.ConfigGlobal;
import configuration.ConstantTexts;
import configuration.DifficultyType;
import configuration.GameState;
import helper.Coordinate;
import helper.MyRandom;

public class GameController {
	private GridView gridView;
	private GridModel gridModel;
	private MenuPrincipalView menuPrincipalView;
	private MenuDifficultyView menuDifficultyView;

	private boolean gameIsRunning;
	private boolean isPlayerOneTurn; // Player one respresent the first Grid (left grid)
	private DifficultyType difficulty;

	private boolean soundEffect, soundGame;
	private Clip clipGameSound;

	private MenuHowToPlay menuHowToPlay;

	private Player player;
	
	public GameController() {
		this.gameIsRunning = false;
		this.isPlayerOneTurn = true;
		this.soundGame = true;
		this.soundEffect = true;

		this.clipGameSound = null;

		this.playSound(ConfigGlobal.SOUND_MAIN_PATH);
	}

	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}

	public void setGridModel(GridModel gridModel) {
		this.gridModel = gridModel;
	}

	public void setMenuPrincipalView(MenuPrincipalView menuView) {
		this.menuPrincipalView = menuView;
	}

	public void setMenuDifficultyView(MenuDifficultyView menuDifficulty) {
		this.menuDifficultyView = menuDifficulty;
	}

	public void setMenuHowToPlay(MenuHowToPlay menuHowToPlay) {
		this.menuHowToPlay = menuHowToPlay;
	}

	/**
	 * if you want firstGridVisible make the first parameter true but if you want
	 * all visible make the second parameter true if you want all invisible make all
	 * false
	 * 
	 * @param firstGridVisible
	 * @param allVisible
	 */
	public void setShipsVisible(boolean firstShips, boolean secondShips, boolean bothShips) {
		ArrayList<Coordinate> p1Coords = this.gridModel.getCoordOfPiecesShips(true);
		ArrayList<Coordinate> p2Coords = this.gridModel.getCoordOfPiecesShips(false);

		if (!firstShips && !bothShips && !secondShips) {
			this.gridView.drawPanelGame(null, null);
		} else if (bothShips) {
			this.gridView.drawPanelGame(p1Coords, p2Coords);
		} else if (firstShips) {
			this.gridView.drawPanelGame(p1Coords, null);
		} else if(secondShips){
			this.gridView.drawPanelGame(null, p2Coords);
		}
	}

	public void caseClicked(Coordinate coordCaseClicked, boolean isP1GridClicked) {
		if (this.gameIsRunning) {
			if (!isP1GridClicked && this.isPlayerOneTurn) {
				this.changeCaseByClick(coordCaseClicked, isP1GridClicked);

				this.changeTurn();
				/*
				 * try {
				 * 
				 * Thread.sleep(3000); } catch (InterruptedException ex) {
				 * Thread.currentThread().interrupt(); }
				 */

				this.IAPlayTurn(this.difficulty);
			}
		}
	}

	public void actionButtonGrid() {
		switch (this.gridView.getActionGameType()) {
		case START:
			this.startGame();
			this.clipGameSound.stop();
			this.playSound(ConfigGlobal.SOUND_GAME_PATH);
			break;
		case RESET:
			this.reset();
			break;
		case RANDOMIZE_PLACEMENT:
			this.update();
			break;
		case SOUND_EFFECT_OFF_ON:
			this.soundEffect = !this.soundEffect;

			if (this.soundEffect) {
				this.gridView.getSoundEffect().setText(ConstantTexts.GeneralTexts.get("soundEffectOn"));
				this.gridView.getSoundEffect().setIcon(new ImageIcon(ConfigGlobal.SOUND_ON_ICON));
			} else {
				this.gridView.getSoundEffect().setText(ConstantTexts.GeneralTexts.get("soundEffectOff"));
				this.gridView.getSoundEffect().setIcon(new ImageIcon(ConfigGlobal.SOUND_OFF_ICON));
			}
			break;
		case SOUND_GAME_OFF_ON:
			this.soundGame = !this.soundGame;

			if (this.soundGame) {
				this.clipGameSound.start();
				this.gridView.getSoundMain().setText(ConstantTexts.GeneralTexts.get("soundGameOn"));
				this.gridView.getSoundMain().setIcon(new ImageIcon(ConfigGlobal.SOUND_ON_ICON));
			} else {
				this.clipGameSound.stop();
				this.gridView.getSoundMain().setText(ConstantTexts.GeneralTexts.get("soundGameOff"));
				this.gridView.getSoundMain().setIcon(new ImageIcon(ConfigGlobal.SOUND_OFF_ICON));
			}
			break;
		case QUIT:
			this.gridView.dispose();
			this.menuPrincipalView.setVisible(true);
			this.clipGameSound.stop();
			this.playSound(ConfigGlobal.SOUND_MAIN_PATH);
			break;
		}
	}

	public void actionMenuPrincipal() {
		this.menuPrincipalView.dispose();

		switch (this.menuPrincipalView.getMenuType()) {
		case START_LOCAL:
			this.menuDifficultyView.setVisible(true);
			break;
		case START_MULTIPLAYER:
			this.update();
			break;
		case HOW_TO_PLAY:
			this.menuHowToPlay.setVisible(true);
			break;
		case QUIT:
			System.exit(0);
			break;
		}
	}

	public void actionMenuDifficulty() {
		switch (this.menuDifficultyView.getDifficultyType()) {
		case EASY:
			this.difficulty = DifficultyType.EASY;
			break;
		case CHALLENGER:
			this.difficulty = DifficultyType.CHALLENGER;
			break;
		}

		this.update();
	}

	public void actionMenuHowToPlay() {
		this.menuHowToPlay.setVisible(false);

		switch (this.menuHowToPlay.getMenuType()) {
		case RETURN_MENU:
			this.menuPrincipalView.setVisible(true);
			break;
		}
	}

	public void IAPlayTurn(DifficultyType difficulty) {
		boolean isP1GridClicked = true;
		Coordinate randCoord = null;

		// Create random coordinate until find unclicked coordinate
		do {
			randCoord = MyRandom.getRandomCoordinate(ConfigGlobal.ROW, ConfigGlobal.COL);

			/*
			 * if (this.difficulty == DifficultyType.CHALLENGER) { ArrayList<Coordinate>
			 * coords = this.gridModel.getCasesShotedByPlayerGrid(isP1GridClicked);
			 * 
			 * for (Coordinate coord : coords) { if (coord.getX() + 1 < ConfigGlobal.ROW) {
			 * randCoord.setX(coord.getX() + 1); randCoord.setY(coord.getY()); } if
			 * (coord.getX() - 1 < ConfigGlobal.ROW) { randCoord.setX(coord.getX() - 1);
			 * randCoord.setY(coord.getY()); }
			 * 
			 * if (coord.getY() + 1 < ConfigGlobal.COL) { randCoord.setX(coord.getX() + 1);
			 * randCoord.setY(coord.getY()); } if (coord.getY() - 1 < ConfigGlobal.COL) {
			 * randCoord.setX(coord.getX() - 1); randCoord.setY(coord.getY()); } } }
			 */

		} while (this.gridModel.isCaseAlreadyClicked(randCoord, isP1GridClicked) == CaseShotResponse.ALREADY_CLICKED);

		this.changeCaseByClick(randCoord, true);
		this.gridModel.addCasesShoted(randCoord, isP1GridClicked);

		this.changeTurn();
	}

	public void changeCaseByClick(Coordinate coordCaseClicked, boolean isP1Grid) {
		CaseShotResponse caseResponse = this.gridModel.checkFieldStatus(coordCaseClicked, isP1Grid);

		if (caseResponse == CaseShotResponse.SUNK) {
			if (this.gridModel.isGameOver(isP1Grid)) {
				this.gridView.drawPanelGameFinish(GameState.GAME_OVER, isP1Grid);
				if(this.soundEffect)
				{
					if(isP1Grid)
					{
						this.playSound(ConfigGlobal.SOUND_GAME_LOSE);	
					}
					else
					{
						this.playSound(ConfigGlobal.SOUND_GAME_WIN);
					}
				}
			}
		}
		if (this.soundEffect) {
			if (caseResponse == CaseShotResponse.MISS && !isP1Grid) {
				this.playSound(ConfigGlobal.SOUND_EFFECT_PATH);
			}
		}

		this.gridView.setCaseShotResponse(caseResponse);
		this.gridView.changeViewByStatus(coordCaseClicked, isP1Grid);
	}

	public void startGame() {
		this.gameIsRunning = true;
		this.setShipsVisible(false, false, false); // First parameter make visible first grid ships, the second make visible
											// both
		this.gridView.getStartGame().setEnabled(false);
		this.gridView.getRandomizePlacement().setEnabled(false);
		this.gridView.getResetGame().setEnabled(true);
		this.gridView.getInfosShot().setText(ConstantTexts.GeneralTexts.get("yourTurn"));
	}

	public void update() {		
		if (this.menuPrincipalView.getMenuType() == ActionMenuType.START_MULTIPLAYER) {
			this.player = new Player();

			//this.gridView.getInfosShot().setText("En attente d'un joueur...");
			if (this.player.getId() == 1) {
				this.setShipsVisible(true, false, false);
			}

			if (this.player.getId() == 2) {
				this.setShipsVisible(false, true, false);
			}
		} else {
			this.gridModel.createRandomPlacement();
			this.setShipsVisible(true, false, false);
		}
		 
		this.gridView.setVisible(true);
	}

	public void reset() {
		this.gameIsRunning = false;
		this.isPlayerOneTurn = true;

		this.update();

		this.gridView.getResetGame().setEnabled(false);
		this.gridView.getStartGame().setEnabled(true);
		this.gridView.getRandomizePlacement().setEnabled(true);
		this.gridView.getInfosShot().setText(ConstantTexts.GeneralTexts.get("putForPlay"));
	}

	public void playSound(String soundFile) {
		File f = new File("./" + soundFile);
		AudioInputStream audioIn = null;
		Clip clip = null;

		try {
			audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		if (soundFile.equals(ConfigGlobal.SOUND_MAIN_PATH) || soundFile.equals(ConfigGlobal.SOUND_GAME_PATH)) {
			this.clipGameSound = clip;
		}

		clip.start();
	}

	public void changeTurn() {
		this.isPlayerOneTurn = !this.isPlayerOneTurn;

		if (this.isPlayerOneTurn) {
			this.gridView.getInfosShot().setText(ConstantTexts.GeneralTexts.get("yourTurn"));
		} else {
			this.gridView.getInfosShot().setText(ConstantTexts.GeneralTexts.get("notYourTurn"));
		}
	}
}
