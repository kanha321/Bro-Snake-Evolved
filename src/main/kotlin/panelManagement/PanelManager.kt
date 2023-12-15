package panelManagement

import utils.SnakePanel
import java.awt.CardLayout
import java.awt.Window
import java.util.*
import javax.swing.JPanel
import kotlin.system.exitProcess

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

    fun goToPanelAndClearStack(targetPanel: JPanel) {
        while (!panelStack.empty()) panelStack.pop()
        panelStack.push(targetPanel)
        switchPanel(targetPanel)
    }

    fun switchToPreviousPanel(targetPanel: JPanel? = null) {  // BUG: giving argument works slower takes around 600-700ms to switch
        if (targetPanel != null) {
            while (!panelStack.empty() && panelStack.peek() != targetPanel) {
                panelStack.pop()
            }
        } else {
            if (!panelStack.empty()) {
                panelStack.pop()
            }
        }
        if (!panelStack.empty()) {
            cardLayout.show(cardPanel, panelStack.peek().name)
            panelStack.peek().requestFocusInWindow()
        } else {
            exitProcess(0)
        }
    }
}