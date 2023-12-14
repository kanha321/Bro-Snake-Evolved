package utils
import GameOver.GameOverPanel
import Home.HomePanel
import panelManagement.PanelManager
import panelManagement.createGameOverPanel
import panelManagement.createGamePanel
import panelManagement.createHomePanel
import java.awt.CardLayout
import javax.swing.JFrame
import javax.swing.JPanel


class SnakeFrame : JFrame() {
    private var homePanel: HomePanel
    private var gamePanel: Game.Panel
    private var gameOverPanel: GameOverPanel = createGameOverPanel()

    init {
        gamePanel = createGamePanel(gameOverPanel)
        homePanel = createHomePanel(gamePanel)
        PanelManager.addPanel(homePanel.panel, "HomePanel")
        PanelManager.addPanel(gamePanel, "GamePanel")
        PanelManager.addPanel(gameOverPanel.panel, "GameOverPanel")

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