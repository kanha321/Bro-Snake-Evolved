package highScores

import custom.CellRenderer
import utils.SCREEN_WIDTH
import java.awt.Color
import java.awt.Dimension
import java.awt.event.MouseAdapter


fun createScoresPanel(highScores: List<HighScoreData>): ScoresPanel {
    val scoresPanel = ScoresPanel()
    if (highScores.isNotEmpty()) {
        for (highScore in highScores) {
            scoresTableModel.addScore(highScore)
        }
    }
//    scoresPanel.scoresTable.background = Color(0, 0, 0, 0)
    scoresPanel.scoresTable.tableHeader.preferredSize = Dimension(SCREEN_WIDTH, 50)
    scoresPanel.scoresTable.rowHeight = 35
    scoresPanel.scoresTable.setDefaultRenderer(Any::class.java, CellRenderer(false))
    scoresPanel.scoresTable.tableHeader.defaultRenderer = CellRenderer(true)
    scoresPanel.scoresTable.tableHeader.addMouseListener(
        object: MouseAdapter() {
            override fun mouseClicked(e: java.awt.event.MouseEvent?) {
                val col = scoresPanel.scoresTable.columnAtPoint(e!!.point)
                val index =
                    if (scoresPanel.scoresTable.getColumnName(col) == "Score") 0
                    else 1
                when (index) {
                    0 -> refreshScores(sort = true)
                    1 -> refreshScores()
                }
            }
        }
    )
    return scoresPanel
}







//    mouse event on table header
//    scoresPanel.scoresTable.tableHeader.addMouseListener(object : MouseAdapter() {
//        override fun mouseClicked(e: MouseEvent?) {
//            val col = scoresPanel.scoresTable.columnAtPoint(e!!.point)
//            val index =
//                if (scoresPanel.scoresTable.getColumnName(col) == "Score") 0
//                else 1
//            when (index) {
//                0 -> refreshScores(sort = true)
//                1 -> refreshScores()
//            }
//        }
//        override fun mouseEntered(e: MouseEvent?) {
//            scoresPanel.scoresTable.tableHeader.background = Color.RED
//        }
//        override fun mouseExited(e: MouseEvent?) {
//            scoresPanel.scoresTable.tableHeader.background = Color.WHITE
//        }
//        override fun mousePressed(e: MouseEvent?) {
//            scoresPanel.scoresTable.tableHeader.background = Color.GREEN
//        }
//        override fun mouseReleased(e: MouseEvent?) {
//            val width = scoresPanel.scoresTable.tableHeader.width
//            val height = scoresPanel.scoresTable.tableHeader.height
//            val color = if (e!!.x in 0..width && e.y in 0..height) Color.RED else Color.WHITE
//            scoresPanel.scoresTable.tableHeader.background = color
//        }
//
//    })
