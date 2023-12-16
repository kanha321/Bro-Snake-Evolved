package highScores

import panelManagement.PanelManager.switchToPreviousPanel


fun createScoresPanel(highScores: List<HighScoreData>): ScoresPanel {
    val scoresPanel = ScoresPanel()
    if (highScores.isNotEmpty()) {
        for (highScore in highScores) {
            scoresTableModel.addScore(highScore)
        }
    }
    scoresPanel.button1.addActionListener {
        refreshScores(sort = true)
    }
    scoresPanel.button2.addActionListener {
        refreshScores()
    }
    return scoresPanel
}

