package utils
import Home.HomePanel
import panelManagement.PanelManager
import panelManagement.createGamePanel
import panelManagement.createHomePanel
import java.awt.CardLayout
import javax.swing.JFrame
import javax.swing.JPanel


class SnakeFrame : JFrame() {
    private var gamePanel = createGamePanel()
    private var homePanel: HomePanel = createHomePanel(gamePanel)

    init {
        PanelManager.addPanel(homePanel.panel, "HomePanel")
        PanelManager.addPanel(gamePanel, "GamePanel")

        PanelManager.switchPanel(homePanel.panel)

        this.contentPane = PanelManager.getCardPanel()
        this.title = "SnakeGame (powered by Kotlin)"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }
}