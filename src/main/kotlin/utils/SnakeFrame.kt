package utils
import javax.swing.JFrame

class SnakeFrame : JFrame() {
    private val gameScreen = Game.Panel(this)
    private val homeScreen = Home.Panel(this)
    private val gameOverScreen = GameOver.Panel(this)

    init {
        this.add(homeScreen)
        this.title = "SnakeGame (powered by Kotlin)"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }

    fun switchToGame() {
        this.remove(homeScreen)
        this.add(gameScreen)
        this.revalidate()
        this.repaint()
        gameScreen.requestFocusInWindow()
    }
    fun switchToHome() {
        this.remove(gameScreen)
        this.add(homeScreen)
        this.revalidate()
        this.repaint()
        homeScreen.requestFocusInWindow()
    }
}