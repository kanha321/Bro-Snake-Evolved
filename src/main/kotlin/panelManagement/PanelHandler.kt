package panelManagement

import Game.restartGame
import Game.timer
import GameOver.GameOverPanel
import Home.HomePanel
import java.awt.event.KeyEvent
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

fun createGamePanel(gameOverPanel: GameOverPanel) = Game.Panel(gameOverPanel)

fun createGameOverPanel(): GameOverPanel {
    val gameOverPanel = GameOverPanel()

    gameOverPanel.panel.addKeyListener(
        object : java.awt.event.KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_SPACE -> {
                        PanelManager.switchToPreviousPanel()
                        PanelManager.getCurrentPanel()!!.requestFocusInWindow()
                        restartGame()
                        timer!!.start()
                    }
                }
            }
        }
    )
    return gameOverPanel
}

