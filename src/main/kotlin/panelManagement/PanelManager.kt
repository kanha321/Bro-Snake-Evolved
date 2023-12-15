package panelManagement

import utils.SnakePanel
import java.awt.CardLayout
import java.awt.Window
import java.util.*
import javax.swing.JPanel

object PanelManager {
    private var cardLayout: CardLayout = CardLayout()
    private var cardPanel: SnakePanel = SnakePanel(cardLayout)
    private var panelStack: Stack<JPanel> = Stack()

    fun getCardPanel(): SnakePanel {
        return cardPanel
    }

    fun getCurrentPanel(): JPanel? {
        return if (!panelStack.empty()) panelStack.peek() else null
    }

    fun addPanel(panel: JPanel, name: String) {
        panel.name = name
        cardPanel.add(panel, name)
    }

    fun switchPanel(panel: JPanel, requestFocusInWindow: Boolean = true) {
        cardLayout.show(cardPanel, panel.name)
        panelStack.push(panel)
        if (requestFocusInWindow)
            panel.requestFocusInWindow()
    }

    fun switchToPreviousPanel() {
        if (!panelStack.empty()) {
            panelStack.pop()
            if (!panelStack.empty()) {
                cardLayout.show(cardPanel, panelStack.peek().name)
            }
        }
    }
}