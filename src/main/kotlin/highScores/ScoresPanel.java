package highScores;

import utils.SnakePanel;

import javax.swing.*;

import static highScores.ValuesKt.getScoresTableModel;

public class ScoresPanel {
    private JPanel parent;
    private JTable scoresTable;

    public ScoresPanel() {}

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

}
