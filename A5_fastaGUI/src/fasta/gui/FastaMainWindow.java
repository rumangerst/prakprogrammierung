package fasta.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import fastaV2.Fasta;

public class FastaMainWindow extends JFrame
{
	SequencePanel sequence1Panel;
	SequencePanel sequence2Panel;
	
	public FastaMainWindow()
	{
		this.setSize(800, 600);
		this.setTitle("FASTA GUI");
		
		this.initializeUI();
		
		sequence1Panel.setFASTA(new Fasta(">Test", "ACTGCCCGCCCATTC"));
	}
	
	private void initializeUI()
	{
		this.getContentPane().setLayout(new GridLayout(3, 1));
		
		sequence1Panel = new SequencePanel("Sequence 1");
		this.add(sequence1Panel);
		
		sequence2Panel = new SequencePanel("Sequence 2");
		this.add(sequence2Panel);
		
		initializeToolsUI();
	}	
	
	
	private void initializeToolsUI()
	{
		/**
		 * Group panel
		 */
		JPanel panel = new JPanel();		
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Tools"));
		
		this.add(panel);
		
		/**
		 * Dotplot button
		 */
		JButton bDotplot = new JButton("Dotplot Sequence 1/2");
		panel.add(bDotplot);
	}
}
