package fasta.gui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fastaV2.Fasta;

public class FastaDotplotDialog extends JDialog
{
	private JTextField uiSequence1Header;
	private JTextField uiSequence2Header;
	private FastaDotPlotCanvas dotplotCanvas;
	
	public FastaDotplotDialog(Frame parent)
	{
		super(parent);
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		this.setModal(true);
		this.setSize(400, 500);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 5;
		
		/**
		 * Header of sequence 1
		 */
		JLabel sequence1Name = new JLabel("Sequence 1");
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(sequence1Name, c);
		
		uiSequence1Header = new JTextField();
		uiSequence1Header.setEditable(false);
		uiSequence1Header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		this.add(uiSequence1Header, c);
		
		/**
		 * Header of sequence 2
		 */
		JLabel sequence2Name = new JLabel("Sequence 2");
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(sequence2Name, c);
		
		uiSequence2Header = new JTextField();
		uiSequence2Header.setEditable(false);
		uiSequence2Header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		this.add(uiSequence2Header, c);
		
		/**
		 * Content pane
		 */
		dotplotCanvas = new FastaDotPlotCanvas();
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		
		JScrollPane dotPlotContainer = new JScrollPane(dotplotCanvas);
		dotPlotContainer.setAutoscrolls(true);
		
		this.add(dotPlotContainer, c);

	}
	
	public void dotplot(Fasta fasta1, Fasta fasta2)
	{
		dotplotCanvas.dotplot(fasta1, fasta2);
		uiSequence1Header.setText(fasta1.getHeader());
		uiSequence2Header.setText(fasta2.getHeader());
	}
}
