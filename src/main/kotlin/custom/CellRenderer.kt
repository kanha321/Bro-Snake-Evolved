package custom

import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.SwingConstants
import javax.swing.table.DefaultTableCellRenderer
import java.awt.Component
import javax.swing.JTable
import javax.swing.JComponent

class CellRenderer(private val isHeader: Boolean) : DefaultTableCellRenderer() {
    init {
        horizontalAlignment = SwingConstants.CENTER
    }

    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        val cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
        cellComponent.background = Color(120, 255, 120)
        cellComponent.foreground = Color(0, 50, 0)
        if (isHeader) {
            if (cellComponent is JComponent) {
                cellComponent.border = BorderFactory.createLineBorder(Color(0, 25, 0), 1)
            }
        } else {
            if (cellComponent is JComponent) {
                cellComponent.border = BorderFactory.createLineBorder(Color(0, 25, 0), 1)
            }
            if (row % 2 == 0) {
                cellComponent.background = Color(255, 255, 255)
            } else {
                cellComponent.background = Color(225, 255, 225)
            }
        }
        return cellComponent
    }
}