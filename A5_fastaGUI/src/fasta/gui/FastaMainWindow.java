package fasta.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

public class FastaMainWindow extends JFrame implements ActionListener
{

    SequencePanel sequence1Panel;
    SequencePanel sequence2Panel;

    public FastaMainWindow()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("FASTA GUI");

        this.initializeComponents();

        // sequence1Panel.setFASTA(new Fasta(">Test", "ACTGCCCGCCCATTC"));
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
        if (e.getActionCommand().equals("DOTPLOT"))
        {
            if (sequence1Panel.getFASTA() != null
                    && sequence2Panel.getFASTA() != null)
            {
                FastaDotplotDialog dlg = new FastaDotplotDialog(this);

                dlg.dotplot(sequence1Panel.getFASTA(),
                        sequence2Panel.getFASTA());
                dlg.setVisible(true);

            }
            else
            {
                JOptionPane.showMessageDialog(this, "No sequences loaded!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
