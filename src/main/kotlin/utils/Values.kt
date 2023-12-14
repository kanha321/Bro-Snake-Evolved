package utils

import Game.Panel
import java.awt.CardLayout
import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JPanel

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 600

fun getImage(fileName: String, classType: Class<*> = Panel::class.java): Image {
    return ImageIO.read(classType.getResource(fileName))
}

fun createBlackImage(width: Int, height: Int): BufferedImage {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val graphics = image.createGraphics()
    graphics.color = Color.BLACK
    graphics.fillRect(0, 0, width, height)
    graphics.dispose()
    return image
}
