package fasta.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

public class SequencePanel extends JPanel implements ActionListener
{

    private JFrame parent;
    private String name;
    private JTextField uiSequenceHeader;
    private JTextArea uiSequenceDNA;
    private Fasta fasta;

    public SequencePanel(String name, JFrame parent)
    {
        this.name = name;
        this.parent = parent;

        initializeComponents();
    }

    private void initializeComponents()
    {
        this.setLayout(new BorderLayout());

        /**
         * Group panel
         */
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(name));
        panel.setLayout(new GridBagLayout());

        this.add(panel);

        /**
         * Add Header/DNA components
         */
        GridBagConstraints c = new GridBagConstraints();

        JLabel headerLabel = new JLabel("Header");
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        panel.add(headerLabel, c);

        uiSequenceHeader = new JTextField();
        uiSequenceHeader.setEditable(false);
        uiSequenceHeader.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        panel.add(uiSequenceHeader, c);

        JLabel sequenceLabel = new JLabel("Sequence");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.weighty = 0;
        panel.add(sequenceLabel, c);

        uiSequenceDNA = new JTextArea();
        uiSequenceDNA.setEditable(false);
        uiSequenceDNA.setBorder(BorderFactory.createEtchedBorder());
        uiSequenceDNA.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));

        //Embedd JTextArea in JScrollPane
        JScrollPane uiSequenceDNA_scollPane = new JScrollPane(uiSequenceDNA);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        panel.add(uiSequenceDNA_scollPane, c);

        /**
         * Add buttons
         */
        JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton loadButton = new JButton("Load ...");
        loadButton.setActionCommand("LOAD");
        loadButton.addActionListener(this);
        buttonpanel.add(loadButton);

        JButton saveButton = new JButton("Save ...");
        saveButton.setActionCommand("SAVE");
        saveButton.addActionListener(this);
        buttonpanel.add(saveButton);

        JButton inputButton = new JButton("Manual input ...");
        inputButton.setActionCommand("INPUT");
        inputButton.addActionListener(this);
        buttonpanel.add(inputButton);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 0;
        panel.add(buttonpanel, c);
    }

    public void update()
    {
        this.uiSequenceHeader.setText(getFASTA().getHeader());

        String sequence = getFASTA().getDNASequence();
        StringBuffer formatedsequence = new StringBuffer();

        for (int i = 0; i < sequence.length(); i += 80)
        {
            if (i != 0)
            {
                formatedsequence.append("\n");
            }

            String dna_substring = sequence.substring(i,
                    Math.min(i + 80, sequence.length()));

            formatedsequence.append(dna_substring);
        }

        this.uiSequenceDNA.setText(formatedsequence.toString());
    }

    public void setFASTA(Fasta fasta)
    {
        this.fasta = fasta;
        update();
    }

    public Fasta getFASTA()
    {
        return this.fasta;
    }

    private void loadFromFile()
    {
        final JFileChooser dlg = new JFileChooser();
        dlg.setCurrentDirectory(new File(System.getProperty("user.dir")));

        if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                Fasta fasta = new Fasta();
                fasta.read(dlg.getSelectedFile());

                this.setFASTA(fasta);
            }
            catch (IllegalHeaderException | IllegalSequenceException | IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();

                JOptionPane.showMessageDialog(this,
                        "Couldn't load FASTA file!\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveToFile()
    {
        if (fasta == null)
        {
            JOptionPane.showMessageDialog(this, "No FASTA loaded!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final JFileChooser dlg = new JFileChooser();
        dlg.setCurrentDirectory(new File(System.getProperty("user.dir")));

        if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                fasta.write(dlg.getSelectedFile());
                
                JOptionPane.showMessageDialog(this, "FASTA written to file " + dlg.getSelectedFile().getName(),
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, "File error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalHeaderException | IllegalSequenceException ex)
            {
                JOptionPane.showMessageDialog(this, "Illegal FASTA file!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("LOAD"))
        {
            loadFromFile();
        }
        else if (e.getActionCommand().equals("SAVE"))
        {
            saveToFile();
        }
        else if (e.getActionCommand().equals("INPUT"))
        {
            FastaInputSequenceDialog dlg = new FastaInputSequenceDialog(this.parent);
            Fasta input = dlg.showDialog();

            if (input != null)
            {
                setFASTA(input);
            }
        }
    }
}
