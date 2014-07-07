/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author ju39gox
 */
public class BingoGame implements ActionListener
{

    public static final Random RANDOM = new Random();
    public static final int BINGO_TURN_TIME = 5;

    private ArrayList<BingoListener> listeners;
    private BingoGameState gameState;
    private ArrayList<BingoCard> cards;

    private Timer turnTimer;

    private int turnTimeLeft;
    private int dicedNumber;

    public BingoGame()
    {
        listeners = new ArrayList<>();

        gameState = BingoGameState.NOT_STARTED;
        cards = new ArrayList<>();
        turnTimer = new Timer(1000, this);
        turnTimer.setActionCommand("TURN_SECOND_PASSED");
        turnTimeLeft = 0;
    }

    public void startGame()
    {
        if (gameState != BingoGameState.NOT_STARTED)
        {
            throw new BingoGameException("Game already started!");
        }

        gameState = BingoGameState.RUNNING;

        dice();
    }

    private void dice()
    {
        dicedNumber = RANDOM.nextInt(75) + 1;

        for (BingoListener listener : listeners)
        {
            listener.bingoDiced(dicedNumber);
        }

        turn();
    }

    public void shoutBingo(BingoCard card)
    {
        if (card.hasBingo())
        {
            playerWon(card);
        }
    }

    private void playerWon(BingoCard winner)
    {
        for (BingoListener listener : listeners)
        {
            listener.bingoWon(winner);
        }

        gameState = BingoGameState.FINISHED;
    }
    
    private void turn()
    {
        /**
         * Start timeout
         */
        turnTimeLeft = BINGO_TURN_TIME;
        checkTurnTimer();
    }
    
    /**
     * Führt Zug der KI aus
     */
    private void aiTurn()
    {       
        for (BingoCard player : cards)
        {
            if (!player.isHuman())
            {
                player.markCurrentNumber();
                shoutBingo(player);
            }
        }
    }

    private void checkTurnTimer()
    {
        if (gameState != BingoGameState.RUNNING)
        {
            return;
        }

        if (turnTimeLeft >= 0)
        {            
            for (BingoListener listener : listeners)
            {
                listener.bingoTurn(turnTimeLeft);
            }
            
            /**
             * KI soll in den letzten 2 Sekunden ankreuzen - simuliere 'nachdenken'
             */
            if(turnTimeLeft == 2)
            {
                aiTurn();
            }

            turnTimeLeft--;

            turnTimer.restart();
        }
        else
        {
            for (BingoListener listener : listeners)
            {
                listener.bingoTurnTimeout();
            }

            dice();
        }
    }

    public void addPlayer(String name, boolean isHuman)
    {
        if (gameState != BingoGameState.NOT_STARTED)
        {
            throw new RuntimeException("Game already started. Cannot add more players!");
        }

        if (getPlayer(name) != null)
        {
            throw new RuntimeException("Player already existing!");
        }

        cards.add(new BingoCard(name, isHuman, this));
    }

    public void addBingoListener(BingoListener listener)
    {
        listeners.add(listener);
    }

    public void removeBingoListener(BingoListener listener)
    {
        listeners.remove(listener);
    }

    public BingoCard getPlayer(String name)
    {
        for (BingoCard player : cards)
        {
            if (player.getName().equals(name))
            {
                return player;
            }
        }

        return null;
    }

    public ArrayList<BingoCard> getPlayers()
    {
        return (ArrayList<BingoCard>) cards.clone();
    }

    public int getCurrentNumber()
    {
        return dicedNumber;
    }

    public BingoGameState getState()
    {
        return gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("TURN_SECOND_PASSED"))
        {
            checkTurnTimer();
        }
    }
}
