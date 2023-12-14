package panelManagement

import Game.restartGame
import Game.startGame
import Game.timer
import Home.HomePanel
import kotlin.system.exitProcess

fun createHomePanel(gamePanel: Game.Panel): HomePanel {
    val homePanel = HomePanel()
    homePanel.startButton.addActionListener {
        PanelManager.switchPanel(gamePanel)
        restartGame()
        timer!!.start()
        gamePanel.requestFocusInWindow()
    }
    homePanel.exitButton.addActionListener {
        exitProcess(0)
    }
    return homePanel
}
fun createGamePanel() = Game.Panel()

