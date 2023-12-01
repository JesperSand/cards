package com.example.blackjack


import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import layout.CardImages

class MainActivity : AppCompatActivity() {
    val cards = mutableListOf<Card>()
    val dealerCards = mutableListOf<Card>()
    val playerCards = mutableListOf<Card>()

    // Definiera dina ImageView objekt
    lateinit var dealerCard1: ImageView
    lateinit var playerCard1: ImageView
    lateinit var dealerCard2: ImageView
    lateinit var playerCard2: ImageView
    lateinit var playerscore: TextView
    lateinit var dealerscore: TextView
    lateinit var standbutton: Button
    lateinit var hitbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dealerCard1 = findViewById(R.id.DealerCard1)
        playerCard1 = findViewById(R.id.PlayerCard1)
        dealerCard2 = findViewById(R.id.DealerCard2)
        playerCard2 = findViewById(R.id.PlayerCard2)
        dealerscore = findViewById(R.id.DealerScore)
        playerscore = findViewById(R.id.PlayerScore)

        // Ändra storleken på ImageView objekten
        val newWidth = 250  // Ange önskad bredd här
        val newHeight = 500 // Ange önskad höjd här

        dealerCard1.layoutParams.width = newWidth
        dealerCard1.layoutParams.height = newHeight

        playerCard1.layoutParams.width = newWidth
        playerCard1.layoutParams.height = newHeight

        dealerCard2.layoutParams.width = newWidth
        dealerCard2.layoutParams.height = newHeight

        playerCard2.layoutParams.width = newWidth
        playerCard2.layoutParams.height = newHeight


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

        for (color in colors) {
            for (value in numbers) {
                val card = Card(color, value)
                cards.add(card) // Lägg till kortet i listan
            }
        }

        cards.shuffle() // Blanda kortleken

        // Dela ut de första korten
        dealerCards.add(drawAndShowCard(dealerCard1))
        playerCards.add(drawAndShowCard(playerCard1))
        dealerCards.add(drawAndShowCard(dealerCard2))
        playerCards.add(drawAndShowCard(playerCard2))

        val dealerPoints = countPoints(dealerCards)
        val playerPoints = countPoints(playerCards)

        dealerscore.text = dealerPoints.toString()
        playerscore.text = playerPoints.toString()
        determineWinner()
    }

    fun drawAndShowCard(imageView: ImageView): Card {
        val cardImagesInstance = CardImages() // Skapar en instans av CardImages
        val drawnCard = cards.removeAt(0) // Använder cards listan istället för Card
        val cardImageResource = cardImagesInstance.cardImages[drawnCard] // Använder instansen cardImagesInstance istället för CardImages
        imageView.setImageResource(
            cardImageResource ?: R.drawable.error // Fortfarande behöver du ha en error bild i din drawable mapp
        )
        return drawnCard
    }

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

    fun determineWinner() {
        val playerPoints = countPoints(playerCards)
        val dealerPoints = countPoints(dealerCards)
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Spelet är slut!")

        when {
            playerPoints > 21 -> builder.setMessage("Dealern vinner!")
            dealerPoints > 21 -> builder.setMessage("Spelaren vinner!")
            playerPoints > dealerPoints -> builder.setMessage("Spelaren vinner!")
            dealerPoints > playerPoints -> builder.setMessage("Dealern vinner!")
            else -> builder.setMessage("Det är oavgjort!")
        }
        builder.setPositiveButton("Spela igen") { dialog, which ->
            restartGame()
        }
        builder.setNegativeButton("Avsluta") { dialog, which ->
            finish()
        }
        builder.show()
    }
    fun restartGame() {
        dealerCards.clear()
        playerCards.clear()
        cards.shuffle()

        // Dela ut de första korten
        dealerCards.add(drawAndShowCard(dealerCard1))
        playerCards.add(drawAndShowCard(playerCard1))
        dealerCards.add(drawAndShowCard(dealerCard2))
        playerCards.add(drawAndShowCard(playerCard2))

        val dealerPoints = countPoints(dealerCards)
        val playerPoints = countPoints(playerCards)

        dealerscore.text = dealerPoints.toString()
        playerscore.text = playerPoints.toString()
        determineWinner()
    }

}

