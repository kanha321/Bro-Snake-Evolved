package utils

import Game.Panel
import java.awt.Image
import javax.imageio.ImageIO

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 600
fun getImage(fileName: String, classType: Class<*> = Panel::class.java): Image {
    return ImageIO.read(classType.getResource(fileName))
}
