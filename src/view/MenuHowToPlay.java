package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.ActionMenuHowToPlayType;
import configuration.ActionMenuType;
import configuration.ConfigGlobal;
import configuration.ConstantTexts;

public class MenuHowToPlay extends GuiView{
	private JPanel howToPlay;
	private JLabel textContent;
	private JButton leftInvisible, rightInvisible;
	private JButton backToMenu;
	
	private ActionMenuHowToPlayType menuType;
	
	public  MenuHowToPlay() {
		this.howToPlay=new JPanel();
		this.howToPlay.setLayout(new BorderLayout());
			this.title = AddFrameComponent.addLabel(this, ConstantTexts.GeneralTexts.get("howToPlay"), ConstantColors.C_BLACK, JLabel.CENTER, ConfigGlobal.FONT_PRINCIPAL.deriveFont(Font.BOLD).deriveFont(40f));
			this.leftInvisible = new JButton();
			this.leftInvisible.setBackground(ConstantColors.C_WHITE);
			
			this.rightInvisible = new JButton();
			this.rightInvisible.setBackground(ConstantColors.C_WHITE);
			
			this.backToMenu = AddFrameComponent.addButton(this, "Retour au menu", ConstantColors.C_BLUE_WATER, ConstantColors.C_WHITE, ConfigGlobal.FONT_PRINCIPAL);
			
			this.textContent = AddFrameComponent.addLabel(this, "<html>"
				+ "<p>"
				+ "Battleship est un jeu de soci�t� sur le th�me de la guerre pour deux joueurs dans lequel les adversaires tentent de deviner l'emplacement des navires de guerre de leur adversaire et de les couler. Une version papier et crayon du jeu\r\n"
				+ "remonte � la Premi�re Guerre mondiale, mais la plupart des gens connaissent le jeu gr�ce au jeu de plateau en plastique qui a �t� commercialis� pour la premi�re fois par la Milton Bradley Company en 1967. Depuis lors, le jeu a engendr�\r\n"
				+ "divers jeux vid�o. et les variations des applications pour smartphone. Aujourd'hui, la version du jeu de soci�t� est produite par Hasbro, la soci�t� qui a acquis Milton Bradley en 1984."
				+ "</p> "
				
				+ "<p></p>"
				
				+ "<p>"
				+ "Le gameplay est simple. Chaque joueur cache les vaisseaux sur une grille en plastique contenant les coordonn�es d'espace vertical et horizontal. Les joueurs appellent � tour de r�le les coordonn�es des lignes et des colonnes sur la grille de l'autre joueur pour tenter d'identifier une case contenant un navire."
				+ "</p>"
				
				+ "<p></p>"
				
				+ "<p>"
				+ "Le plateau de jeu que chaque joueur obtient a deux grilles: une grille sup�rieure et une grille inf�rieure. La grille inf�rieure est utilis�e par le joueur pour \"cacher\" l'emplacement de ses propres vaisseaux, tandis que la grille sup�rieure est utilis�e pour enregistrer les coups tir�s vers l'adversaire et pour documenter si ces coups �taient des coups ou des rat�s."
				+ "</p>"
				
				+ "</html>", ConfigGlobal.labelColor, JLabel.CENTER, ConfigGlobal.FONT_PRINCIPAL.deriveFont(16f));
			
			this.howToPlay.add(this.backToMenu, BorderLayout.PAGE_END);
			this.howToPlay.add(this.leftInvisible, BorderLayout.LINE_START);
			this.howToPlay.add(this.rightInvisible, BorderLayout.LINE_END);
			this.howToPlay.add(this.title, BorderLayout.PAGE_START);
			this.howToPlay.add(this.textContent, BorderLayout.CENTER);
		this.add(this.howToPlay);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonSource = (JButton) e.getSource();
		
		if(buttonSource.getText().equals("Retour au menu"))
		{
			this.menuType = ActionMenuHowToPlayType.RETURN_MENU;
		}
		
		this.controller.actionMenuHowToPlay();
	}
	
	public ActionMenuHowToPlayType getMenuType()
	{
		return this.menuType;
	}

}
