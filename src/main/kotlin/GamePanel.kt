import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.util.*
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.system.exitProcess


class GamePanel : JPanel(), ActionListener {
    val screenWidth = 800
    val screenHeight = 600
    val unitSize = 25
    val initialBodyParts = 5
    val gameUnits = screenHeight * screenWidth / unitSize * unitSize
    val delay = 15
    val x = IntArray(gameUnits)
    val y = IntArray(gameUnits)
    val bigAppleSize = 3
    val bigAppleUnitSize = bigAppleSize * unitSize
    val bigAppleFrequency = 0
    val bigAppleScore = 5
    val bigAppleBodyParts = 1
    val bigAppleTimer = 5000
    var bigAppleTimeLeft = bigAppleTimer
    val speed = 6 // MAX 7
    var speedCheck = 0
    var appleX = 0
    var appleY = 0
    var bigAppleX = 0
    var bigAppleY = 0
    var scoreCount = 0
    var appleAfterBigApple = 0
    var bodyParts = initialBodyParts
    var direction = 'r'
    var running = false
    var gameOver = false
    var stopMovement = false
    var timer: Timer? = null
    var random: Random? = null

    init {
        random = Random()
        val keyAd = MyKeyAdapter()
        this.preferredSize = Dimension(screenWidth, screenHeight)
        setBackground(Color.BLACK)
        setFocusable(true)
        addKeyListener(keyAd)
        startGame()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    fun draw(g: Graphics) {
        if (running) {
            if (showBigApple()) {
                bigAppleTimer(g)
            }
            g.color = Color.red
            g.fillOval(appleX, appleY, unitSize, unitSize)
            if (showBigApple()) {
                g.color = Color.yellow
                g.fillOval(bigAppleX, bigAppleY, bigAppleUnitSize, bigAppleUnitSize)
                bigAppleTimer(g)
            }
            for (i in 0..<bodyParts) {
                if (i == 0) {
                    g.color = Color.green
                } else {
                    g.color = Color(45, 180, 0)
                }
                g.fillRect(x[i], y[i], unitSize, unitSize)
            }
//            nokia(g);
        } else gameOver(g)
        displayScore(g)
    }

//    fun bigAppleTimer(g: Graphics) {
//        g.color = Color(210, 144, 0, 180)
//        g.font = Font("Monotype Corsiva", Font.PLAIN, 40)
//        g.drawString(bigAppleTimeLeft.toString() + "", screenWidth - 100, screenHeight - 30)
//    }


    fun bigAppleTimer(g: Graphics) {
        var i = 0
        while (i < screenWidth) {
            g.color = Color(210, 177, 0, 180)
            g.fillRect(i, screenHeight - unitSize, unitSize, unitSize)
            i += unitSize
        }
    }


    fun displayScore(g: Graphics) {
        g.color = Color(176, 176, 176, 180)
        g.font = Font("Monotype Corsiva", Font.PLAIN, 40)
        val metrics = getFontMetrics(g.font)
        g.drawString(
            "score: $scoreCount",
            (screenWidth - metrics.stringWidth("score: $scoreCount")) / 2,
            screenHeight - 30
        )
    }

    fun newApple() {
        appleX = random!!.nextInt(screenWidth / unitSize) * unitSize
        appleY = random!!.nextInt(screenHeight / unitSize) * unitSize
    }

    fun gameOver(g: Graphics) {
        gameOver = true
        g.color = Color.red
        g.font = Font("Ink Free", Font.BOLD, 69)
        val metrics = getFontMetrics(g.font)
        g.drawString("Game Over!!", (screenWidth - metrics.stringWidth("Game Over")) / 2, screenHeight / 2)
        restartPrompt(g)
    }

    fun restartPrompt(g: Graphics) {
        g.color = Color.green
        g.font = Font("Times New Roman", Font.PLAIN, 30)
        val metrics = getFontMetrics(g.font)
        g.drawString(
            "Press SPACE key to play again",
            (screenWidth - metrics.stringWidth("Press SPACE key to play again")) / 2,
            screenHeight - 100
        )
    }

    fun startGame() {
        newBigApple()
        newApple()
        running = true
        timer = Timer(delay, this)
        timer!!.start()
    }

    fun restartGame() {
        bodyParts = initialBodyParts
        scoreCount = 0
        appleAfterBigApple = 0
        stopMovement = false
        x[0] = 0
        y[0] = 0
        for (i in 1..<bodyParts) {
            x[i] = -1
            y[i] = -1
        }
        direction = 'r'
        gameOver = false
        startGame()
    }

    fun checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            newApple()
            bodyParts++
            scoreCount++
            if (!showBigApple()) appleAfterBigApple++
        }
        for (i in bodyParts downTo 1) {
            if (appleX == x[i] && appleY == y[i]) {
                newApple()
            }
        }
    }

    fun showBigApple(): Boolean {
        return appleAfterBigApple == bigAppleFrequency
    }


    fun newBigApple() {
        bigAppleX = random!!.nextInt(screenWidth / bigAppleUnitSize) * bigAppleUnitSize
        bigAppleY = random!!.nextInt(screenHeight / bigAppleUnitSize) * bigAppleUnitSize
        bigAppleTimeLeft = bigAppleTimer
        appleAfterBigApple = 0
    }

    fun checkBigApple() {
        var i = bigAppleX
        while (i < bigAppleX + bigAppleSize * unitSize) {
            var j = bigAppleY
            while (j < bigAppleY + bigAppleSize * unitSize) {
                if (x[0] == i && y[0] == j) {
                    newBigApple()
                    bodyParts += bigAppleBodyParts
                    scoreCount += bigAppleScore
                    appleAfterBigApple = 0
                }
                j += unitSize
            }
            i += unitSize
        }
    }

    fun checkCollision(){
        if (x[0] > screenWidth - unitSize) {
            x[0] = 0
        }
        if (x[0] < 0) {
            x[0] = screenWidth - unitSize
        }
        if (y[0] > screenHeight - unitSize) {
            y[0] = 0
        }
        if (y[0] < 0) {
            y[0] = screenHeight - unitSize
        }
        for (i in bodyParts downTo 1) {
            if (x[0] == x[i] && y[0] == y[i]) {
                stopMovement = true
            }
            if (!running) timer!!.stop()
        }
    }

    fun die() {
        bodyParts--
        if (bodyParts == 0) {
            gameOver = true
            running = false
            timer!!.stop()
        }
    }

    fun move() {
        for (i in bodyParts downTo 1) {
            x[i] = x[i - 1]
            y[i] = y[i - 1]
        }
        when (direction) {
            'u' -> y[0] = y[0] - unitSize
            'd' -> y[0] = y[0] + unitSize
            'l' -> x[0] = x[0] - unitSize
            'r' -> x[0] = x[0] + unitSize
        }
    }

    override fun actionPerformed(e: ActionEvent) {
        if (running) {
            if (speedCheck == 22 - speed * 3 || speedCheck == 0) {
                if (stopMovement) die()
                else move()
                speedCheck = 0;
            }
            speedCheck++;
            checkApple();
            if (showBigApple()) {
                checkBigApple();
                bigAppleTimeLeft -= delay;
                if (bigAppleTimeLeft <= 0) {
                    newBigApple();
                }
            }
            checkCollision();
        }
        repaint()
    }


    inner class MyKeyAdapter : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            when (e.keyCode) {
                KeyEvent.VK_W -> if (direction != 'd' && !stopMovement) direction = 'u'
                KeyEvent.VK_S -> if (direction != 'u' && !stopMovement) direction = 'd'
                KeyEvent.VK_A -> if (direction != 'r' && !stopMovement) direction = 'l'
                KeyEvent.VK_D -> if (direction != 'l' && !stopMovement) direction = 'r'

                KeyEvent.VK_SPACE -> {
                    if (!running) {
                        running = true
                        bodyParts = initialBodyParts
                        restartGame()
                    }
                }

                KeyEvent.VK_ESCAPE -> {
                    exitProcess(0)
                }

                KeyEvent.VK_P -> {
                    if (!gameOver) {
                        if (running) {
                            running = false
                            timer!!.stop()
                        } else {
                            running = true
                            timer!!.start()
                        }
                    }
                }

                KeyEvent.VK_F2 -> {
                    timer!!.stop()
                    restartGame()
                }

                KeyEvent.VK_F9 -> {
                    if (running) {
                        x[0] = appleX
                        y[0] = appleY
                        for (i in 1..<bodyParts) {
                            when (direction) {
                                'u' -> y[i] = appleY + unitSize
                                'd' -> y[i] = appleY - unitSize
                                'l' -> x[i] = appleX + unitSize
                                'r' -> x[i] = appleX - unitSize
                            }
                        }
                        checkApple()
                    }
                }
            }
        }
    }
}