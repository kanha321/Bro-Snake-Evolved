package utils

import java.awt.Color
import java.awt.Dimension
import java.awt.LayoutManager
import javax.swing.JPanel

open class SnakePanel: JPanel {
    constructor()
    constructor(layout: LayoutManager?) : super(layout)
    init {
        preferredSize = Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)
        background = Color(0)
    }
}