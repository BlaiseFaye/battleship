package configuration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import controller.GameController;
import model.GridModel;
import view.ConstantColors;

public class ConfigGlobal {
	public static final int ROW = 10, COL = 10;
	public static final int WIDTH_WINDOW = 1300, HEIGHT_WINDOW = 750;
	public static final int X_LOCATION_WINDOW = 100, Y_LOCATION_WINDOW = 50;
	public static final int MAX_SHIP = 4;
	
	public static final Rectangle PANEL_BUTTONS_GAME = new Rectangle(0, 0, 250, 250);
	public static final Rectangle PANEL_GRID_GAME = new Rectangle(250, 0, ConfigGlobal.WIDTH_WINDOW, ConfigGlobal.HEIGHT_WINDOW - 300);
	public static final Rectangle PANEL_INFOS_GAME = new Rectangle(0, 300, ConfigGlobal.WIDTH_WINDOW, ConfigGlobal.HEIGHT_WINDOW - 300);
	
	public final static Color 
			btnBackgroundColor = ConstantColors.C_BLUE_WATER,
			btnForegroundColor = ConstantColors.C_WHITE, 
			labelColor = ConstantColors.C_BLACK;
	
	public final static Font FONT_PRINCIPAL = new Font("Monospaced", Font.PLAIN, 22);
	
	// Chemin vers les sound du jeu
	public final static String SOUND_MAIN_PATH = "src/public/song/musicPrincipal.wav",
	         			       SOUND_EFFECT_PATH = "src/public/song/effect/splashWater.wav",
	         			       SOUND_GAME_PATH = "src/public/song/musicInGame.wav",
	         			       SOUND_GAME_WIN = "src/public/song/effect/winGame.wav",
	         			       SOUND_GAME_LOSE = "src/public/song/effect/loseGame.wav",
	         			       
	         			       SOUND_EFFECT_BTN_HOV_IN = "src/public/song/effect/buttonHoverIn.wav",
	         			       SOUND_EFFECT_BTN_HOV_OUT = "src/public/song/effect/buttonHoverOut.wav";
	
	// Chemin ver les icones du jeu
	public final static String MULTIPLAYER_ICON = "src/public/img/icons/mainMenu/multiplayer.png",
							   LOCAL_ICON = "src/public/img/icons/mainMenu/local.png",
							   HOW_TO_PLAY_ICON = "src/public/img/icons/mainMenu/howToPlay.png",
							   QUIT_ICON = "src/public/img/icons/mainMenu/quit.png",
							   
							   SOUND_ON_ICON = "src/public/img/icons/gridView/soundOn.png",
						       SOUND_OFF_ICON = "src/public/img/icons/gridView/soundOff.png";
	
	public final static Dimension DEFAULT_BTN_DIMENSION = new Dimension(100, 80);
	
	// MULTIPLAYER RESEAU
	public final static int MAX_PLAYER_MULTIPLAYER = 2;
	
	public final static int NO_PORT = 1234;
	
	@SuppressWarnings("unused")
	public static int getMaxCase()
	{
		if(ConfigGlobal.ROW >= ConfigGlobal.COL)
		{
			return ROW;
		}
		
		return COL;
	}
}
