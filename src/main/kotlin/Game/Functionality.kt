package Game

import utils.SCREEN_HEIGHT
import utils.SCREEN_WIDTH
import java.awt.event.ActionListener
import javax.swing.Timer

fun startGame(actionListener: ActionListener) {
    newPowerUp()
    newFood()
    timer = Timer(DELAY, actionListener)
    timer!!.start()
}


fun restartGame() {
    bodyParts = INITIAL_BODY_PARTS
    scoreCount = 0
    appleAfterPowerUp = 0
    isDead = false
    collisionX = -1
    collisionY = -1
    x[0] = 0
    y[0] = 0
    for (i in 1..<bodyParts) {
        x[i] = -1
        y[i] = -1
    }
    direction = 'r'
    gameOver = false
    running = true
    newFood()
    newPowerUp()
}

fun newFood() {
    foodIndex = getRandom(foodImgs.size)
    foodX = getRandom(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE
    foodY = getRandom(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE
}

fun checkFood() {
    if (x[0] == foodX && y[0] == foodY) {
        newFood()
        bodyParts++
        scoreCount += SPEED
        if (!showPowerUp()) appleAfterPowerUp++
    }
    for (i in bodyParts downTo 1) {
        if (foodX == x[i] && foodY == y[i]) {
            newFood()
        }
    }
}

fun showPowerUp(): Boolean {
    return appleAfterPowerUp == POWER_UP_FREQUENCY
}


fun newPowerUp() {
    powerUpIndex = getRandom(powerUpImgs.size)
    powerUpX = getRandom(SCREEN_WIDTH / POWER_UP_UNIT_SIZE) * POWER_UP_UNIT_SIZE
    powerUpY = getRandom(SCREEN_HEIGHT / POWER_UP_UNIT_SIZE) * POWER_UP_UNIT_SIZE
    powerUpTimeLeft = POWER_UP_TIMER
    appleAfterPowerUp = 0
}

fun checkPowerUp() {
    var i = powerUpX
    while (i < powerUpX + POWER_UP_SIZE * UNIT_SIZE) {
        var j = powerUpY
        while (j < powerUpY + POWER_UP_SIZE * UNIT_SIZE) {
            if (x[0] == i && y[0] == j) {
                scoreCount += powerUpTimeLeft / 100 * SPEED
                applyPowerUp()
                newPowerUp()
                bodyParts++
                appleAfterPowerUp = 0
            }
            j += UNIT_SIZE
        }
        i += UNIT_SIZE
    }
}

fun applyPowerUp() {
    when (powerUpIndex) {
//        0, 1, 2 -> {   // swap
//            val tempX = IntArray(bodyParts)
//            val tempY = IntArray(bodyParts)
//
//            // Store the current positions
//            for (i in 0..<bodyParts) {
//                tempX[i] = x[i]
//                tempY[i] = y[i]
//            }
//
//            // Reverse the positions
//            for (i in 0..<bodyParts) {
//                x[i] = tempX[bodyParts - i - 1]
//                y[i] = tempY[bodyParts - i - 1]
//            }

            // Reverse the direction
//            when (direction) {
//                'r' -> direction = 'l'
//                'l' -> direction = 'r'
//                'u' -> direction = 'd'
//                'd' -> direction = 'u'
//            }
//        }
    }
}

fun checkCollision() {
    if (x[0] > SCREEN_WIDTH - UNIT_SIZE) {
        x[0] = 0
    }
    if (x[0] < 0) {
        x[0] = SCREEN_WIDTH - UNIT_SIZE
    }
    if (y[0] > SCREEN_HEIGHT - UNIT_SIZE) {
        y[0] = 0
    }
    if (y[0] < 0) {
        y[0] = SCREEN_HEIGHT - UNIT_SIZE
    }
    for (i in bodyParts downTo 1) {
        if (x[0] == x[i] && y[0] == y[i]) {
            collisionX = x[i]
            collisionY = y[i]
            isDead = true
        }
        if (!running) timer!!.stop()
    }
}

fun move() {
    for (i in bodyParts downTo 1) {
        x[i] = x[i - 1]
        y[i] = y[i - 1]
    }
    when (direction) {
        'u' -> y[0] = y[0] - UNIT_SIZE
        'd' -> y[0] = y[0] + UNIT_SIZE
        'l' -> x[0] = x[0] - UNIT_SIZE
        'r' -> x[0] = x[0] + UNIT_SIZE
    }
    tailDirection = getDirection(bodyParts - 1)
}

fun die() {
    bodyParts--
    if (bodyParts == 0) {
        gameOver = true
        running = false
        timer!!.stop()
    }
}

fun getDirection(index: Int): Char {
    return when {
        index == 0 || index >= bodyParts -> ' '
        x[index] < x[index - 1] -> 'r'
        x[index] > x[index - 1] -> 'l'
        y[index] < y[index - 1] -> 'd'
        y[index] > y[index - 1] -> 'u'
        else -> ' '
    }
}