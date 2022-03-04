
import view.*;
import model.*;
import configuration.ConfigGlobal;
import controller.*;

public class BattleShip {
	
	public static void main(String[] args)
	{
		GameController controller = new GameController();
		
		// Create all views
		GridView gridView = new GridView();

		HomeResume homeResume = new HomeResume();

		MenuPrincipalView menuPrincipalView = new MenuPrincipalView();
		MenuDifficultyView menuDifficultyView = new MenuDifficultyView();
		MenuHowToPlay menuHowToPlay = new MenuHowToPlay();

		GridModel gridModel = new GridModel();

		// Set the controller for all view
		gridView.setController(controller);
		menuPrincipalView.setController(controller);
		menuDifficultyView.setController(controller);
		menuHowToPlay.setController(controller);

		// Set all views in the controller
		controller.setMenuPrincipalView(menuPrincipalView);
		controller.setGridView(gridView);
		controller.setMenuDifficultyView(menuDifficultyView);
		controller.setMenuHowToPlay(menuHowToPlay);

		controller.setGridModel(gridModel);
	}
}
