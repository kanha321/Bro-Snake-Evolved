package Resources

import GamePanel
import java.awt.Image
import java.util.*
import javax.imageio.ImageIO
import javax.swing.Timer

object Vars {

    // general
    const val SCREEN_WIDTH = 800
    const val SCREEN_HEIGHT = 600
    const val UNIT_SIZE = 25
    const val GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE * UNIT_SIZE
    const val DELAY = 15
    var direction = 'r'

    // snake
    val x = IntArray(GAME_UNITS)
    val y = IntArray(GAME_UNITS)
    const val INITIAL_BODY_PARTS = 5
    var bodyParts = INITIAL_BODY_PARTS

    // big apple
    const val BIG_APPLE_SIZE = 3
    const val BIG_APPLE_UNIT_SIZE = BIG_APPLE_SIZE * UNIT_SIZE
    val BIG_APPLE_FREQUENCY = 5
    const val BIG_APPLE_TIMER = 5000
    var bigAppleTimer = BIG_APPLE_TIMER
    var bigAppleX = 0
    var bigAppleY = 0

    // speed TODO(needs improvement)
    const val SPEED = 6 // MAX 7
    var speedCheck = 0

    // apple
    var appleX = 0
    var appleY = 0

    // collision (for making that part red)
    var collisionX = -1
    var collisionY = -1

    // apple counts
    var scoreCount = 0
    var appleAfterBigApple = 0

    // flags
    var running = false
    var gameOver = false
    var stopMovement = false

    // some objects
    var timer: Timer? = null
    var random: Random? = null


    private val appleImg: Image = ImageIO.read(GamePanel::class.java.getResource("/food_apple.png"))
    private val bigAppleImg: Image = ImageIO.read(GamePanel::class.java.getResource("/bigApple.png"))

    private val foodImg = arrayOf(
        getImage("/food_apple.png"),
        getImage("/food_banana.png"),
        getImage("/food_cherry.png"),
        getImage("/food_strawberry.png"),
    )

    private val powerupImg = arrayOf(
        getImage("/powerup_swap.png"),
        getImage("/powerup_turbo.png"),
        getImage("/powerup_slow.png"),
//
//        getImage("/powerup_ghost.png"),
//        getImage("/powerup_life.png"),
    )

    private fun getImage(fileName: String): Image = ImageIO.read(GamePanel::class.java.getResource(fileName))
}