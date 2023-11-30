package layout

import com.example.blackjack.Card
import com.example.blackjack.R

object CardImageMapper {
    val cardImages: Map<Card, Int> = mapOf(
        //Images for Hearts
        Card("hearts", "ace") to R.drawable.ace_of_hearts,
        Card("hearts", "two") to R.drawable.two_of_hearts,
        Card("hearts", "three") to R.drawable.three_of_hearts,
        Card("hearts", "four") to R.drawable.four_of_hearts,
        Card("hearts", "five") to R.drawable.five_of_hearts,
        Card("hearts", "six") to R.drawable.six_of_hearts,
        Card("hearts", "seven") to R.drawable.seven_of_hearts,
        Card("hearts", "eight") to R.drawable.eight_of_hearts,
        Card("hearts", "nine") to R.drawable.nine_of_hearts,
        Card("hearts", "ten") to R.drawable.ten_of_hearts,
        Card("hearts", "jack") to R.drawable.jack_of_hearts,
        Card("hearts", "queen") to R.drawable.queen_of_hearts,
        Card("hearts", "king") to R.drawable.ace_of_hearts,

        //Images for Diamonds
        Card("diamond", "ace") to R.drawable.ace_of_diamonds,
        Card("diamond", "two") to R.drawable.two_of_diamonds,
        Card("diamond", "three") to R.drawable.three_of_diamonds,
        Card("diamond", "four") to R.drawable.four_of_diamonds,
        Card("diamond", "five") to R.drawable.five_of_diamonds,
        Card("diamond", "six") to R.drawable.six_of_diamonds,
        Card("diamond", "seven") to R.drawable.seven_of_diamonds,
        Card("diamond", "eight") to R.drawable.eight_of_diamonds,
        Card("diamond", "nine") to R.drawable.nine_of_diamonds,
        Card("diamond", "ten") to R.drawable.ten_of_diamonds,
        Card("diamond", "jack") to R.drawable.jack_of_diamonds,
        Card("diamond", "queen") to R.drawable.queen_of_diamonds,
        Card("diamond", "king") to R.drawable.king_of_diamonds,

        //Images for clubs
        Card("clubs", "ace") to R.drawable.ace_of_clubs,
        Card("clubs", "two") to R.drawable.two_of_clubs,
        Card("clubs", "three") to R.drawable.three_of_clubs,
        Card("clubs", "four") to R.drawable.four_of_clubs,
        Card("clubs", "five") to R.drawable.five_of_clubs,
        Card("clubs", "six") to R.drawable.six_of_clubs,
        Card("clubs", "seven") to R.drawable.seven_of_clubs,
        Card("clubs", "eight") to R.drawable.eight_of_clubs,
        Card("clubs", "nine") to R.drawable.nine_of_clubs,
        Card("clubs", "ten") to R.drawable.ten_of_clubs,
        Card("clubs", "jack") to R.drawable.jack_of_clubs,
        Card("clubs", "queen") to R.drawable.queen_of_clubs,
        Card("clubs", "king") to R.drawable.king_of_clubs,

        //Images for spades
        Card("spades", "ace") to R.drawable.ace_of_spades,
        Card("spades", "two") to R.drawable.two_of_spades,
        Card("spades", "three") to R.drawable.three_of_spades,
        Card("spades", "four") to R.drawable.four_of_spades,
        Card("spades", "five") to R.drawable.five_of_spades,
        Card("spades", "six") to R.drawable.six_of_spades,
        Card("spades", "seven") to R.drawable.seven_of_spades,
        Card("spades", "eight") to R.drawable.eight_of_spades,
        Card("spades", "nine") to R.drawable.nine_of_spades,
        Card("spades", "ten") to R.drawable.ten_of_spades,
        Card("spades", "jack") to R.drawable.jack_of_spades,
        Card("spades", "queen") to R.drawable.queen_of_spades,
        Card("spades", "king") to R.drawable.king_of_spades,
    )
}