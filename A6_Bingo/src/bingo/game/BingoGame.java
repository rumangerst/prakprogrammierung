/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ju39gox
 */
public class BingoGame
{
    private ArrayList<BingoListener> listeners;
    
    private boolean gameStarted;
    private ArrayList<BingoCard> cards;
    
    private LinkedList<BingoCard> currentRoundPlayers;
    private int currentNumber;
    
    public BingoGame()
    {
        listeners = new ArrayList<>();
        
        gameStarted = false;
        cards = new ArrayList<>();
        currentRoundPlayers = new LinkedList<>();
    }
    
    public void startGame()
    {
        gameStarted = true;
        
        dice();
    }
    
    private void dice()
    {
        currentNumber = BingoCard.RANDOM.nextInt(75) + 1;        
        currentRoundPlayers.addAll(cards);
        
        for(BingoListener listener : listeners)
        {
            listener.bingoDiced(currentNumber);
        }
        
        playerTurn();
    }
    
    private void playerTurn()
    {
        if(currentRoundPlayers.isEmpty())
        {
            dice();
        }
        else
        {
            if(cur)
        }
    }
    
    public void addPlayer(String name, boolean isHuman)
    {
        if(gameStarted)
            throw new RuntimeException("Game already started. Cannot add more players!");
        
        cards.add(new BingoCard(name, isHuman));
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
        for(BingoCard player : cards)
        {
            if(player.getName().equals(name))
                return player;
        }
        
        return null;
    }
    
    public ArrayList<BingoCard> getPlayers()
    {
        return (ArrayList<BingoCard>)cards.clone();
    }
    
    public int getCurrentNumber()
    {
        return currentNumber;
    }
    
    public BingoCard getCurrentPlayer()
    {
        return currentRoundPlayers.getFirst();
    }
}
