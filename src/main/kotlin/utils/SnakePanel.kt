package utils

import panelManagement.PanelManager
import java.awt.Color
import java.awt.Dimension
import java.awt.LayoutManager
import java.awt.event.KeyEvent
import javax.swing.JPanel

open class SnakePanel: JPanel {
    constructor()
    constructor(layout: LayoutManager?) : super(layout)
    init {
        preferredSize = Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)
        background = Color(0)
        this.addKeyListener(
            object : java.awt.event.KeyAdapter() {
                override fun keyPressed(e: KeyEvent) {
                    if (e.keyCode == KeyEvent.VK_ESCAPE) {
                        PanelManager.switchToPreviousPanel()
                    }
                }
            }
        )
        this.isFocusable = true
        this.requestFocusInWindow()
    }
}