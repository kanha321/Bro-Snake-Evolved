package utils

import java.awt.Color
import java.awt.Dimension
import javax.swing.JPanel

open class SnakePanel: JPanel() {
    init {
        preferredSize = Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)
        background = Color(0)
    }
}