package game

import javax.swing.JFrame

class GameFrame : JFrame(){
    init {
        val panel = GamePanel2()
        this.add(panel)
        this.title = "SnakeGame (powered by Kotlin)"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }
}