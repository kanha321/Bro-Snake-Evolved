package utils
import Home.HomePanel
import Home.Panel
import java.awt.CardLayout
import java.awt.event.ActionListener
import javax.swing.JFrame
import javax.swing.JPanel


class SnakeFrame : JFrame() {
    var cardLayout: CardLayout? = null
    var cardsContainer: JPanel? = null
    var homePanel: HomePanel? = null
    var myPanel: Panel? = null

    fun MainFrame() {

    }


    init {
        cardLayout = CardLayout()
        cardsContainer = JPanel(cardLayout)
        homePanel = HomePanel()
        myPanel = Panel()

        cardsContainer!!.add(homePanel, "Home")
        cardsContainer!!.add(myPanel, "MyPanel")

        homePanel!!.button1!!.addActionListener(ActionListener {
            cardLayout!!.show(cardsContainer, "MyPanel")
        })

        // Add a button listener in Home panel to switch to MyPanel
        this.add(cardsContainer)

        val homePanel = HomePanel()
        this.add(homePanel)
        this.title = "SnakeGame (powered by Kotlin)"
        this.pack()
        this.isResizable = false
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isVisible = true
        this.setLocationRelativeTo(null)
    }
}