package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        class MainActivity : AppCompatActivity() {

            val colors = listOf("hjärter", "ruter", "klöver", "spader")
            val numbers = listOf(
                "ess",
                "två",
                "tre",
                "fyra",
                "fem",
                "sex",
                "sju",
                "åtta",
                "nio",
                "tio",
                "knekt",
                "dam",
                "kung"
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
                dealerCards.add(drawCard())
                playerCards.add(drawCard())
                dealerCards.add(drawCard())
                playerCards.add(drawCard())
            }

            fun drawCard(): Card {
                return cards.removeAt(0)
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