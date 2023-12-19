package game

import game.logic.*
import gameOver.GameOverPanel
import utils.SnakePanel
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

class Panel(private val gameOverPanel: GameOverPanel) : SnakePanel(), ActionListener {

    private val url = {}.javaClass.getResource("/Music/jungle1.wav")
    private val jungle1 = AudioSystem.getAudioInputStream(url)
    private val clip = AudioSystem.getClip()

    init {
        clip.open(jungle1)
        clip.loop(Clip.LOOP_CONTINUOUSLY)

        forceInit()
//        running = true
        val keyAd = Controls()
        isFocusable = true
        addKeyListener(keyAd)
        startGame(this)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    fun startSound() {
        clip.start()
    }

    fun stopSound() {
        clip.stop()
    }

    override fun actionPerformed(e: ActionEvent?) {
        if (running) {
            if (speedCheck == 22 - SPEED * 3 || speedCheck == 0) {
                if (isDead) die(gameOverPanel, this) else move()
                speedCheck = 0
            }
            if (isDead) clip.stop()
            speedCheck++
            checkFood()
            if (showPowerUp()) {
                checkPowerUp()
                if (!isDead)
                    powerUpTimeLeft -= DELAY
                if (powerUpTimeLeft <= 0) {
                    appleAfterPowerUp = 0
                    newPowerUp()
                }
            }
            checkCollision()
            repaint()
        }
    }

    inner class Controls : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            when (e.keyCode) {
                KeyEvent.VK_W -> if (direction != 'd' && !isDead) direction = 'u'
                KeyEvent.VK_S -> if (direction != 'u' && !isDead) direction = 'd'
                KeyEvent.VK_A -> if (direction != 'r' && !isDead) direction = 'l'
                KeyEvent.VK_D -> if (direction != 'l' && !isDead) direction = 'r'

                KeyEvent.VK_UP -> if (direction != 'd' && !isDead) direction = 'u'
                KeyEvent.VK_DOWN -> if (direction != 'u' && !isDead) direction = 'd'
                KeyEvent.VK_LEFT -> if (direction != 'r' && !isDead) direction = 'l'
                KeyEvent.VK_RIGHT -> if (direction != 'l' && !isDead) direction = 'r'

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

                KeyEvent.VK_F9 -> {
                    isDead = true
                }
            }
        }
    }
}