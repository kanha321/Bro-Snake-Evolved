package game

import game.logic.showPowerUp
import highScores.getHighScore
import utils.SCREEN_HEIGHT
import utils.SCREEN_WIDTH
import java.awt.Color
import java.awt.Font
import java.awt.Graphics

fun draw(g: Graphics) {

    drawBorder(g)

    if (showPowerUp()) {
        powerUpTimer(g)
    }
    g.drawImage(foodImgs[foodIndex], foodX, foodY, UNIT_SIZE, UNIT_SIZE, null)
    if (showPowerUp()) {
        g.drawImage(powerUpImgs[powerUpIndex], powerUpX, powerUpY, POWER_UP_UNIT_SIZE, POWER_UP_UNIT_SIZE, null)
        powerUpTimer(g)
    }
    for (i in 0..<bodyParts) {
        if (i == 0) {
            if (isDead && x[i] == collisionX && y[i] == collisionY)
                g.color = Color.red
            else
                g.color = Color.green
        } else {
            if (isDead && x[i] == collisionX && y[i] == collisionY)
                g.color = Color.red
            else
                g.color = Color(45, 180, 0)
        }
        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE)
    }
    fix00(g)
    waterMark(g)
//    devVal1(g)
    displayScore(g)
}
fun fix00(g: Graphics){
    g.color = Color.black
    g.fillRect(0, 0, UNIT_SIZE, UNIT_SIZE)  // the dirty way to hide the glitch where snake's body appears at (0, 0)
    g.color = Color.decode("#FFF800")
    g.fillRect(UNIT_SIZE - 2, UNIT_SIZE - 2, SCREEN_WIDTH - 2 * UNIT_SIZE + 3, 2)
}

fun drawBorder(g: Graphics) {
    g.color = Color.decode("#FFF800")
    g.fillRect(UNIT_SIZE - 2, UNIT_SIZE - 2, 3, SCREEN_HEIGHT - UNIT_SIZE - 2 * UNIT_SIZE)
    g.fillRect(SCREEN_WIDTH - UNIT_SIZE - 1, UNIT_SIZE - 2, 3, SCREEN_HEIGHT - UNIT_SIZE - 2 * UNIT_SIZE)
    g.fillRect(UNIT_SIZE - 2, UNIT_SIZE - 2, SCREEN_WIDTH - 2 * UNIT_SIZE + 3, 3)
    g.fillRect(UNIT_SIZE - 2, SCREEN_HEIGHT - UNIT_SIZE * 2 - 2, SCREEN_WIDTH - 2 * UNIT_SIZE + 4, 3)
}

fun displayScore(g: Graphics) {
    val xpos = if (gameOver) 4 else 2
    g.color = Color(200, 200, 200, 180)
    g.font = Font("Monotype Corsiva", Font.PLAIN, 40)
    g.drawString(
        "Score: $scoreCount",
        (SCREEN_WIDTH - g.fontMetrics.stringWidth("Score: $scoreCount")) / xpos,
        SCREEN_HEIGHT - 15
    )
}

fun displayHighScore(g: Graphics) {
    g.color = Color(200, 200, 200, 180)
    g.font = Font("Monotype Corsiva", Font.PLAIN, 40)
    g.drawString(
        "High Score: $highScore",
        (SCREEN_WIDTH - g.fontMetrics.stringWidth("High Score: $highScore")) * 3 / 4,
        SCREEN_HEIGHT - 15
    )
}
fun displayNewHighScore(g: Graphics) {
    g.color = Color(255, 163, 0, 180)
    g.font = Font("Monotype Corsiva", Font.PLAIN, 45)
    g.drawString(
        "New High Score: ${getHighScore().highScore}",
        (SCREEN_WIDTH - g.fontMetrics.stringWidth("New High Score: ${getHighScore().highScore}")) / 2,
        SCREEN_HEIGHT - 25
    )
}

fun gameOver(g: Graphics) {
    gameOver = true
    g.color = Color.red
    g.font = Font("Ink Free", Font.BOLD, 84)
    g.drawString(stGameOver, (SCREEN_WIDTH - g.fontMetrics.stringWidth(stGameOver)) / 2, SCREEN_HEIGHT / 2)
    restartPrompt(g)
}

//fun homeScreen(g: Graphics) {
//    homeScreen.paint(g)
//}

fun waterMark(g: Graphics) {
    g.color = Color(255, 255, 255, 50)
    g.font = Font("Tahoma", Font.BOLD, 40)
    g.drawString(stWatermark, (SCREEN_WIDTH - g.fontMetrics.stringWidth(stWatermark)) / 2, SCREEN_HEIGHT / 2)
}


fun restartPrompt(g: Graphics) {
    g.color = Color.green
    g.font = Font("Times New Roman", Font.PLAIN, 30)
    g.drawString(
        stRestartPrompt,
        (SCREEN_WIDTH - g.fontMetrics.stringWidth(stRestartPrompt)) / 2,
        SCREEN_HEIGHT - 100
    )
}

fun devVal1(g: Graphics, val1: Int) {
    g.color = Color.green
    g.font = Font("Times New Roman", Font.PLAIN, 30)
    g.drawString(
        val1.toString(),
        (SCREEN_WIDTH - g.fontMetrics.stringWidth(val1.toString())) / 2,
        SCREEN_HEIGHT - 50
    )
}

fun powerUpTimer(g: Graphics) {

    for (i in 0..<powerUpTimeLeft * SCREEN_WIDTH / POWER_UP_TIMER) {
        g.color = Color(255, 255, 0)
        g.fillRect(i - UNIT_SIZE, SCREEN_HEIGHT - 3, UNIT_SIZE, UNIT_SIZE)
    }
}