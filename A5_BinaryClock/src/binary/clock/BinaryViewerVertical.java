package binary.clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class BinaryViewerVertical extends JPanel
{
	private int length;
	private Color inactiveColor;
	private Color activeColor;
	private Color borderColor;
	
	private int number;
	
	public BinaryViewerVertical()
	{
		length = 1;
		inactiveColor = new Color(50,50,50);
		activeColor = new Color(0,100,200);
		setBorderColor(new Color(0, 50, 100));
		number = 0;
	}
	
	public void setLength(int length)
	{
		if(length < 1)
			throw new IllegalArgumentException("Length must me at least one!");
		
		this.length = length;
		
		setNumber(0);
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
	
	public Color getBorderColor()
	{
		return borderColor;
	}

	public void setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
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
	
	public boolean[] getBinaryNumber()
	{
		boolean[] binary = new boolean[length];
		
		int leftOverNumber = number;
		
		while(leftOverNumber != 0)
		{
			int highestBinary = (int)Math.floor(Math.log10(leftOverNumber) / Math.log10(2));
			binary[highestBinary] = true;
			
			leftOverNumber -= (int)Math.pow(2, highestBinary);			
		}
		
		return binary;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		/**
		 * Resize component, using width
		 */
		this.setPreferredSize(new Dimension(this.getWidth(), length * this.getWidth()));
		
		
		/**
		 * Draw number
		 */
		boolean[] binary = getBinaryNumber();
		
		int cellSize = this.getWidth();
		int y = this.getHeight() - cellSize;
		
		for(int i = 0; i < length; i++)
		{
			boolean active = binary[i];
			
			if(active)
			{
				g2.setColor(activeColor);
				g2.fillOval(5, 5 + y, cellSize - 10, cellSize - 10);
			}
			else
			{
				g2.setColor(inactiveColor);
				g2.fillOval(5, 5 + y, cellSize - 10, cellSize - 10);
			}
			
			g2.setStroke(new BasicStroke(2));
			g2.setColor(borderColor);
			g2.drawOval(5, 5 + y, cellSize - 10, cellSize - 10);
			
			y -= cellSize;
		}
	}

	
	
}
