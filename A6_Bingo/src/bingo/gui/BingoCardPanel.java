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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ju39gox
 */
public class BingoCardPanel extends JPanel implements BingoListener
{

    private BingoGame game;
    private String playerName;
    private BingoCardNumberButton[][] uiBingoFields;
    private JButton uiBingoFinishTurn;

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
            JLabel labelTop = new JLabel("" + "BINGO".charAt(i - 1));
            c.gridx = i;
            c.gridy = 0;
            this.add(labelTop, c);

            JLabel labelBottom = new JLabel("" + "BINGO".charAt(i - 1));
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
        
        //Add 'finished' button
        uiBingoFinishTurn = new JButton("Fertig!");
        c.weightx = 0;
        c.weighty = 1;
        c.gridx= 0;
        c.gridy = 0;
      
        add(uiBingoFinishTurn, c);
    }

    @Override
    public void bingoDiced(int number)
    {       
    }

    @Override
    public void bingoPlayerTurn(BingoCard player)
    {
        if(player.getName().equals(playerName))
        {
            this.setEnabled(true);
            
            if(player.isHuman())
            {
                uiBingoFinishTurn.setVisible(true);
            }
            else
            {
                uiBingoFinishTurn.setVisible(false);
            }
        }      
        else
        {
            this.setEnabled(false);
            uiBingoFinishTurn.setVisible(false);
        }
        
    }

    @Override
    public void bingoWon(BingoCard player)
    {
        this.setEnabled(false);
    }
}
