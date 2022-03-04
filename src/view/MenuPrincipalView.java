package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.ActionMenuType;
import configuration.ConfigGlobal;
import configuration.ConstantTexts;
import configuration.DifficultyType;
import controller.GameController;

public class MenuPrincipalView extends GuiView {
	private JPanel panelMain;
	private static JButton startLocal, startMultiplayer, howToPlay, quit;

	private ActionMenuType menuType;
	
	public MenuPrincipalView() {

		while (HomeResume.isLoading) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.panelMain = new JPanel();
		this.panelMain.setLayout(new GridLayout(0, 1));

		this.title = AddFrameComponent.addLabel(this, ConstantTexts.GeneralTexts.get("titleMainMenu"),
				ConfigGlobal.labelColor, JLabel.CENTER, ConfigGlobal.FONT_PRINCIPAL.deriveFont(40f).deriveFont(Font.BOLD));
		this.panelMain.add(this.title);

		MenuPrincipalView.startLocal = AddFrameComponent.addButton(this, ConstantTexts.GeneralTexts.get("startLocal"),
				ConfigGlobal.btnBackgroundColor, ConfigGlobal.btnForegroundColor, ConfigGlobal.FONT_PRINCIPAL, ConfigGlobal.DEFAULT_BTN_DIMENSION, ConfigGlobal.LOCAL_ICON);
		this.panelMain.add(startLocal);

		MenuPrincipalView.startMultiplayer = AddFrameComponent.addButton(this,
				ConstantTexts.GeneralTexts.get("startMultiplayer"), ConfigGlobal.btnBackgroundColor,
				ConfigGlobal.btnForegroundColor, ConfigGlobal.FONT_PRINCIPAL, ConfigGlobal.DEFAULT_BTN_DIMENSION, ConfigGlobal.MULTIPLAYER_ICON);
		this.panelMain.add(startMultiplayer);

		MenuPrincipalView.howToPlay = AddFrameComponent.addButton(this, ConstantTexts.GeneralTexts.get("howToPlay"),
				ConfigGlobal.btnBackgroundColor, ConfigGlobal.btnForegroundColor, ConfigGlobal.FONT_PRINCIPAL, ConfigGlobal.DEFAULT_BTN_DIMENSION, ConfigGlobal.HOW_TO_PLAY_ICON);
		this.panelMain.add(howToPlay);

		MenuPrincipalView.quit = AddFrameComponent.addButton(this, ConstantTexts.GeneralTexts.get("quit"),
				ConfigGlobal.btnBackgroundColor, ConfigGlobal.btnForegroundColor, ConfigGlobal.FONT_PRINCIPAL, ConfigGlobal.DEFAULT_BTN_DIMENSION, ConfigGlobal.QUIT_ICON);
		this.panelMain.add(quit);

		this.add(this.panelMain);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button.getText().equals(ConstantTexts.GeneralTexts.get("quit"))) {
			this.menuType = ActionMenuType.QUIT;
		} else if (button.getText().equals(ConstantTexts.GeneralTexts.get("startLocal"))) {
			this.menuType = ActionMenuType.START_LOCAL;
//fermer fenetre apres lancement de startLocal******************************************
		} else if (button.getText().equals(ConstantTexts.GeneralTexts.get("startMultiplayer"))) {
			this.menuType = ActionMenuType.START_MULTIPLAYER;
		}
		else if (button.getText().equals(ConstantTexts.GeneralTexts.get("howToPlay"))) {
			this.menuType = ActionMenuType.HOW_TO_PLAY;
		}

		this.setVisible(false);
		this.controller.actionMenuPrincipal();
	}

	public ActionMenuType getMenuType() {
		return menuType;
	}
}
