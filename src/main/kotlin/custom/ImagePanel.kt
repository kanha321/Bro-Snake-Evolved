package custom

import utils.getImage
import java.awt.Graphics
import java.awt.Image
import javax.swing.JPanel

open class ImagePanel(width: Int, height: Int) : JPanel() {
    private val pImage = getImage("/HomeScreen/portrait_4k.jpeg", this::class.java)
    private val lImage = getImage("/HomeScreen/landscape_4k.jpeg", this::class.java)
    private val sImage = getImage("/HomeScreen/square_4k.jpeg", this::class.java)
    // get panel dimensions
    private var image =
        if (width > height) lImage
        else if (width < height) pImage
        else sImage

    init {
        image = image.getScaledInstance(-1, height, Image.SCALE_SMOOTH)
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