/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ju39gox
 */
public class MainWindow extends JFrame implements WindowListener, DocumentListener, ActionListener
{

    private JTextArea currentTextEditor;
    private File currentFile;
    private boolean currentFileEdited;

    public MainWindow()
    {
        currentFile = null;
        currentFileEdited = false;

        initializeComponents();
        requestCloseFile();
    }

    private void initializeComponents()
    {
        this.setSize(1024, 768);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);

        /**
         * Add menu bar
         */
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newFileItem = new JMenuItem("New File");
        newFileItem.setActionCommand("NEW_FILE");
        newFileItem.addActionListener(this);
        fileMenu.add(newFileItem);

        JMenuItem openFileItem = new JMenuItem("Open File ...");
        openFileItem.setActionCommand("OPEN_FILE");
        openFileItem.addActionListener(this);
        fileMenu.add(openFileItem);

        fileMenu.addSeparator();

        JMenuItem saveFileItem = new JMenuItem("Save File");
        saveFileItem.setActionCommand("SAVE_FILE");
        saveFileItem.addActionListener(this);
        fileMenu.add(saveFileItem);

        JMenuItem saveAsFileItem = new JMenuItem("Save File as ...");
        saveAsFileItem.setActionCommand("SAVE_FILE_AS");
        saveAsFileItem.addActionListener(this);
        fileMenu.add(saveAsFileItem);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        /**
         * Add text area
         */
        currentTextEditor = new JTextArea();
        currentTextEditor.getDocument().addDocumentListener(this);

        JScrollPane textContainer = new JScrollPane(currentTextEditor);
        textContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(textContainer, BorderLayout.CENTER);
    }

    public boolean requestCloseFile()
    {
        if (currentFileEdited)
        {
            int answer = JOptionPane.showConfirmDialog(this, "Current file has been changed! Save it?");

            if (answer == JOptionPane.YES_OPTION)
            {
                saveFile();
            }
            else if (answer == JOptionPane.CANCEL_OPTION)
            {
                return false;
            }
        }

        currentTextEditor.setText("");
        currentFile = null;
        currentFileEdited = false;

        updateWindowTitle();

        return true;
    }

    public void openFile()
    {
        if (requestCloseFile())
        {
            JFileChooser dlg = new JFileChooser();

            if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    BufferedReader rd = new BufferedReader(new FileReader(dlg.getSelectedFile()));

                    StringBuilder text = new StringBuilder();
                    String line = null;

                    while ((line = rd.readLine()) != null)
                    {
                        text.append('\n');
                        text.append(line);
                    }
                    
                    if(text.length() != 0)
                    {
                        text.delete(0, 1);
                    }

                    rd.close();

                    currentTextEditor.setText(text.toString());
                    currentFileEdited = false;
                    currentFile = dlg.getSelectedFile();

                    updateWindowTitle();

                }
                catch (FileNotFoundException ex)
                {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    public void saveFile(File file)
    {
        if (file != null)
        {

            try
            {
                FileWriter w = new FileWriter(file);
                w.write(currentTextEditor.getText());
                w.close();

                currentFile = file;
                currentFileEdited = false;

                updateWindowTitle();
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "File is NULL!");
        }
    }

    public void saveFileAs()
    {

        JFileChooser dlg = new JFileChooser();

        if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            currentFile = dlg.getSelectedFile();
        }
        else
        {
            return;
        }

        saveFile(currentFile);
    }

    public void saveFile()
    {
        if (currentFile == null)
        {
            JFileChooser dlg = new JFileChooser();

            if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                currentFile = dlg.getSelectedFile();
            }
            else
            {
                return;
            }
        }

        saveFile(currentFile);
    }

    private void updateWindowTitle()
    {
        if (currentFile == null)
        {
            if (currentFileEdited)
            {
                setTitle("New file (*)");
            }
            else
            {
                setTitle("New file");
            }
        }
        else
        {
            if (currentFileEdited)
            {
                setTitle(currentFile.getAbsolutePath() + " (*)");
            }
            else
            {
                setTitle(currentFile.getAbsolutePath());
            }
        }
    }

    private void documentChanged()
    {
        if (!currentFileEdited)
        {
            currentFileEdited = true;
            updateWindowTitle();
        }
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        if (requestCloseFile())
        {
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
        documentChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("NEW_FILE"))
        {
            requestCloseFile();
        }
        else if (e.getActionCommand().equals("OPEN_FILE"))
        {
            openFile();
        }
        else if (e.getActionCommand().equals("SAVE_FILE"))
        {
            saveFile();
        }
        else if (e.getActionCommand().equals("SAVE_FILE_AS"))
        {
            saveFileAs();
        }
    }
}
