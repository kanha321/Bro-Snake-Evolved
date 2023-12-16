package highScores;

import utils.SnakePanel;

import javax.swing.*;

import static highScores.ValuesKt.getScoresTableModel;

public class ScoresPanel {
    private JPanel parent;
    private JTable scoresTable;
    private JButton button1;
    private JButton button2;

    public ScoresPanel() {

    }

    private void createUIComponents() {
        parent = new SnakePanel();
        scoresTable = new JTable(getScoresTableModel());
        parent.add(new JScrollPane(scoresTable));
    }

    public JPanel getParent() {
        return parent;
    }

    public JTable getScoresTable() {
        return scoresTable;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

}
