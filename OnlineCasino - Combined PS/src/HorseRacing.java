import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HorseRacing extends Deck {
    public static int raceHorses(int playerMoney) {
        //printing introduction and establishing variables & scanner
        System.out.println("");
        System.out.println("");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("    Welcome to Horse Racing     ");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        Scanner scnr = new Scanner(System.in);
        String heart, diamond, spade, club;
        int heartLength = 0, diamondLength = 0, spadeLength = 0, clubLength = 0;
        int heartOdds = 0, diamondOdds = 0, spadeOdds = 0, clubOdds = 0;
        int heartMult, diamondMult, spadeMult, clubMult;
        boolean validWager = true, userPlay = true;



        Deck deck = new Deck();
        deck.setDeck();
        deck.shuffle();

        System.out.println("You have $" + playerMoney + " left.");
        System.out.println(" ");
        while(userPlay) {
            int wagerAmt = 0, wager = 0;
            while (validWager) { //keeps looping until it gets a valid wager amount or reads that the user is out of money.
                System.out.println();
                System.out.print("Enter wager amount: ");
                wagerAmt = scnr.nextInt(); // asks for wager amount
                if (wagerAmt <= playerMoney) {
                    wager = wagerAmt;
                    validWager = false;
                } else if (playerMoney <= 0){
                    System.out.println("You are out of money. Please deposit more if you wish to keep playing.");
                    break;
                } else if (playerMoney < wagerAmt){
                    System.out.println("Insufficient funds.");
                }
            }
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
            //if 5 of a suit is drawn, restart
            while(heartOdds >= 5 || clubOdds >= 5 || diamondOdds >= 5 || spadeOdds >=5) {
                deck.shuffle();
                heartOdds = 0;
                clubOdds = 0;
                diamondOdds = 0;
                spadeOdds = 0;
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


            }

            heartMult = getMultiplier(heartOdds);
            diamondMult = getMultiplier(diamondOdds);
            spadeMult = getMultiplier(spadeOdds);
            clubMult = getMultiplier(clubOdds);
            printMultipliers(heartMult, diamondMult, spadeMult, clubMult);

            System.out.println("Which suit would you like to bet on?");
            System.out.println("(H for heart, C for club, D for diamonds, S for spades)");

            String input = scnr.nextLine();

            System.out.print("DIAMOND: ");
            howFar(diamondLength);
            System.out.print("CLUB:    ");
            howFar(clubLength);
            System.out.print("SPADE:   ");
            howFar(spadeLength);
            System.out.print("HEART:   ");
            howFar(heartLength);
            if(race(deck, clubLength, diamondLength, heartLength, spadeLength).equals(userWins(input))) {
                return playerMoney += wager * getMultiplier(heartOdds);
            } else {
                return playerMoney =- wager;
            }



        }
        return playerMoney;

    }


    public static int getMultiplier(int odds) {
        if(odds == 0) {return 1;}
        if(odds == 1) {return 2;}
        if(odds == 2) {return 3;}
        if(odds == 3) {return 5;}
        if(odds == 4) {return 10;}
        else {return 0;}
    }

    public static void printMultipliers(int h, int d, int s, int c) {
        System.out.println("The dealer has determined the odds...");
        System.out.println("Hearts: " + h + "-in-1" + "\nDiamonds: " + d + "-in-1" + "\nSpades: " + s + "-in-1" + "\nClubs: " + c + "-in-1");
    }

    public static String race(Deck deck, int c, int d, int h, int s ) {

        for (int i = 8; i < 48; i++) {
            System.out.println();
            deck.getSpot(i);
            if(deck.isHeart(i)) {
                h++;
            } else if(deck.isClub(i)) {
                c++;
            } else if(deck.isDiamond(i)) {
                d++;
            } else if(deck.isSpade(i)) {
                s++;
            }

            System.out.print("DIAMOND: ");
            howFar(d);
            System.out.print("CLUB:    ");
            howFar(c);
            System.out.print("SPADE:   ");
            howFar(s);
            System.out.print("HEART:   ");
            howFar(h);
            if(h == 8) {
                return "Hearts";
            }
            else if(d == 8) {
                return "Diamonds";
            }
            else if(c == 8) {
                return "Clubs";
            } else if(s == 8) {
                return "Spades";
            }

        }
        return "";
    }

    public static void howFar(int l) {
             if(l==0) {System.out.println("A:::::::#"); }
        else if(l==1) {System.out.println(":A::::::#"); }
        else if(l==2) {System.out.println("::A:::::#"); }
        else if(l==3) {System.out.println(":::A::::#"); }
        else if(l==4) {System.out.println("::::A:::#"); }
        else if(l==5) {System.out.println(":::::A::#"); }
        else if(l==6) {System.out.println("::::::A:#"); }
        else if(l==7) {System.out.println(":::::::A#"); }
        else if(l==8) {System.out.println("::::::::A"); }
    }

    public static String userWins (String input) {
        if(input.equals("D")) { return "Diamonds";}
        if(input.equals("S")) {return "Spades";}
        if(input.equals("C")) {return "Clubs";}
        if(input.equals("H")) {return "Hearts"; }
        return "";
    }
}
