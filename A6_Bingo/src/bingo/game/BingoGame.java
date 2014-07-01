/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author ju39gox
 */
public class BingoGame
{
    public static final Random RANDOM = new Random();

    private ArrayList<BingoListener> listeners;
    private ArrayList<Integer> existingCardFieldNumbers;
    private BingoGameState gameState;
    private ArrayList<BingoCard> cards;
    private LinkedList<BingoCard> currentRoundPlayers;
    private int currentNumber;

    public BingoGame()
    {
        listeners = new ArrayList<>();

        gameState = BingoGameState.NOT_STARTED;
        cards = new ArrayList<>();
        currentRoundPlayers = new LinkedList<>();
        existingCardFieldNumbers = new ArrayList<>();
    }

    public void startGame()
    {
        if (gameState != BingoGameState.NOT_STARTED)
        {
            throw new RuntimeException("Game already started!");
        }

        gameState = BingoGameState.RUNNING;

        dice();
    }

    private void dice()
    {
        currentRoundPlayers.clear();

        currentNumber = RANDOM.nextInt(75) + 1;
        currentRoundPlayers.addAll(cards);

        for (BingoListener listener : listeners)
        {
            listener.bingoDiced(currentNumber);
        }

        nextPlayer();
    }

    private boolean checkIfPlayerWon()
    {
        //Look for player, who won
        for (BingoCard card : currentRoundPlayers)
        {
            if (card.hasBingo())
            {
                for (BingoListener listener : listeners)
                {
                    listener.bingoWon(card);
                }
                
                gameState = BingoGameState.FINISHED;

                return true;
            }
        }

        return false;
    }

    private void nextPlayer()
    {
        if (currentRoundPlayers.isEmpty())
        {
            dice();
        }
        else
        {
            //Gibt es Bingo?
            if(checkIfPlayerWon())
            {
                return;
            }

            //Kein Bingo
            BingoCard player = currentRoundPlayers.getFirst();

            if (!player.isHuman())
            {
                player.markAll();
                currentRoundPlayers.remove(player);

                nextPlayer();
            }
            else
            {
                for (BingoListener listener : listeners)
                {
                    listener.bingoPlayerTurn(player);
                }
            }
        }
    }

    public void addPlayer(String name, boolean isHuman)
    {
        if (gameState != BingoGameState.NOT_STARTED)
        {
            throw new RuntimeException("Game already started. Cannot add more players!");
        }
        
        if(getPlayer(name) != null)
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
        listeners.add(listener);
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
        return currentNumber;
    }

    public BingoCard getCurrentPlayer()
    {
        if(currentRoundPlayers.isEmpty())
            return null;
        
        return currentRoundPlayers.getFirst();
    }
    
    public int getCardFieldNumber()
    {
        int random = 0;
        
        do
        {
            random = 1 + RANDOM.nextInt(75);
        }
        while(existingCardFieldNumbers.contains(random));
        
        existingCardFieldNumbers.add(random);
        
        return random;
    }
}