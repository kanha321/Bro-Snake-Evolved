package custom

import utils.SnakePanel
import utils.getImage
import java.awt.Graphics
import java.awt.Image

open class ImagePanel(width: Int, height: Int) : SnakePanel() {
    private val pImage = getImage("/HomeScreen/portrait_4k.jpeg")
    private val lImage = getImage("/HomeScreen/landscape_4k.jpeg")
    private val sImage = getImage("/HomeScreen/square_4k.jpeg")
    // get panel dimensions
    private var image =
        if (width > height) lImage
        else if (width < height) pImage
        else sImage

    init {
        if (width > height) {
            image = image.getScaledInstance(-1, height, Image.SCALE_SMOOTH)
        } else if (width < height) {
            image = image.getScaledInstance(width, -1, Image.SCALE_SMOOTH)
        }
    }
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics) {
        val imageWidth = image.getWidth(null)
        val imageHeight = image.getHeight(null)
        val x = (width - imageWidth) / 2
        val y = (height - imageHeight) / 2
        g.drawImage(image, x, y, null)
    }
}