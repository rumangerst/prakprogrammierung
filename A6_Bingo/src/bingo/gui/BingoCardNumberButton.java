/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.gui;

import bingo.game.BingoCard;
import bingo.game.BingoGame;
import bingo.game.BingoListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author ju39gox
 */
public class BingoCardNumberButton extends JButton implements BingoListener, ActionListener
{

    private BingoGame game;
    private String playerName;
    private int bingoX;
    private int bingoY;

    public BingoCardNumberButton(BingoGame game, String playerName, int x, int y)
    {
        this.game = game;
        this.playerName = playerName;
        this.bingoX = x;
        this.bingoY = y;
        
        game.addBingoListener(this);

        this.setEnabled(false);
        this.addActionListener(this);

        this.setActionCommand("MARK");
    }

    private BingoCard getPlayer()
    {
        return game.getPlayer(playerName);
    }

    @Override
    public void bingoDiced(int number)
    {
    }

    @Override
    public void bingoPlayerTurn(BingoCard player)
    {
        this.setEnabled(player.isHuman() && player == getPlayer());
    }

    @Override
    public void bingoWon(BingoCard player)
    {
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("MARK"))
        {
            getPlayer().mark(bingoX, bingoY);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        /**
         * Draw text
         */
        int fontsize = Math.min(getWidth(), getHeight()) / 2;
        String number = "?";
        if (getPlayer().isHuman())
        {
            number = getPlayer().getValueAt(bingoX, bingoY) + "";
        }

        g2.setColor(Color.GRAY);
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, fontsize));
        g2.drawString(number, getWidth() / 4, (int) (getHeight() / 1.5));

        
        /**
         * Draw cross/mark
         */
        if (getPlayer().isHuman())
        {
            if (getPlayer().isMarked(bingoX, bingoY))
            {
                g2.setColor(Color.red);
                g2.setStroke(new BasicStroke(7));

                g2.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
                g2.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
            }
        }
    }
}
