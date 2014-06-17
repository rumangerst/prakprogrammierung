package fasta.gui;

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
	
	public void setComparedSequences(Fasta fasta1, Fasta fasta2)
	{
		this.fasta1 = fasta1;
		this.fasta2 = fasta2;
		
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		if(fasta1 == null || fasta2 == null)
			return;
	}
}
