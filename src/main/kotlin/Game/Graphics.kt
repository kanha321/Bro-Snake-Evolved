package Game

import utils.SCREEN_HEIGHT
import utils.SCREEN_WIDTH
import java.awt.Color
import java.awt.Font
import java.awt.Graphics

fun draw(g: Graphics) {
    if (running) {
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
                if (stopMovement && x[i] == collisionX && y[i] == collisionY)
                    g.color = Color.red
                else
                    g.color = Color.green
            } else {
                if (stopMovement && x[i] == collisionX && y[i] == collisionY)
                    g.color = Color.red
                else
                    g.color = Color(45, 180, 0)
            }
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE)
        }
        waterMark(g)
    } else gameOver(g)
    displayScore(g)
}

fun displayScore(g: Graphics) {
    g.color = Color(200, 200, 200, 180)
    g.font = Font("Monotype Corsiva", Font.PLAIN, 40)
    g.drawString(
        "score: $scoreCount",
        (SCREEN_WIDTH - g.fontMetrics.stringWidth("score: $scoreCount")) / 2,
        SCREEN_HEIGHT - 30
    )
}

fun gameOver(g: Graphics) {
    gameOver = true
    g.color = Color.red
    g.font = Font("Ink Free", Font.BOLD, 69)
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

fun powerUpTimer(g: Graphics) {
    var i = 0
    while (i < powerUpTimeLeft * SCREEN_WIDTH / POWER_UP_TIMER) {
        g.color = Color(255, 255, 0)
        g.fillRect(i - UNIT_SIZE, SCREEN_HEIGHT - 3, UNIT_SIZE, UNIT_SIZE)
        i++
    }
}