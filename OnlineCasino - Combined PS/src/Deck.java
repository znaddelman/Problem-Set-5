/**********************************************************************
 * @file Deck.java
 * @brief File for deck of cards. Can be used for multiple files or games
 * if needed. The file sets a deck of cards and then has shuffle function
 * to shuffle the deck.getSpot() is a function that gets the next card.
 * The booleans can be used in other games but were specifically created
 * for horse racing.
 * @author Zach Naddelman
 * @date: 11/27/2022
 * @acknowledgement: N/A
 ***********************************************************************/
public class Deck {
        //deck class used to create a deck of cards
        //arrays set for clubs and values
        String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades" };

        String[] FACES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        // initialize deck array with 52 cards
        String[] deck = new String[52];

        public void setDeck() { //method sets each combination of faces and suits together for all 52 cards
            for(int i = 0; i < FACES.length; i++) {
                for (int j = 0; j < SUITS.length; j++) {
                    deck[SUITS.length * i + j] = FACES[i] + " of " + SUITS[j];
                }
            }
        }



    public void shuffle() { //shuffles deck
        // shuffle
        for (int i = 0; i < 52; i++) {
            int r = i + (int) (Math.random() * (52 - i));
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
    }

    public void getSpot(int i) { //gets a single card from the deck
            //getSpot is useful for loops where it can pull cards from the top of a pile
        System.out.println(deck[i]);
    }

    public boolean isHeart(int i) { //boolean to check if a card in the deck is a heart
        return deck[i].contains("Hearts");
    }
    public boolean isSpade(int i) { //same as above but spades
        return deck[i].contains("Spades");
    }
    public boolean isClub(int i) { //same as above but clubs
        return deck[i].contains("Clubs");
    }
    public boolean isDiamond(int i) {//same as above but diamonds
        return deck[i].contains("Diamonds");
    }






}
