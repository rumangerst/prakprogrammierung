package fasta.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FastaMainWindow extends JFrame
{
	private JTextField uiSequence1Header;
	private JTextField uiSequence2Header;
	private JTextArea uiSequence1DNA;
	private JTextArea uiSequence2DNA;
	
	public FastaMainWindow()
	{
		this.setSize(800, 600);
		this.setTitle("FASTA GUI");
		
		this.initializeUI();
	}
	
	private void initializeUI()
	{
		this.getContentPane().setLayout(new GridLayout(3, 1));
		
		initializeSequence1UI();
		initializeSequence2UI();
		initializeToolsUI();
	}
	
	private void initializeSequence1UI()
	{
		/**
		 * Group panel
		 */
		JPanel panel = new JPanel();		
		panel.setBorder(BorderFactory.createTitledBorder("Sequence 1"));
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		
		this.add(panel);
		
		/**
		 * Add components		
		 */
		panel.add(new JLabel("Header"));
		
		uiSequence1Header = new JTextField();
		panel.add(uiSequence1Header);
		
		panel.add(new JLabel("Sequence"));
		
		uiSequence1DNA = new JTextArea();
		panel.add(uiSequence1DNA);
		
		
	}
	
	private void initializeSequence2UI()
	{
		/**
		 * Group panel
		 */
		JPanel panel = new JPanel();		
		panel.setBorder(BorderFactory.createTitledBorder("Sequence 2"));
		
		this.add(panel);
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
