package game.logic

import game.*
import utils.SCREEN_HEIGHT
import utils.SCREEN_WIDTH

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

fun checkPowerUp() {
    var i = powerUpX
    while (i < powerUpX + POWER_UP_SIZE * UNIT_SIZE) {
        var j = powerUpY
        while (j < powerUpY + POWER_UP_SIZE * UNIT_SIZE) {
            // Check collision with snake's head
            if (x[0] == i && y[0] == j) {
                scoreCount += powerUpTimeLeft / 100 * SPEED
                applyPowerUp()
                newPowerUp()
                appleAfterPowerUp = 0
            }
            // Check collision with the rest of the snake's body
            for (k in 1..<bodyParts) {
                if (i == x[k] && j == y[k]) {
                    newPowerUp()
                }
            }
            j += UNIT_SIZE
        }
        i += UNIT_SIZE
    }
}

fun newPowerUp() {
    powerUpIndex = getRandom(powerUpImgs.size)
    do {
        powerUpX = getRandom((SCREEN_WIDTH - ((POWER_UP_SIZE - 1) + 2) * UNIT_SIZE) / UNIT_SIZE) * UNIT_SIZE + UNIT_SIZE
        powerUpY = getRandom((SCREEN_HEIGHT - ((POWER_UP_SIZE - 1) + 3) * UNIT_SIZE) / UNIT_SIZE) * UNIT_SIZE + UNIT_SIZE
    } while (isCollidingWithSnake())
    powerUpTimeLeft = POWER_UP_TIMER
//    appleAfterPowerUp = 0
}

fun showPowerUp(): Boolean {
    return appleAfterPowerUp == POWER_UP_FREQUENCY
}


fun isCollidingWithSnake(): Boolean {
    for (i in 0..<bodyParts) {
        if (x[i] == powerUpX && y[i] == powerUpY) {
            return true
        }
    }
    return false
}