package fasta.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import fastaV2.Fasta;

public class FastaDotPlotCanvas extends JPanel
{
	private Fasta fasta1;
	private Fasta fasta2;

	public FastaDotPlotCanvas()
	{

	}

	public void dotplot(Fasta fasta1, Fasta fasta2)
	{
		this.fasta1 = fasta1;
		this.fasta2 = fasta2;

		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if (fasta1 == null || fasta2 == null
				|| fasta1.getDNASequenceLength() == 0
				|| fasta2.getDNASequenceLength() == 0)
			return;

		int cellSize = 16;
		this.setPreferredSize(new Dimension(cellSize
				* (fasta1.getDNASequenceLength() + 1), cellSize
				* (fasta2.getDNASequenceLength() + 1)));

		/**
		 * Draw labels
		 */
		g.setColor(Color.BLACK);
		
		for (int i = 1; i < fasta1.getDNASequenceLength() + 1; i++)
		{
			g.drawString("" + fasta1.getDNASequence().charAt(i - 1), i * cellSize + 4, 15);
		}
		for (int j = 1; j < fasta2.getDNASequenceLength() + 1; j++)
		{
			g.drawString("" + fasta2.getDNASequence().charAt(j - 1), 4, j * cellSize + 15);
		}
		
		/**
		 * Dotplot
		 */
		for (int i = 1; i < fasta1.getDNASequenceLength() + 1; i++)
		{
			for (int j = 1; j < fasta2.getDNASequenceLength() + 1; j++)
			{
				/**
				 * Draw grid
				 */
				g.setColor(Color.GRAY);
				g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);

				/**
				 * If matches, draw dot
				 */
				if (fasta1.getDNASequence().charAt(i - 1) == fasta2
						.getDNASequence().charAt(j - 1))
				{
					g.setColor(Color.DARK_GRAY);
					g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
				}

			}
		}
	}
}
