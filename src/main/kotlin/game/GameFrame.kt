import javax.swing.JFrame

class GameFrame : JFrame(){
    init {
        val panel = GamePanel()
        this.add(panel)
        this.title = "SnakeGame"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }
}