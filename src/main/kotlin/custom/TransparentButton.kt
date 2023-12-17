package custom

import javax.swing.JButton
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.geom.RoundRectangle2D

const val defRed = 0
const val defGreen = 46
const val defBlue = 0
const val defTransparency = 175
const val hoverTransparency = 225
const val pressedTransparency = 255
const val roundness = 25f

class TransparentButton(
    text: String,
    R: Int = defRed,
    G: Int = defGreen,
    B: Int = defBlue,
    transparencyDef: Int = defTransparency,
    transparencyHover: Int = hoverTransparency,
    transparencyPressed: Int = pressedTransparency
) : JButton(text) {

    constructor(text: String) : this(text, 0, 46, 0, 175, 225, 255)
    constructor(text: String, R: Int, G: Int, B: Int) : this(text, R, G, B, 175, 225, 255)

    private var color = getColor(R, G, B, transparencyDef)
    private var borderColor = Color(
        if (R*2 > 255) 255 else R*2,
        if (G*2 > 255) 255 else G*2,
        if (B*2 > 255) 255 else B*2,
    )

    init {
        isOpaque = false
        isContentAreaFilled = false
        isBorderPainted = false
        addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent?) {
                color = getColor(R, G, B, transparencyHover)
                repaint()
            }

            override fun mouseExited(e: MouseEvent?) {
                color = getColor(R, G, B, transparencyDef)
                repaint()
            }

            override fun mousePressed(e: MouseEvent?) {
                color = getColor(R, G, B, transparencyPressed)
                repaint()
            }

            override fun mouseReleased(e: MouseEvent?) {
                color = getColor(R, G, B, transparencyHover)
                repaint()
            }
        })
    }

    override fun paintComponent(g: Graphics) {
        val g2 = g.create() as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2.color = color
        g2.fill(RoundRectangle2D.Float(0f, 0f, width.toFloat(), height.toFloat(), roundness, roundness))
        g2.dispose()

        super.paintComponent(g)
    }

    override fun paintBorder(g: Graphics) {
        val g2 = g.create() as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2.color = borderColor
        g2.draw(RoundRectangle2D.Float(0f, 0f, (width - 1).toFloat(), (height - 1).toFloat(), roundness, roundness))
        g2.dispose()
    }

    fun getColor(
        R: Int,
        G: Int,
        B: Int,
        transparency: Int
    ): Color {
//        Color(10, 10, 10)
        return Color(R, G, B, transparency)
    }
}