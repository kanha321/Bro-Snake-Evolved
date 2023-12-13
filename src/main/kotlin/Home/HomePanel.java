package Home;

import custom.ImagePanel;
import utils.SnakePanel;
import javax.swing.*;
import static utils.ValuesKt.*;

public class HomePanel extends SnakePanel {

    public JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel imagePanel;

    public HomePanel() {
        button1.setText("Goto MyPanel");
    }

    private void createUIComponents() {
        imagePanel = new ImagePanel(SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
