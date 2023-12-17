package home

import custom.ImagePanel
import custom.TransparentButton
import game.logic.restartGame
import game.timer
import highScores.refreshScores
import panelManagement.PanelManager
import utils.SnakePanel
import javax.swing.JPanel
import kotlin.system.exitProcess

private val homePanel = HomePanel()
fun createHomePanel(gamePanel: game.Panel, scoresPanel: JPanel): HomePanel {
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
        refreshScores(true)
    }

    return homePanel
}