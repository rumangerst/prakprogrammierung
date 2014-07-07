/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.game;

import static bingo.game.BingoGame.RANDOM;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ju39gox
 */
public class BingoCard
{

    private ArrayList<Integer> existingCardFieldNumbers;
    private BingoGame game;
    private String name;
    private boolean isHuman;
    private int[][] field;
    private boolean[][] strikes;

    public BingoCard(String name, boolean isHuman, BingoGame game)
    {
        this.existingCardFieldNumbers = new ArrayList<>();
        this.game = game;
        this.isHuman = isHuman;
        this.name = name;
        this.field = new int[5][5];
        this.strikes = new boolean[5][5];

        //Create random field
        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                field[x][y] = getCardFieldNumber();
                strikes[x][y] = false;
            }
        }

        /**
         * Testfunktion für 'Bingo' button
         */
        /*
         if(isHuman)
         {
         for(int x = 1; x < 4; x++)
         {
         for (int y = 1; y < 4;y++)
         {
         strikes[x][y] = true;
         }
         }
         }
         */
    }

    public boolean isHuman()
    {
        return isHuman;
    }

    public String getName()
    {
        return name;
    }

    public int getValueAt(int x, int y)
    {
        return field[x][y];
    }

    public boolean isMarked(int x, int y)
    {
        return strikes[x][y];
    }

    public boolean unmarkedNumbers()
    {
        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                if (getValueAt(x, y) == game.getCurrentNumber())
                {
                    if (!strikes[x][y])
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void markCurrentNumber()
    {
        if (game.getState() != BingoGameState.RUNNING)
        {
            throw new InvalidPlayerActionException("Game is not running!");
        }

        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                if (getValueAt(x, y) == game.getCurrentNumber())
                {
                    strikes[x][y] = true;
                }
            }
        }
    }

    public void markCurrentNumberAt(int x, int y)
    {
        if (game.getState() != BingoGameState.RUNNING)
        {
            throw new InvalidPlayerActionException("Game is not running!");
        }

        if (getValueAt(x, y) == game.getCurrentNumber())
        {
            strikes[x][y] = true;
        }
    }

    public boolean hasBingo()
    {
        //Prüfe Horizontale
        for (int y = 0; y < 5; y++)
        {
            boolean row = true;

            for (int x = 0; x < 5; x++)
            {
                row &= isMarked(x, y);

                if (!row)
                {
                    break;
                }
            }

            if (row)
            {
                return true;
            }
        }

        //Prüfe Vertikale
        for (int x = 0; x < 5; x++)
        {
            boolean row = true;

            for (int y = 0; y < 5; y++)
            {
                row &= isMarked(x, y);

                if (!row)
                {
                    break;
                }
            }

            if (row)
            {
                return true;
            }
        }
        //Prüfe Diagonale 1
        {
            boolean row = true;
            for (int xy = 0; xy < 5; xy++)
            {

                row &= isMarked(xy, xy);

                if (!row)
                {
                    break;
                }

            }
            if (row)
            {
                return true;
            }
        }
        //Prüfe Diagonale 2
        {
            boolean row = true;
            for (int i = 0; i < 5; i++)
            {

                row &= isMarked(i, i);

                if (!row)
                {
                    break;
                }

            }
            if (row)
            {
                return true;
            }
        }

        return false;
    }

    private int getCardFieldNumber()
    {
        int random = 0;

        do
        {
            random = 1 + RANDOM.nextInt(75);
        }
        while (existingCardFieldNumbers.contains(random));

        existingCardFieldNumbers.add(random);

        return random;
    }
}
