package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import configuration.ConfigGlobal;
import helper.AudioHelp;

public class AddFrameComponent {
	public static void modifyLabel(JFrame j, JLabel label, String textModified, Color foregroundColorModified, Font font)
	{
		label.setText(textModified);
		label.setForeground(foregroundColorModified);
		label.setFont(font);
	}
	
	public static JLabel addLabel(JFrame j, String text, Color color, int position, Font font)
	{
		JLabel label = new JLabel(text);
		label.setForeground(color);
		label.setHorizontalAlignment(position);
		label.setFont(font);
		
		j.add(label);
		
		return label;
	}

	public static JTextField addTextField(JFrame j, Dimension dimension, Color color, Font font)
	{
		JTextField textField = new JTextField();
		textField.setPreferredSize(dimension);
		textField.setBackground(color);
		textField.setFont(font);
		
		j.add(textField);
		
		return textField;
	}
	
	/**
	 * Adding button to specified frame in parameters
	 * @param j
	 * @param text
	 * @param backgroundColor
	 * @param foregroundColor
	 * @param font
	 * @return button created with specific caracteristics parameters
	 */
	public static JButton addButton(JFrame j, String text, Color backgroundColor, Color foregroundColor, Font font)
	{
		JButton button = new JButton(text);
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.addActionListener((ActionListener)j);
		button.setFont(font);
		button.setFocusPainted(false);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt)
			{
				button.setBackground(ConstantColors.C_BLUE_DARK);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_IN);
			}
			
			public void mouseExited(MouseEvent evt)
			{
				button.setBackground(backgroundColor);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_OUT);
			}
		});
		
		j.add(button);
		
		return button;
	}
	
	public static JButton addButton(JFrame j, String text, Color backgroundColor, Color foregroundColor, Font font, Dimension dimension)
	{
		JButton button = new JButton(text);
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.addActionListener((ActionListener)j);
		button.setFont(font);
		button.setPreferredSize(dimension);
		button.setFocusPainted(false);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt)
			{
				button.setBackground(ConstantColors.C_BLUE_DARK);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_IN);
			}
			
			public void mouseExited(MouseEvent evt)
			{
				button.setBackground(backgroundColor);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_OUT);
			}
		});
		
		j.add(button);
		
		return button;
	}
	
	public static JButton addButton(JFrame j, String text, Color backgroundColor, Color foregroundColor, Font font, Dimension dimension, String pathIcon)
	{
		ImageIcon icon = new ImageIcon(pathIcon);
		
		JButton button = new JButton(text, icon);
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.addActionListener((ActionListener)j);
		button.setFont(font);
		button.setPreferredSize(dimension);
		button.setIconTextGap(40);
		button.setFocusPainted(false);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt)
			{
				button.setBackground(ConstantColors.C_BLUE_DARK);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_IN);
			}
			
			public void mouseExited(MouseEvent evt)
			{
				button.setBackground(backgroundColor);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_OUT);
			}
		});
		
		j.add(button);
		
		return button;
	}
	
	public static JButton addButton(JFrame j, JPanel p, String text, Color backgroundColor, Color foregroundColor, Font font, String pathIcon)
	{
		ImageIcon icon = new ImageIcon(pathIcon);
		
		JButton button = new JButton(text, icon);
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.addActionListener((ActionListener)j);
		button.setFont(font);
		button.setIconTextGap(40);
		button.setFocusPainted(false);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt)
			{
				button.setBackground(ConstantColors.C_BLUE_DARK);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_IN);
			}
			
			public void mouseExited(MouseEvent evt)
			{
				button.setBackground(backgroundColor);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_OUT);
			}
		});
		
		p.add(button);
		
		j.add(p);
		
		return button;
	}
	
	/**
	 * Adding button to specified panel in parameters
	 * @param j
	 * @param text
	 * @param backgroundColor
	 * @param foregroundColor
	 * @param font
	 * @return button created with specific caracteristics parameters
	 */
	public static JButton addButton(JFrame j, JPanel p, String text, Color backgroundColor, Color foregroundColor, Font font)
	{
		JButton button = new JButton(text);
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.addActionListener((ActionListener)j);
		button.setFont(font);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt)
			{
				button.setBackground(ConstantColors.C_BLUE_DARK);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_IN);
			}
			
			public void mouseExited(MouseEvent evt)
			{
				button.setBackground(backgroundColor);
				AudioHelp.playSound(ConfigGlobal.SOUND_EFFECT_BTN_HOV_OUT);
			}
		});
		
		p.add(button);
		
		return button;
	}
}
