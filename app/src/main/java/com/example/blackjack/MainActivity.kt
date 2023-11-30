package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        class MainActivity : AppCompatActivity() {

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

            val cards = mutableListOf<Card>()
            val dealerCards = mutableListOf<Card>()
            val playerCards = mutableListOf<Card>()

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

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
            }

            fun drawAndShowCard(imageView: ImageView): Card {
                val drawnCard = cards.removeAt(0)
                val cardImageResource = CardImageMapper.cardImages[drawnCard]
                imageView.setImageResource(cardImageResource ?: R.drawable.error) // Här antar jag att du har en generisk felbild ifall kortet inte hittas i kartan
                return drawnCard
            }

            fun countPoints(cards: List<Card>): Int {
                var points = 0

                for (card in cards) {
                    points += when (card.value) {
                        "ess" -> 11
                        "två" -> 2
                        "tre" -> 3
                        "fyra" -> 4
                        "fem" -> 5
                        "sex" -> 6
                        "sju" -> 7
                        "åtta" -> 8
                        "nio" -> 9
                        "tio", "knekt", "dam", "kung" -> 10
                        else -> 0
                    }
                }
                return points
            }

            fun determineWinner() {
                val playerPoints = countPoints(playerCards)
                val dealerPoints = countPoints(dealerCards)

                when {
                    playerPoints > 21 -> println("Dealern vinner!")
                    dealerPoints > 21 -> println("Spelaren vinner!")
                    playerPoints > dealerPoints -> println("Spelaren vinner!")
                    dealerPoints > playerPoints -> println("Dealern vinner!")
                    else -> println("Det är oavgjort!")
                }
            }

        }
    }
}