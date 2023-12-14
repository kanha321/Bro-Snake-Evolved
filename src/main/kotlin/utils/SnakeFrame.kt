package utils
import Home.HomePanel
import java.awt.CardLayout
import javax.swing.JFrame
import javax.swing.JPanel


class SnakeFrame : JFrame() {
    init {
        val container = JPanel(CardLayout()) // Create the container with CardLayout
        this.contentPane = container // Set the container as the content pane
        container.add(HomePanel().panel) // Add the HomePanel to the container

        this.title = "SnakeGame (powered by Kotlin)"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }
}