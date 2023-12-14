package panelManagement

import utils.SnakePanel
import java.awt.CardLayout
import java.util.*
import javax.swing.JPanel

object PanelManager {
    private var cardLayout: CardLayout = CardLayout()
    private var cardPanel: SnakePanel = SnakePanel(cardLayout)
    private var panelStack: Stack<JPanel> = Stack()

    fun getCardPanel(): SnakePanel {
        return cardPanel
    }
    fun addPanel(panel: JPanel, name: String) {
        panel.name = name
        cardPanel.add(panel, name)
    }
    fun switchPanel(panel: JPanel) {
        cardLayout.show(cardPanel, panel.name)
        panelStack.push(panel)
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