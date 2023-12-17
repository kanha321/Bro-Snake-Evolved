package highScores

import java.text.SimpleDateFormat
import javax.swing.table.AbstractTableModel

class ScoresTableModel : AbstractTableModel() {

    private val scores = arrayListOf<HighScoreData>()
    private val columnNames = arrayOf("Score", "Time")
    override fun getRowCount(): Int {
        return scores.size
    }

    override fun getColumnCount(): Int {
        return columnNames.size
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        val scoreData: HighScoreData = scores[rowIndex]

        val sdf = SimpleDateFormat("hh:mm:ss a     dd/MMM/yyyy")

        return when (columnIndex) {
            0 -> scoreData.highScore
            1 -> sdf.format(scoreData.time)
            else -> ""
        }
    }

    override fun getColumnName(column: Int): String {
        return columnNames[column]
    }
    fun setColumnName(column: Int, name: String) {
        columnNames[column] = name
    }

    fun addScore(scoreData: HighScoreData) {
        scores.add(scoreData)
        fireTableDataChanged()
    }
    fun clearScores() {
        scores.clear()
        fireTableDataChanged()
    }
}