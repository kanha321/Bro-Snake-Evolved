package utils
import gameOver.GameOverPanel
import home.HomePanel
import panelManagement.PanelManager
import panelManagement.createGameOverPanel
import panelManagement.createGamePanel
import panelManagement.createHomePanel
import javax.swing.JFrame


class SnakeFrame : JFrame() {
    private var homePanel: HomePanel
    private var gamePanel: game.Panel
    private var gameOverPanel: GameOverPanel = createGameOverPanel()

    init {
        gamePanel = createGamePanel(gameOverPanel)
        homePanel = createHomePanel(gamePanel)
        PanelManager.addPanel(homePanel.panel, "HomePanel")
        PanelManager.addPanel(gamePanel, "GamePanel")
        PanelManager.addPanel(gameOverPanel.panel, "GameOverPanel")

        PanelManager.switchPanel(homePanel.panel)
//        homePanel.panel.requestFocusInWindow()

        this.contentPane = PanelManager.getCardPanel()
        this.title = "SnakeGame (powered by Kotlin)"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }
}