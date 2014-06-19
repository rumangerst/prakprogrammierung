package binary.clock;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BinaryViewerVertical extends JPanel
{
	private int length;
	private Color inactiveColor;
	private Color activeColor;
	
	private int number;
	
	public BinaryViewerVertical()
	{
		length = 1;
		inactiveColor = new Color(50,50,50);
		activeColor = new Color(0,100,200);
		number = 0;
	}
	
	public void setLength(int length)
	{
		if(length < 1)
			throw new IllegalArgumentException("Length must me at least one!");
		
		this.length = length;
		this.repaint();
	}
	
	public int getLength()
	{
		return length;
	}

	public Color getInactiveColor()
	{
		return inactiveColor;
	}

	public void setInactiveColor(Color inactiveColor)
	{
		this.inactiveColor = inactiveColor;
		this.repaint();
	}

	public Color getActiveColor()
	{
		return activeColor;
	}

	public void setActiveColor(Color activeColor)
	{
		this.activeColor = activeColor;
		this.repaint();
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		if(number < 0 || number > getMaximumNumber())
			throw new IllegalArgumentException("Number too high!");
		
		this.number = number;
		this.repaint();
	}
	
	public int getMaximumNumber()
	{
		int max = 0;
		
		for(int i = 0; i < length;i++)
		{
			max += Math.pow(2, i);
		}
		
		return max;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
	}
	
}
