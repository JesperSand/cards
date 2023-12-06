package com.example.blackjack


import android.app.AlertDialog
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import layout.CardImages

class MainActivity : AppCompatActivity() {
    //emptly lists
    val cards = mutableListOf<Card>()
    val dealerCards = mutableListOf<Card>()
    val playerCards = mutableListOf<Card>()


    // Defines all objects
    lateinit var dealerCard1: ImageView
    lateinit var playerCard1: ImageView
    lateinit var dealerCard2: ImageView
    lateinit var playerCard2: ImageView
    lateinit var playerCard3: ImageView
    lateinit var dealercard3: ImageView
    lateinit var playerscore: TextView
    lateinit var dealerscore: TextView
    lateinit var standbutton: Button
    lateinit var hitbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dealerCard1 = findViewById(R.id.DealerCards1)
        playerCard1 = findViewById(R.id.PlayerCard1)
        dealerCard2 = findViewById(R.id.DealerCards2)
        playerCard2 = findViewById(R.id.PlayerCard2)
        playerCard3 = findViewById(R.id.Playercard3)
        dealercard3 = findViewById(R.id.DealerCards3)
        dealerscore = findViewById(R.id.DealerScore)
        playerscore = findViewById(R.id.PlayerScore)
        standbutton = findViewById(R.id.StandButton)
        hitbutton = findViewById(R.id.HitButton)

        // Changes the size of images
        val newWidth = 250
        val newHeight = 500

        //applies the new size
        dealerCard1.layoutParams.width = newWidth
        dealerCard1.layoutParams.height = newHeight

        playerCard1.layoutParams.width = newWidth
        playerCard1.layoutParams.height = newHeight

        dealerCard2.layoutParams.width = newWidth
        dealerCard2.layoutParams.height = newHeight

        playerCard2.layoutParams.width = newWidth
        playerCard2.layoutParams.height = newHeight

        playerCard3.layoutParams.width = newWidth
        playerCard3.layoutParams.height = newHeight

        dealercard3.layoutParams.width = newWidth
        dealercard3.layoutParams.height = newHeight

        //variables for all the suits and numbers possible for a playing card
        val colors = listOf("hearts", "diamond", "clubs", "spades")
        val numbers = listOf(
            "ace",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "jack",
            "queen",
            "king"
        )
        //Creates a card and adds it to a list
        for (color in colors) {
            for (value in numbers) {
                val card = Card(color, value)
                cards.add(card)
            }
        }

        //Shuffles the card
        cards.shuffle()

        //Deals the first 4 cards
        dealerCards.add(drawAndShowCard(dealerCard1))
        playerCards.add(drawAndShowCard(playerCard1))
        dealerCards.add(drawAndShowCard(dealerCard2))
        playerCards.add(drawAndShowCard(playerCard2))
        //Hides the 3rd card for dealer and player
        playerCard3.visibility = View.GONE
        dealercard3.visibility = View.GONE


        val dealerPoints = countPoints(dealerCards)
        val playerPoints = countPoints(playerCards)

