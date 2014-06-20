package fasta.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fastaV2.Fasta;
import fastaV2.IllegalHeaderException;
import fastaV2.IllegalSequenceException;

public class FastaInputSequenceDialog extends JDialog implements ActionListener
{
	private JTextField uiSequenceHeader;
	private JTextArea uiSequenceDNA;

	private Fasta fasta;

	public FastaInputSequenceDialog(Frame owner)
	{
		super(owner);
		initializeComponents();

	}

	private void initializeComponents()
	{
		this.setTitle("Input sequence");
		this.setSize(400, 300);
		this.setModal(true);
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		/**
		 * Header input
		 */
		JLabel headerLabel = new JLabel("Header");
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		this.add(headerLabel, c);

		uiSequenceHeader = new JTextField();
		uiSequenceHeader.setEditable(true);
		uiSequenceHeader.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		this.add(uiSequenceHeader, c);

		/**
		 * DNA sequence input
		 */
		JLabel sequenceLabel = new JLabel("Sequence");
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 0;
		this.add(sequenceLabel, c);

		uiSequenceDNA = new JTextArea();
		uiSequenceDNA.setEditable(true);
		uiSequenceDNA.setBorder(BorderFactory.createEtchedBorder());
		uiSequenceDNA.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));

		// Embedd JTextArea in JScrollPane
		JScrollPane uiSequenceDNA_scollPane = new JScrollPane(uiSequenceDNA);

		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		this.add(uiSequenceDNA_scollPane, c);

		/**
		 * Buttons
		 */
		JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton loadButton = new JButton("Cancel");
		loadButton.setActionCommand("CANCEL");
		loadButton.addActionListener(this);
		buttonpanel.add(loadButton);

		JButton inputButton = new JButton("OK");
		inputButton.setActionCommand("OK");
		inputButton.addActionListener(this);
		buttonpanel.add(inputButton);

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.weighty = 0;
		this.add(buttonpanel, c);
	}

	public Fasta showDialog()
	{
		this.setVisible(true);

		return this.fasta;
	}

	@Override
	public void actionPerformed(ActionEvent args)
	{
		if (args.getActionCommand().equals("CANCEL"))
		{
			this.fasta = null; //Reset fasta
			this.setVisible(false);
		}
		else if (args.getActionCommand().equals("OK"))
		{
			this.fasta = null; //Reset fasta
			
			try
			{
				String header = this.uiSequenceHeader.getText().trim();
				String dna = this.uiSequenceDNA.getText().replace("\n", "")
						.replace("\r", "").replace(" ", "").toUpperCase();

				this.fasta = new Fasta(header, dna);
				this.setVisible(false);
			}
			catch (IllegalSequenceException e)
			{
				JOptionPane.showMessageDialog(this, "Illegal DNA sequence!",
						"Error", JOptionPane.ERROR_MESSAGE);
				uiSequenceDNA.selectAll();
			}
			catch (IllegalHeaderException e)
			{
				JOptionPane.showMessageDialog(this, "Illegal FASTA header!",
						"Error", JOptionPane.ERROR_MESSAGE);
				uiSequenceHeader.selectAll();
			}
		}
	}
}
