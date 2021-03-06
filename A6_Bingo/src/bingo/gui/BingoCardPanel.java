/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.gui;

import bingo.game.BingoCard;
import bingo.game.BingoGame;
import bingo.game.BingoListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 *
 * @author ju39gox
 */
public class BingoCardPanel extends JPanel implements BingoListener, ActionListener
{

    private BingoGame game;
    private String playerName;
    private BingoCardNumberButton[][] uiBingoFields;
    private JButton uiBingoShoutBingo;

    public BingoCardPanel(BingoGame game, String playerName)
    {
        this.game = game;
        this.playerName = playerName;
        this.uiBingoFields = new BingoCardNumberButton[5][5];

        game.addBingoListener(this);

        initializeComponents();
    }

    private BingoCard getPlayer()
    {
        return game.getPlayer(playerName);
    }

    private void initializeComponents()
    {
        this.setEnabled(false);
        this.setBorder(BorderFactory.createTitledBorder(playerName));
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        //Add labels
        for (int i = 1; i < 6; i++)
        {
            JLabel labelTop = new JLabel("" + "BINGO".charAt(i - 1), JLabel.CENTER);
            c.gridx = i;
            c.gridy = 0;
            this.add(labelTop, c);

            JLabel labelBottom = new JLabel("" + "BINGO".charAt(i - 1), JLabel.CENTER);
            c.gridx = 0;
            c.gridy = i;
            this.add(labelBottom, c);
        }

        //Add field x,y
        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                BingoCardNumberButton button = new BingoCardNumberButton(game, playerName, x, y);
                uiBingoFields[x][y] = button;

                c.weightx = 1;
                c.weighty = 1;
                c.gridx = 1 + x;
                c.gridy = 1 + y;

                this.add(button, c);
            }
        }

        //Add line
        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        add(Box.createVerticalStrut(5), c);

        //Add 'bingo!' button
        uiBingoShoutBingo = new JButton("Bingo!!!");
        c.gridx = 1;
        c.gridwidth = 5;
        uiBingoShoutBingo.addActionListener(this);
        uiBingoShoutBingo.setActionCommand("SHOUT_BINGO");
        add(uiBingoShoutBingo, c);
    }

    @Override
    public void bingoDiced(int number)
    {
    }

    @Override
    public void bingoTurn(int secondsLeft)
    {
        this.setEnabled(true);

        if (getPlayer().isHuman())
        {
            uiBingoShoutBingo.setVisible(true);
        }
        else
        {
            uiBingoShoutBingo.setVisible(false);
        }
    }

    @Override
    public void bingoTurnTimeout()
    {
        this.setEnabled(false);
        uiBingoShoutBingo.setVisible(false);
    }

    @Override
    public void bingoWon(BingoCard player)
    {
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("SHOUT_BINGO"))
        {
            game.shoutBingo(getPlayer());
        }
    }

    @Override
    public void setBounds(int x, int y, int w, int h)
    {
        int size = Math.min(w, h);

        super.setBounds(x, y, size, size);
    }
}
