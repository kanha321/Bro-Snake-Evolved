package highScores

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import game.allScore
import utils.JSON_HIGHSCORES
import java.io.File
import java.lang.reflect.Type

val scoresTableModel: ScoresTableModel = ScoresTableModel()

fun saveScore(highScoreData: HighScoreData) {
    val dir = File(System.getProperty("user.home"), "SnakeKt")
    dir.mkdirs()
    val file = File(dir, JSON_HIGHSCORES)
    val gson = Gson()
    val highScores: MutableList<HighScoreData> = loadHighScores() ?: mutableListOf()
    highScores.add(highScoreData)
    val json = gson.toJson(highScores)
    file.writeText(json)
    scoresTableModel.addScore(highScoreData)
    allScore!!.add(highScoreData)
}

fun loadHighScores(): MutableList<HighScoreData>? {
    val dir = File(System.getProperty("user.home"), "SnakeKt")
    val file = File(dir, JSON_HIGHSCORES)
    return if (file.exists()) {
        val gson = Gson()
        val json = file.readText()
        val listType: Type = object : TypeToken<List<HighScoreData>>() {}.type
        gson.fromJson(json, listType)
    } else null
}

fun loadSortedHighScores(): MutableList<HighScoreData> {
    val dir = File(System.getProperty("user.home"), "SnakeKt")
    val file = File(dir, JSON_HIGHSCORES)
    val scoreData: MutableList<HighScoreData>
    if (file.exists()) {
        val gson = Gson()
        val json = file.readText()
        val listType: Type = object : TypeToken<List<HighScoreData>>() {}.type
        scoreData = gson.fromJson(json, listType)
    } else scoreData = mutableListOf()
    return scoreData.sortedByDescending { it.highScore }.take(10).toMutableList()
}

fun getHighScore(): HighScoreData {
    val highScores = loadHighScores()
    return if (highScores != null) {
        highScores.maxByOrNull { it.highScore } ?: HighScoreData(0, 0)
    } else {
        HighScoreData(0, 0)
    }
}

fun refreshScores(sort: Boolean = false) {
    scoresTableModel.clearScores()
    val highScores = if (sort) loadSortedHighScores() else loadHighScores()!!
    highScores.forEach { scoresTableModel.addScore(it) }
}