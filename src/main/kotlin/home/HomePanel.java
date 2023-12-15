package home;

import custom.ImagePanel;
import custom.TransparentButton;
import utils.SnakePanel;

import javax.swing.*;

import static utils.ValuesKt.*;

public class HomePanel {

    public JButton startButton;
    private JPanel imagePanel;
    private JPanel panel1;
    private JButton highScoreButton;
    private JButton settingsButton;
    private JButton exitButton;
    private JPanel panel2;

    public HomePanel() {}

    private void createUIComponents() {
        panel1 = new SnakePanel();
        panel2 = new ImagePanel(SCREEN_WIDTH, SCREEN_HEIGHT);
        startButton = new TransparentButton("Play", 0, 132, 0);
        highScoreButton = new TransparentButton("High Scores");
        settingsButton = new TransparentButton("Settings");
        exitButton = new TransparentButton("Exit", 132, 0, 0);
    }
    public JPanel getPanel(){
        return panel1;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getHighScoreButton() {
        return highScoreButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
