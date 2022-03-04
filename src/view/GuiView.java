package view;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.ActionMenuType;
import configuration.ConfigGlobal;
import controller.GameController;

public abstract class GuiView extends JFrame implements ActionListener {
	private ImageIcon logo;
	protected GameController controller;
	protected  JLabel title;
	
	public GuiView()
	{
		this.setSize(ConfigGlobal.WIDTH_WINDOW, ConfigGlobal.HEIGHT_WINDOW);
		this.setLocation(ConfigGlobal.X_LOCATION_WINDOW, ConfigGlobal.Y_LOCATION_WINDOW);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.logo = new ImageIcon("src/public/img/logo.jpg");
		this.setIconImage(this.logo.getImage());
		this.setTitle("BattleShip from MadeGAME");
	}
	
	public void setController(GameController controller)
	{
		this.controller = controller;
	}
	
	public void changePanel(JPanel oldPanel, JPanel newPanel) {
		oldPanel.setVisible(false);
		this.getContentPane().add(newPanel);
	}
}