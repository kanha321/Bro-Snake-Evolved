package highScores

import game.allScore

fun createScoresPanel(): ScoresPanel {
    val scoresPanel = ScoresPanel()
    if (allScore != null) {
        for (highScore in loadSortedHighScores()!!) {
            scoresTableModel.addScore(highScore)
        }
    }
    return scoresPanel
}