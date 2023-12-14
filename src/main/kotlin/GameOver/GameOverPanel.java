package GameOver;

import custom.GameOverBackgroundPanel;

import javax.swing.*;

public class GameOverPanel {
    private JPanel panel1;

    public GameOverPanel() {}

    private void createUIComponents() {
        panel1 = new GameOverBackgroundPanel();
    }

    public JPanel getPanel(){
        return panel1;
    }
}
