package gameOver

import game.Panel
import game.logic.restartGame
import game.timer
import panelManagement.PanelManager
import java.awt.event.KeyEvent

fun createGameOverPanel(): GameOverPanel {
    val gameOverPanel = GameOverPanel()

    gameOverPanel.panel.addKeyListener(
        object : java.awt.event.KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_SPACE -> {
                        PanelManager.switchToPreviousPanel()
                        restartGame()
                        timer!!.start()
                    }
                    KeyEvent.VK_ESCAPE -> {
                        PanelManager.switchToPreviousPanel()
                    }
                }
            }
        }
    )
    return gameOverPanel
}