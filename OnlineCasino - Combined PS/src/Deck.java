public class Deck {
        //deck class used to create a deck of cards
        String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades" };

        String[] FACES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        // initialize deck
        String[] deck = new String[48];

        public void setDeck() {
            for(int i = 0; i < FACES.length; i++) {
                for (int j = 0; j < SUITS.length; j++) {
                    deck[SUITS.length * i + j] = FACES[i] + SUITS[j];
                }
            }
        }



    public void shuffle() {
        // shuffle
        for (int i = 0; i < 48; i++) {
            int r = i + (int) (Math.random() * (48 - i));
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
    }

    public void getSpot(int i) {
        System.out.print(deck[i]);
    }

    public boolean isHeart(int i) {
            if(deck[i].contains("Hearts")) {
                return true;
            }
            return false;
    }
    public boolean isSpade(int i) {
        if(deck[i].contains("Spades")) {
            return true;
        }
        return false;
    }
    public boolean isClub(int i) {
        if(deck[i].contains("Clubs")) {
            return true;
        }
        return false;
    }
    public boolean isDiamond(int i) {
        if(deck[i].contains("Diamonds")) {
            return true;
        }
        return false;
    }

    public String[] getDeck() {
            return deck.clone();
    }




}
