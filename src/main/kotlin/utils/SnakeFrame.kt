package utils
import com.google.gson.JsonSyntaxException
import gameOver.*
import highScores.*
import home.*
import panelManagement.PanelManager
import java.io.File
import javax.swing.JFrame
import kotlin.system.exitProcess


class SnakeFrame : JFrame() {
    private var homePanel: HomePanel
    private var gamePanel: game.Panel
    private var gameOverPanel: GameOverPanel = createGameOverPanel()
    private var scoresPanel: ScoresPanel

    init {
        var highScores: List<HighScoreData>
        try {
            highScores = loadHighScores()!!.sortedByDescending { it.highScore }.take(10)
        } catch (e :NullPointerException){
            deleteFile()
            highScores = mutableListOf()
        } catch (e: JsonSyntaxException) {
            System.err.println("JSON file is corrupted..")
            System.err.println("Recreation needed... (deletes all the scores, if present)")
            System.err.println("Recreate? (Y/N)")
            if (takeYNInput(asErr = true)) {
                deleteFile()
                highScores = mutableListOf()
            } else {
                System.err.println("Exiting..")
                exitProcess(0)
            }
            deleteFile()
        }
        gamePanel = game.Panel(gameOverPanel)
        scoresPanel = createScoresPanel(highScores)
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