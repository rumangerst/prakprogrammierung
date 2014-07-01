/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.gui;

import bingo.game.BingoCard;
import bingo.game.BingoGame;
import bingo.game.BingoListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ju39gox
 */
public class MainWindow extends JFrame implements BingoListener
{

    private BingoGame currentGame;
    private HashMap<String, BingoCardPanel> uiPlayerCards;
    
    private JPanel uiBingoCardContainer;
    private JLabel uiGameStatusLabel;

    public MainWindow()
    {
        uiPlayerCards = new HashMap<>();

        initializeComponents();
        newGame();
    }

    private void initializeComponents()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Bingo");
        
        this.setLayout(new BorderLayout());
        
        uiGameStatusLabel = new JLabel("Ready.");
        uiGameStatusLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        add(uiGameStatusLabel, BorderLayout.NORTH);
        
        uiBingoCardContainer = new JPanel(new FlowLayout());
        add(uiBingoCardContainer, BorderLayout.CENTER);
    }

    public void newGame()
    {
        uiBingoCardContainer.removeAll();

        currentGame = new BingoGame();
        currentGame.addBingoListener(this);
        
        addPlayer("Horst", true);
        addPlayer("GlaDOS", false);
        
        currentGame.startGame();

    }

    private void addPlayer(String name, boolean isHuman)
    {
        currentGame.addPlayer(name, isHuman);

        BingoCardPanel card = new BingoCardPanel(currentGame, name);
        card.setPreferredSize(new Dimension(400, 400));
        uiBingoCardContainer.add(card);

        uiPlayerCards.put(name, card);
    }

    @Override
    public void bingoDiced(int number)
    {
        uiGameStatusLabel.setText("Es wurde eine " + number  + " gewürfelt!");
    }

    @Override
    public void bingoPlayerTurn(BingoCard player)
    {
    }

    @Override
    public void bingoWon(BingoCard player)
    {
       uiGameStatusLabel.setText("BINGO! " + player.getName() + " hat gewonnen!");
    }
}
