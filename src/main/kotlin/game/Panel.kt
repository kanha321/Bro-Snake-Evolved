package game

import utils.*
import java.awt.CardLayout
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class Panel(
    private var cardLayout: CardLayout,
    private var cardPanel: SnakePanel
) : SnakePanel(), ActionListener {

    init {
        forceInit()
        val keyAd = Controls()
        isFocusable = true
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

    inner class Controls : KeyAdapter() {
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
                        restartGame()
                        startGame(this@Panel)
                    }
                }

                KeyEvent.VK_ESCAPE -> {
                    running = false
                    timer!!.stop()
                    cardLayout.show(cardPanel, "Home")
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
                    startGame(this@Panel)
                }
            }
        }
    }
}