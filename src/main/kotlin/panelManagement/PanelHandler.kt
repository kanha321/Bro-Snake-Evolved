package panelManagement

import game.restartGame
import game.timer
import gameOver.GameOverPanel
import home.HomePanel
import panelManagement.PanelManager.switchToPreviousPanel
import java.awt.event.KeyEvent
import kotlin.system.exitProcess

fun createHomePanel(gamePanel: game.Panel): HomePanel {
    val homePanel = HomePanel()
    homePanel.startButton.addActionListener {
        PanelManager.switchPanel(gamePanel)
        restartGame()
        timer!!.start()
    }
    homePanel.exitButton.addActionListener {
        exitProcess(0)
    }

    homePanel.panel.addKeyListener(
        object : java.awt.event.KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_SPACE -> {
                        PanelManager.switchPanel(gamePanel)
                        restartGame()
                        timer!!.start()
                    }
                }
            }
        }
    )

    return homePanel
}

fun createGamePanel(gameOverPanel: GameOverPanel) = game.Panel(gameOverPanel)

fun createGameOverPanel(): GameOverPanel {
    val gameOverPanel = GameOverPanel()

    gameOverPanel.panel.addKeyListener(
        object : java.awt.event.KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_SPACE -> {
                        switchToPreviousPanel()
                        restartGame()
                        timer!!.start()
                    }
                    KeyEvent.VK_ESCAPE -> {
                        switchToPreviousPanel()
                    }
                }
            }
        }
    )
    return gameOverPanel
}

