package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.ConfigGlobal;
import configuration.ConstantTexts;
import configuration.DifficultyType;

public class MenuDifficultyView extends GuiView {
	private JPanel panelDifficulty;
	
	private static JButton easy, challenger;
	private DifficultyType difficultyType;
	
	public MenuDifficultyView()
	{
		this.panelDifficulty = new JPanel();
		this.panelDifficulty.setLayout(new GridLayout(0, 1));

		this.title = AddFrameComponent.addLabel(this, ConstantTexts.GeneralTexts.get("choiceDifficulty"), ConfigGlobal.labelColor, JLabel.CENTER, ConfigGlobal.FONT_PRINCIPAL);
		this.panelDifficulty.add(this.title);

		MenuDifficultyView.easy = AddFrameComponent.addButton(this, ConstantTexts.GeneralTexts.get("easy"), ConfigGlobal.btnBackgroundColor, ConfigGlobal.btnForegroundColor, ConfigGlobal.FONT_PRINCIPAL);
		this.panelDifficulty.add(easy);

		MenuDifficultyView.challenger = AddFrameComponent.addButton(this, ConstantTexts.GeneralTexts.get("challenger"), ConfigGlobal.btnBackgroundColor, ConfigGlobal.btnForegroundColor, ConfigGlobal.FONT_PRINCIPAL);
		this.panelDifficulty.add(challenger);
		
		this.add(this.panelDifficulty);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		if(button.getText().equals(ConstantTexts.GeneralTexts.get("easy")))
		{
			this.difficultyType = DifficultyType.EASY;
		}
		else if(button.getText().equals(ConstantTexts.GeneralTexts.get("challenger")))
		{
			this.difficultyType = DifficultyType.CHALLENGER;
		}
		
		this.setVisible(false);
		this.controller.actionMenuDifficulty();
	}

	public DifficultyType getDifficultyType() {
		return difficultyType;
	}
}
