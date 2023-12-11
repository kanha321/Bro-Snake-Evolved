package Game

import backups.GamePanelBak
import utils.SCREEN_HEIGHT
import utils.SCREEN_WIDTH
import utils.getImage
import java.awt.Image
import java.util.*
import javax.imageio.ImageIO
import javax.swing.Timer

// general
const val UNIT_SIZE = 25
const val GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE * UNIT_SIZE
const val DELAY = 15
var direction = 'r'
var tailDirection = 'r'

// snake
val x = IntArray(GAME_UNITS)
val y = IntArray(GAME_UNITS)
const val INITIAL_BODY_PARTS = 5
var bodyParts = INITIAL_BODY_PARTS

// power up
const val POWER_UP_SIZE = 3
const val POWER_UP_UNIT_SIZE = POWER_UP_SIZE * UNIT_SIZE
const val POWER_UP_FREQUENCY = 5
const val POWER_UP_TIMER = 5000
var powerUpTimeLeft = POWER_UP_TIMER
var powerUpX = 0
var powerUpY = 0
var powerUpIndex = -1

// speed TODO(needs improvement)
const val SPEED = 6 // MAX 7
var speedCheck = 0

// food
var foodX = 0
var foodY = 0
var foodIndex = -1

// collision (for making that part red)
var collisionX = -1
var collisionY = -1

// apple counts
var scoreCount = 0
var appleAfterPowerUp = 0

// flags
var running = false
var gameOver = false
var stopMovement = false

// some objects
var timer: Timer? = null
var random: Random = Random()

// strings
var stRestartPrompt = "Press SPACE to play again"
var stPausePrompt = "Press SPACE to resume"
var stPaused = "Paused"
var stGameOver = "Game Over!!"
var stWatermark = "!Bro-Snake-Evolved!"

private val appleImg: Image = getImage("/Food/apple.png")
private val bigAppleImg: Image = getImage("/bigApple.png")

val foodImgs = arrayOf(
    getImage("/Food/apple.png"),
    getImage("/Food/banana.png"),
    getImage("/Food/cherry.png"),
    getImage("/Food/strawberry.png"),
)

val powerUpImgs = arrayOf(
    getImage("/PowerUp/swap.png"),
    getImage("/PowerUp/turbo.png"),
    getImage("/PowerUp/slow.png"),

//        getImage("/power_ghost.png"),
//        getImage("/power_life.png"),
)

fun getRandom(bound: Int): Int = random.nextInt(bound)

fun forceInit() {
    random = Random()
}