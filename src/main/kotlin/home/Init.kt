package home

import game.logic.restartGame
import game.timer
import highScores.ScoresPanel
import panelManagement.PanelManager
import java.util.stream.IntStream.range
import javax.swing.JPanel
import kotlin.system.exitProcess

fun createHomePanel(gamePanel: game.Panel, scoresPanel: JPanel): HomePanel {
    val homePanel = HomePanel()
    homePanel.startButton.addActionListener {
        PanelManager.switchPanel(gamePanel)
        restartGame()
        timer!!.start()
    }
    homePanel.exitButton.addActionListener {
        exitProcess(0)
    }
    homePanel.highScoreButton.addActionListener {
        PanelManager.switchPanel(scoresPanel)
    }

    return homePanel
}