package view;

import javax.swing.JButton;

import helper.Coordinate;

public class CaseGrid extends JButton {
	private Coordinate coord;
	
	public CaseGrid(Coordinate coord)
	{
		this.coord = coord;
	}
	
	public Coordinate getCoord()
	{
		return this.coord;
	}
}
