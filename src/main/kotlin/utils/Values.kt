package utils

import game.allScore
import game.highScore
import game.highScoreTime
import highScores.getHighScore
import highScores.loadHighScores
import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 600
const val JSON_SETTINGS = "gameSettings.json"
const val JSON_HIGHSCORES = "highScores.json"
const val JSON_DIR = "SnakeKt"

fun getImage(fileName: String): Image {
    return ImageIO.read({}.javaClass.getResource(fileName))
}

fun getResourceFile(filePath: String) = {}.javaClass.getResource(filePath)?.file
fun createBlackImage(width: Int, height: Int): BufferedImage {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val graphics = image.createGraphics()
    graphics.color = Color.BLACK
    graphics.fillRect(0, 0, width, height)
    graphics.dispose()
    return image
}

fun loadJSONData() {
    highScore = getHighScore().highScore
    highScoreTime = getHighScore().time
    allScore = loadHighScores()
}

fun init() {
    SnakeFrame()
    Thread{
        loadJSONData()
    }.start()
}