package utils

import Game.Panel
import backups.GamePanelBak
import java.awt.Image
import java.io.File
import javax.imageio.ImageIO

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 600
// getImage function takes 2 parameters: fileName and class
fun getImage(fileName: String, classType: Class<*> = Panel::class.java): Image {
    return ImageIO.read(classType.getResource(fileName))
}
