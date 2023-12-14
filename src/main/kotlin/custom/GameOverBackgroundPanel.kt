package custom

import Game.draw
import utils.SnakePanel
import java.awt.Graphics

class GameOverBackgroundPanel : SnakePanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        Game.gameOver(g)
        Game.restartPrompt(g)
        Game.displayScore(g)
    }

}