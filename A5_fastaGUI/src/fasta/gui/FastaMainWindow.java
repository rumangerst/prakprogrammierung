package fasta.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class FastaMainWindow extends JFrame implements ActionListener
{
	SequencePanel sequence1Panel;
	SequencePanel sequence2Panel;
	
	public FastaMainWindow()
	{
		this.setSize(800, 600);
		this.setTitle("FASTA GUI");
		
		this.initializeComponents();
		
		//sequence1Panel.setFASTA(new Fasta(">Test", "ACTGCCCGCCCATTC"));
	}
	
	private void initializeComponents()
	{
		this.getContentPane().setLayout(new GridLayout(3, 1));
		
		sequence1Panel = new SequencePanel("Sequence 1", this);
		this.add(sequence1Panel);
		
		sequence2Panel = new SequencePanel("Sequence 2", this);
		this.add(sequence2Panel);
		
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
		JButton bDotplot = new JButton("Dotplot Sequences");
		bDotplot.addActionListener(this);
		bDotplot.setActionCommand("DOTPLOT");
		
		panel.add(bDotplot);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("DOTPLOT"))
		{
			FastaDotplotDialog dlg = new FastaDotplotDialog(this);
			
			dlg.setVisible(true);
		}
	}		
}
