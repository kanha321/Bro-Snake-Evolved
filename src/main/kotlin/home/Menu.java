package home;

import customs.ImagePanel;
import customs.TransparentButton;
import game.Panel;
import game.ValuesKt;
import utils.SnakePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static game.FunctionalityKt.restartGame;
import static utils.ValuesKt.*;

public class Menu extends JFrame{
    private JPanel panel1;
    private JButton settingsButton;
    private JButton exitGameButton;
    private JButton startGameButton;
    private JButton highScoresButton;
    private CardLayout cardLayout;
    private SnakePanel cardPanel;

    public Menu(){
        cardLayout = new CardLayout();
        cardPanel = new SnakePanel(cardLayout);
        cardPanel.add(panel1, "Home");
        cardPanel.add(new Panel(cardLayout, cardPanel), "NewForm");

        this.setContentPane(cardPanel);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setTitle("SnakeGame");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        startGameButton.addActionListener(e -> {
            // Switch to the NewForm panel
            if (!Objects.requireNonNull(ValuesKt.getTimer()).isRunning()) {
                ValuesKt.getTimer().start();
            }
            restartGame();
            cardLayout.show(cardPanel, "NewForm");
            cardPanel.getComponent(1).requestFocusInWindow();
        });
        exitGameButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void createUIComponents() {
        panel1 = new ImagePanel(SCREEN_WIDTH, SCREEN_HEIGHT);
        startGameButton = new TransparentButton("Start Game", 0, 132, 0);
        highScoresButton = new TransparentButton("High Scores");
        settingsButton = new TransparentButton("Settings");
        exitGameButton = new TransparentButton("Exit Game", 132, 0, 0);
    }
}