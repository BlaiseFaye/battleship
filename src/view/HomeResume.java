package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import configuration.ConfigGlobal;

public class HomeResume extends GuiView {
	private JProgressBar progress;
	private JLabel background;
	public static boolean isLoading;
	
	public HomeResume() {
		this.setLayout(new GridLayout(0, 1));
		HomeResume.isLoading = true;
		
		try {
			background = new JLabel(new ImageIcon(ImageIO.read(new File("src/public/img/homeResume.jpg"))));
			background.setPreferredSize(new Dimension(ConfigGlobal.WIDTH_WINDOW, ConfigGlobal.HEIGHT_WINDOW));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		progress = new JProgressBar(0, 100);
		progress.setPreferredSize(new Dimension(1000, 20));
		progress.setBackground(ConstantColors.C_WHITE);
		progress.setForeground(ConstantColors.C_SUCCESS);

		JPanel progressPanel = new JPanel();
			progressPanel.add(progress);
			progressPanel.add(background);
		this.add(progressPanel);

		this.setVisible(true);
		
		this.loadGame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public void loadGame() {
		for(int i = 0; i < 101; i++)
		{
			this.progress.setValue(i);
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.dispose();
		HomeResume.isLoading = false;
	}
}
