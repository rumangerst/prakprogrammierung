/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.game;

/**
 *
 * @author ju39gox
 */
public interface BingoListener
{
    public void bingoDiced(int number);
    public void bingoPlayerTurn(BingoCard player);
    public void bingoWon(BingoCard player);
}
