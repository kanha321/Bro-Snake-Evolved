package utils
import game.timer
import javax.swing.JFrame

class SnakeFrame : JFrame() {
//    private val gameScreen = game.Panel(this)
    private val homeScreen = home.Panel(this)

    init {
        this.contentPane = homeScreen
        this.title = "SnakeGame (powered by Kotlin)"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }

//    fun switchToGame() {
//        this.remove(homeScreen)
//        this.contentPane = gameScreen
//        if (!timer!!.isRunning) timer!!.start()
//        this.revalidate()
//        this.repaint()
//        gameScreen.requestFocusInWindow()
//    }
//    fun switchToHome() {
//        this.remove(gameScreen)
//        this.contentPane = homeScreen
//        this.revalidate()
//        this.repaint()
//        homeScreen.requestFocusInWindow()
//    }
}