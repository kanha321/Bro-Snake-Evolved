package utils
import gameOver.GameOverPanel
import gameOver.createGameOverPanel
import highScores.createScoresPanel
import home.HomePanel
import home.createHomePanel
import panelManagement.PanelManager
import javax.swing.JFrame


class SnakeFrame : JFrame() {
    private var homePanel: HomePanel
    private var gamePanel: game.Panel
    private var gameOverPanel: GameOverPanel = createGameOverPanel()
    private var scoresPanel: highScores.ScoresPanel

    init {
        gamePanel = game.Panel(gameOverPanel)
        scoresPanel = createScoresPanel()
        homePanel = createHomePanel(gamePanel, scoresPanel.parent)
        PanelManager.addPanel(homePanel.panel, "HomePanel")
        PanelManager.addPanel(gamePanel, "GamePanel")
        PanelManager.addPanel(gameOverPanel.panel, "GameOverPanel")
        PanelManager.addPanel(scoresPanel.parent, "ScoresPanel")

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