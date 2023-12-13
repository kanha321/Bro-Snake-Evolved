package utils

import game.Panel
import java.awt.Image
import javax.imageio.ImageIO

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 600
// getImage function takes 2 parameters: fileName and class
fun getImage(fileName: String, classType: Class<*> = Panel::class.java): Image {
    return ImageIO.read(classType.getResource(fileName))
}
