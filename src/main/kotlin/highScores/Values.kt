package highScores

//import org.json.JSONObject
//import java.io.File
//
//fun saveHighScore(score: Int) {
//    val file = File(System.getProperty("user.home"), "gameSettings.json")
//    val json = JSONObject()
//    json.put("highScore", score)
//    file.writeText(json.toString())
//}
//
//fun loadHighScore(): Int {
//    val file = File(System.getProperty("user.home"), "gameSettings.json")
//    return if (file.exists()) {
//        val json = JSONObject(file.readText())
//        json.getInt("highScore")
//    } else {
//        0
//    }
//}