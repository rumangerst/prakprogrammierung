/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.gui;

import bingo.game.BingoCard;
import bingo.game.BingoGame;
import bingo.game.BingoGameState;
import bingo.game.BingoListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.LineMetrics;
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
    public void bingoTurn(int secondsLeft)
    {
        this.setEnabled(getPlayer().isHuman());
        repaint();
    }

    @Override
    public void bingoTurnTimeout()
    {
        this.setEnabled(false);
        repaint();
    }

    @Override
    public void bingoWon(BingoCard player)
    {
        this.setEnabled(false);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("MARK"))
        {
            getPlayer().markCurrentNumberAt(bingoX, bingoY);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        boolean visibleNumbers = getPlayer().isHuman() || game.getState() == BingoGameState.FINISHED;
     
        /**
         * Draw text
         */
        String number = "?";
        if (visibleNumbers)
        {
            number = getPlayer().getValueAt(bingoX, bingoY) + "";
        }

        g2.setColor(Color.GRAY);

        Font numberFont = new Font(Font.DIALOG, Font.PLAIN, Math.min(getWidth(), getHeight()) / 2);
        FontMetrics metrics = g2.getFontMetrics(numberFont);

        g2.setFont(numberFont);
        g2.drawString(number, getWidth() / 2 - metrics.stringWidth(number) / 2, getHeight() / 2 + metrics.getAscent() / 2);

        /**
         * Draw cross/mark
         */
        if (visibleNumbers)
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
