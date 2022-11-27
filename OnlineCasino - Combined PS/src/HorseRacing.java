import java.util.Random;
import java.util.Scanner;

public class HorseRacing extends Deck {
    public static int raceHorses(int playerMoney) {
        //printing introduction and establishing variables & scanner
        System.out.println("");
        System.out.println("");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("    Welcome to Horse Racing     ");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        String heart, diamond, spade, club;
        int heartLength = 0, diamondLength = 0, spadeLength = 0, clubLength = 0;
        int heartOdds = 0, diamondOdds = 0, spadeOdds = 0, clubOdds = 0;
        int heartMultiplier = 1, diamondMultiplier = 1, spadeMultiplier = 1, clubMultiplier = 1;
        Deck deck = new Deck();
        deck.setDeck();
        deck.shuffle();

        System.out.println();
        for (int i = 0; i < 7; i++) {
            deck.getSpot(i);
            if(deck.isHeart(i)) {
                heartOdds++;
            } else if(deck.isClub(i)) {
                clubOdds++;
            } else if(deck.isDiamond(i)) {
                diamondOdds++;
            } else if(deck.isSpade(i)) {
                spadeOdds++;
            }
        }
        System.out.println("Heart: " + heartOdds + "Club: " + clubOdds + "Diamond: " + diamondOdds + "Spade: " + spadeOdds);





        return playerMoney;
    }

}