        //Hit Button
        hitbutton.setOnClickListener {
            playerCards.add(drawAndShowCard(playerCard3))
            val playerPoints = countPoints(playerCards)
            playerscore.text = playerPoints.toString()
            //writes out blackjack if you get 21 points
            if (playerPoints == 21) {
                AlertDialog.Builder(this)
                    .setTitle("Blackjack!")
                    .setMessage("Du fick 21 och vann!")
                    .setPositiveButton("Spela igen") { dialog, which ->
                        restartGame()
                    }
                    .setNegativeButton("Avsluta") { dialog, which ->
                        finish()
                    }
                    .show()
            }
            //writes out bust if you go over 21 points
            else if (playerPoints > 21) {
                AlertDialog.Builder(this)
                    .setTitle("BUST!")
                    .setMessage("You went over 21, try again")
                    .setPositiveButton("Play again") { dialog, which ->
                        restartGame()
                    }
                    .setNegativeButton("Quit") { dialog, which ->
                        finish()
                    }
                    .show()
            }
        }
        //Updates the score according to the value of the cards
        dealerscore.text = dealerPoints.toString()
        playerscore.text = playerPoints.toString()
        //stand Button
        standbutton.setOnClickListener {
            while (countPoints(dealerCards) < 17) {
                dealerCards.add(drawAndShowCard(dealercard3))
                val dealerPoints = countPoints(dealerCards)
                dealerscore.text = dealerPoints.toString()
            }
            determineWinner()
        }
    }
    //Function for drawing and displayin images
    fun drawAndShowCard(imageView: ImageView): Card {
        val cardImagesInstance = CardImages()
        val drawnCard = cards.removeAt(0)
        val cardImageResource = cardImagesInstance.cardImages[drawnCard]
        imageView.setImageResource(
            cardImageResource ?: R.drawable.error
        )
        //Showes the hidden card
        imageView.visibility = View.VISIBLE

        if (imageView == playerCard1 || imageView == playerCard2) {
            val playerPoints = countPoints(playerCards)
            if (playerPoints > 21) {
                determineWinner()
            }
        }

        return drawnCard
    }
    //Function to count ponts
    fun countPoints(cards: List<Card>): Int {
        var points = 0

        for (card in cards) {
            points += when (card.value) {
                "ace" -> 11
                "two" -> 2
                "three" -> 3
                "four" -> 4
                "five" -> 5
                "six" -> 6
                "seven" -> 7
                "eight" -> 8
                "nine" -> 9
                "ten", "jack", "queen", "king" -> 10
                else -> 0
            }
        }
        return points
    }
    //Function that checks winner and displays a message accordingly
    fun determineWinner() {
        val playerPoints = countPoints(playerCards)
        val dealerPoints = countPoints(dealerCards)
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Game over!")

        when {
            playerPoints > 21 -> builder.setMessage("The dealer won!")
            dealerPoints > 21 -> builder.setMessage("You won!")
            playerPoints > dealerPoints -> builder.setMessage("You won!")
            dealerPoints > playerPoints -> builder.setMessage("The dealer won!")
            else -> builder.setMessage("It's a tie!")
        }
        builder.setPositiveButton("Play again") { dialog, which ->
            restartGame()
        }
        builder.setNegativeButton("Quit") { dialog, which ->
            finish()
        }
        builder.show()
    }
    //Function for resetting the came and running it again
    fun restartGame() {
        dealerCards.clear()
        playerCards.clear()
        cards.shuffle()


        dealerCards.add(drawAndShowCard(dealerCard1))
        playerCards.add(drawAndShowCard(playerCard1))
        dealerCards.add(drawAndShowCard(dealerCard2))
        playerCards.add(drawAndShowCard(playerCard2))
        playerCard3.visibility = View.GONE
        dealercard3.visibility = View.GONE


        val dealerPoints = countPoints(dealerCards)
        val playerPoints = countPoints(playerCards)

        if (playerPoints == 21) {
            AlertDialog.Builder(this)
                .setTitle("Blackjack!")
                .setMessage("Du fick 21 och vann!")
                .setPositiveButton("Spela igen") { dialog, which ->
                    restartGame()
                }
                .setNegativeButton("Avsluta") { dialog, which ->
                    finish()
                }
                .show()
        } else if (dealerPoints == 21) {
            AlertDialog.Builder(this)
                .setTitle("Blackjack!")
                .setMessage("Dealern fick 21 och vann!")
                .setPositiveButton("Spela igen") { dialog, which ->
                    restartGame()
                }
                .setNegativeButton("Avsluta") { dialog, which ->
                    finish()
                }
                .show()
        }

        dealerscore.text = dealerPoints.toString()
        playerscore.text = playerPoints.toString()

        standbutton.setOnClickListener {
            while (countPoints(dealerCards) < 17) {
                dealerCards.add(drawAndShowCard(dealercard3))
                val dealerPoints = countPoints(dealerCards)
                dealerscore.text = dealerPoints.toString()
            }
            determineWinner()
        }
    }

}