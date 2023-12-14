package Home;

import custom.ImagePanel;
import custom.TransparentButton;
import utils.SnakePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.ValuesKt.*;

public class HomePanel {

    public JButton button1;
    private JPanel imagePanel;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panel2;

    public HomePanel() {
        panel1.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        button1.addActionListener(e -> {
            switchPanel(panel1, new Game.Panel());
        });
        button4.addActionListener(e -> System.exit(0));
    }

    private void createUIComponents() {
        panel2 = new ImagePanel(SCREEN_WIDTH, SCREEN_HEIGHT);
        button1 = new TransparentButton("Play", 0, 132, 0);
        button2 = new TransparentButton("High Scores");
        button3 = new TransparentButton("Settings");
        button4 = new TransparentButton("Exit", 132, 0, 0);
    }
    public JPanel getPanel(){
        return panel1;
    }
}
