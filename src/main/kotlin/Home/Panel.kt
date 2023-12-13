package Home

import utils.SnakePanel
import javax.swing.JLabel

class Panel : SnakePanel() {
    init {
        val label = JLabel("You are now in the Home.Panel")
        add(label)
    }
}