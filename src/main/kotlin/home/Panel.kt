package home

import game.restartGame
import utils.SCREEN_HEIGHT
import utils.SnakeFrame
import utils.SnakePanel
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.imageio.ImageIO
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import kotlin.system.exitProcess

class Panel(private val gameFrame: SnakeFrame) : SnakePanel(), ActionListener {


    private var btStartGame: JButton? = null
    private var btExitGame: JButton? = null

    private var myFont = Font("Comic Sans MS", Font.BOLD, 30)
    private var backgroundImage: Image = ImageIO.read(Panel::class.java.getResource("/HomeScreen/landscape_4k.jpeg"))

    init {

        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        add(Box.createVerticalGlue())

        btStartGame = JButton("Start Game")
        btStartGame!!.addActionListener(this)
        btStartGame!!.font = myFont
        btStartGame!!.background = Color(0)
        btStartGame!!.foreground = Color.WHITE
        btStartGame!!.alignmentX = CENTER_ALIGNMENT
        btStartGame!!.alignmentY = TOP_ALIGNMENT
        btStartGame!!.isFocusable = false

        btExitGame = JButton("Exit Game")
        btExitGame!!.addActionListener(this)
        btExitGame!!.font = myFont
        btExitGame!!.background = Color(0)
        btExitGame!!.foreground = Color.WHITE
        btExitGame!!.alignmentX = CENTER_ALIGNMENT
        btExitGame!!.alignmentY = TOP_ALIGNMENT
        btExitGame!!.isFocusable = false

        add(btStartGame)
        add(btExitGame)


        backgroundImage = backgroundImage.getScaledInstance(-1, SCREEN_HEIGHT, Image.SCALE_SMOOTH)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics){
        val imageWidth = backgroundImage.getWidth(null)
        val imageHeight = backgroundImage.getHeight(null)
        val x = (width - imageWidth) / 2
        val y = (height - imageHeight) / 2
        g.drawImage(backgroundImage, x, y, null)
    }

    override fun actionPerformed(e: ActionEvent?) {

//        if (e!!.source === btStartGame) {
//            gameFrame.switchToGame()
//            restartGame()
//        }
//        if (e!!.source === btExitGame) {
//            exitProcess(0)
//        }
    }
}