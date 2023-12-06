package com.example.blackjack
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val startGameButton = findViewById<Button>(R.id.PlayButton)
        startGameButton.setOnClickListener {
            startGameButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        val ruleButton = findViewById<Button>(R.id.RuleButton)
        ruleButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("How to play")
                .setMessage("Beat The Dealer. There are some misconceptions about the objective of the game of blackjack but at the simplest level all you are trying to do is beat the dealer.\n" +
                        "\n" +
                        "How do you beat the dealer?\n" +
                        "\n" +
                        "1. By drawing a hand value that is higher than the dealer’s hand value\n" +
                        "2. By the dealer drawing a hand value that goes over 21.\n" +
                        "3. By drawing a hand value of 21 on your first two cards, when the dealer does not.\n" +
                        "\n" +
                        "How do you lose to the dealer? \n" +
                        "\n" +
                        "1. Your hand value exceeds 21.\n" +
                        "2. The dealers hand has a greater value than yours at the end of the round")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        val quitButton = findViewById<Button>(R.id.QuitButton)
        quitButton.setOnClickListener {
            finish()
        }
    }

}