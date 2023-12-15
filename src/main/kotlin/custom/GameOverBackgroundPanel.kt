package custom

import game.*
import utils.SnakePanel
import java.awt.Graphics

class GameOverBackgroundPanel : SnakePanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        gameOver(g)
        restartPrompt(g)
        displayScore(g)
        displayHighScore(g)
    }

}