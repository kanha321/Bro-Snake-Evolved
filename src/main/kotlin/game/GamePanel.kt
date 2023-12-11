package game

import DELAY
import INITIAL_BODY_PARTS
import SCREEN_HEIGHT
import SCREEN_WIDTH
import SPEED
import bodyParts
import checkFood
import checkCollision
import checkPowerUp
import die
import direction
import draw
import forceInit
import gameOver
import move
import newPowerUp
import powerUpTimeLeft
import restartGame
import running
import showPowerUp
import speedCheck
import startGame
import stopMovement
import timer
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel
import kotlin.system.exitProcess

class GamePanel : JPanel(), ActionListener {

    init {
        forceInit()
        val keyAd = Controls()
        this.preferredSize = Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)
        setBackground(Color(0))
        setFocusable(true)
        addKeyListener(keyAd)
        startGame(this)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    override fun actionPerformed(e: ActionEvent?) {
        if (running) {
            if (speedCheck == 22 - SPEED * 3 || speedCheck == 0) {
                if (stopMovement) die() else move()
                speedCheck = 0
            }
            speedCheck++
            checkFood()
            if (showPowerUp()) {
                checkPowerUp()
                if (!stopMovement)
                    powerUpTimeLeft -= DELAY
                if (powerUpTimeLeft <= 0)
                    newPowerUp()
            }
            checkCollision()
        }
        repaint()
    }

    inner class Controls: KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            when (e.keyCode) {
                KeyEvent.VK_W -> if (direction != 'd' && !stopMovement) direction = 'u'
                KeyEvent.VK_S -> if (direction != 'u' && !stopMovement) direction = 'd'
                KeyEvent.VK_A -> if (direction != 'r' && !stopMovement) direction = 'l'
                KeyEvent.VK_D -> if (direction != 'l' && !stopMovement) direction = 'r'

                KeyEvent.VK_UP -> if (direction != 'd' && !stopMovement) direction = 'u'
                KeyEvent.VK_DOWN -> if (direction != 'u' && !stopMovement) direction = 'd'
                KeyEvent.VK_LEFT -> if (direction != 'r' && !stopMovement) direction = 'l'
                KeyEvent.VK_RIGHT -> if (direction != 'l' && !stopMovement) direction = 'r'

                KeyEvent.VK_SPACE -> {
                    if (!running) {
                        running = true
                        bodyParts = INITIAL_BODY_PARTS
                        restartGame(this@GamePanel)
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
                    restartGame(this@GamePanel)
                }
            }
        }
    }
}