/**********************************************************************
 * @file HorseRacing.java
 * @brief Created a horse racing card game in Java. Inputs user's money
 * and plays until user quits.
 * @author Zach Naddelman
 * @date: 11/28/2022
 * @acknowledgement: N/A
 ***********************************************************************/
import java.util.Scanner;


public class HorseRacing extends Deck { //horse racing extends deck of cards
    public static int raceHorses(int playerMoney) {
        //printing introduction and establishing variables & scanner
        header(); //header function

        //scanner, int, String, and boolean vars
        Scanner scnr = new Scanner(System.in);
        int heartMult, diamondMult, spadeMult, clubMult;
        String input;
        boolean validWager, validInput, userPlay = true;

        //create new deck
        Deck deck = new Deck();
        deck.setDeck();

        //tells user how much money they have left
        System.out.println("You have $" + playerMoney + " left.");
        System.out.println(" ");

        //user can ask for instructions if they've never played before
        System.out.println("Press I to read instructions / Enter anything else to ignore");
        input = scnr.next();

        //if user inputs I, initiate the instructions() function (otherwise just ignore and keep going)
        if(input.equals("I")||input.equals("i")) {
            instructions();
        }

        while(userPlay) {//while the user is playing (loops until turn is over and user does not want to play anymore)
            deck.shuffle(); //deck shuffles at beginning of each round
            boolean playAnswer = true;
            validWager = true;
            validInput = true; //set booleans to true, lengths and odds = 0, wager = 0
            int heartLength = 0, diamondLength = 0, spadeLength = 0, clubLength = 0;
            int heartOdds = 0, diamondOdds = 0, spadeOdds = 0, clubOdds = 0;
            int wager = 0;

            while (validWager) { //keeps looping until it gets a valid wager amount or reads that the user is out of money.
                System.out.println();
                System.out.print("Enter wager amount: ");
                int wagerAmt = scnr.nextInt(); // asks for wager amount
                if (wagerAmt <= playerMoney) {
                    wager = wagerAmt;
                    System.out.println();
                    validWager = false;
                } else if (playerMoney <= 0){
                    System.out.println("You are out of money. Please deposit more if you wish to keep playing.");
                    break;
                } else if (playerMoney < wagerAmt){
                    System.out.println("Insufficient funds.");
                }
            }
            //game starts!!
            //dealers first 7 cards are drawn (to determine odds)
            System.out.println("\nThe initial 7 cards the dealer drew are...");
            for (int i = 0; i < 7; i++) { //for loop that uses getSpot() from deck, adds 1 to odd counter depending on what the card is
                deck.getSpot(i); //gets the card value
                if(deck.isHeart(i)) { //if card is heart, heart odds increases by 1 (repeats for each suit)
                    heartOdds++;
                } else if(deck.isClub(i)) {
                    clubOdds++;
                } else if(deck.isDiamond(i)) {
                    diamondOdds++;
                } else if(deck.isSpade(i)) {
                    spadeOdds++;
                }
            }
            //if 5 of a suit is drawn, restart (extremely rare case but could maybe happen)
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

            //set multiplier vars equal to odds through getMultiplier() function
            heartMult = getMultiplier(heartOdds);
            diamondMult = getMultiplier(diamondOdds);
            spadeMult = getMultiplier(spadeOdds);
            clubMult = getMultiplier(clubOdds);

            //delay for 2 seconds before printing the odds
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //prints odds
            printOdds(heartMult, diamondMult, spadeMult, clubMult);

            while(validInput) { //while loop to ensure that the user has made a valid input
                //ask user for input
                System.out.println("Which suit would you like to bet on?");
                System.out.println("(H for heart, C for club, D for diamonds, S for spades)");
                input = scnr.next();

                //if input is valid, break, otherwise ask again until the input is valid
                if(input.equals("C")||input.equals("c")||input.equals("D")||input.equals("d")||input.equals("H")||input.equals("h")||input.equals("S")||input.equals("s")) {
                    validInput = false;
                } else {
                    System.out.println("Invalid input. Please try again...");
                }
            }

            //print the initial board
            System.out.print("DIAMOND: ");
            howFar(diamondLength);
            System.out.print("CLUB:    ");
            howFar(clubLength);
            System.out.print("SPADE:   ");
            howFar(spadeLength);
            System.out.print("HEART:   ");
            howFar(heartLength);

            //race() runs the code!!
            if(race(deck, clubLength, diamondLength, heartLength, spadeLength).equals(userWins(input))) {  //if user's selection matches winner...
                //if suit wins, pay out the correct amount using multiplier, wager, and playerMoney variables
                if(userWins(input).equals("Diamonds")) {
                    playerMoney += wager * diamondMult;
                    System.out.println("Diamonds win!");
                }
                else if(userWins(input).equals("Hearts")) {
                    playerMoney += wager * heartMult;
                    System.out.println("Hearts Win!");
                }
                else if(userWins(input).equals("Spades")) {
                    playerMoney += wager * spadeMult;
                    System.out.println("Spades Win!");
                }
                else if(userWins(input).equals("Clubs")) {
                    playerMoney += wager * clubMult;
                    System.out.println("Clubs Win!");
                }
                //display user wins and remaining balance
                System.out.println("You win!");
                System.out.println("Your remaining balance is " + playerMoney);
            } else {
                //subtract wager, user loses, and remaining balance
                playerMoney -= wager;
                System.out.println("You lose!");
                System.out.println("Your remaining balance is " + playerMoney);
            }

            while (playAnswer) {//while loop to ask if player wants to play again
                System.out.print("Would you like to play again? [y/n]: "); // asks for input to play again or not
                String playAgain = scnr.next();
                if (playAgain.equals("y") || playAgain.equals("Y")) { //keeps the main loop going by re-establishing that userPlay is true.
                    userPlay = true;
                    break;
                } else if (playAgain.equals("N") || playAgain.equals("n")){ //if they don't want to play spit out the game statistics and stop the code.
                    userPlay = false;
                    System.out.println("GAME STATISTICS----------");

                    System.out.println("Player ended with $" + playerMoney + ".");
                    System.out.println("-------------------------");
                    break;
                } else if (playerMoney <= 0){ //checks to see if you're out of money, and if you are then ends the game.
                    System.out.println("You ran out of money. Go to the menu to deposit more!");
                    System.out.println("GAME STATISTICS----------");
                    System.out.println("Player  ended with $" + playerMoney + ".");
                    System.out.println("-------------------------");
                    break;
                }
            }


        }
        return playerMoney; //returns player money to main menu to be used for other games

    }


    public static int getMultiplier(int odds) { //returns odds based how many cards were dealt in the initial 7
        if(odds == 0) {return 1;}
        if(odds == 1) {return 2;}
        if(odds == 2) {return 3;}
        if(odds == 3) {return 5;}
        if(odds == 4) {return 10;}
        else {return 0;}
    }

    public static void printOdds(int h, int d, int s, int c) { //prints odds (played before user bets and after initial 7 cards are dealt)
        System.out.println("\nThe dealer has determined the odds...");
        System.out.println("Hearts: " + h + "-in-1" + "\nDiamonds: " + d + "-in-1" + "\nSpades: " + s + "-in-1" + "\nClubs: " + c + "-in-1");
    }

    public static String race(Deck deck, int c, int d, int h, int s ) { //game function, happens after user bets

        for (int i = 8; i < 52; i++) { //start at the 8th card (skip the first 7 since they were already drawn)
            //delay 2 seconds to give user time to process what's going on
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //print user card
            System.out.println("\nThe dealer has drawn a...");
            deck.getSpot(i);

            //count how much each suit has been drawn (add by 1 when the suit is drawn)
            if(deck.isHeart(i)) {
                h++;
            } else if(deck.isClub(i)) {
                c++;
            } else if(deck.isDiamond(i)) {
                d++;
            } else if(deck.isSpade(i)) {
                s++;
            }

            //give player 2 seconds to process the board changing
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //display the game board, user can visualize how each suit is doing
            System.out.print("DIAMOND: ");
            howFar(d);
            System.out.print("CLUB:    ");
            howFar(c);
            System.out.print("SPADE:   ");
            howFar(s);
            System.out.print("HEART:   ");
            howFar(h);

            //when a suit has been drawn 8 times, return the suit
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
        return ""; //empty string so that the code doesn't yell at me
    }

    public static void howFar(int l) { //how far function inputs the number of cards in a suit drawn and prints the appropriate game board for each suit
        //(used in race())
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

    public static String userWins (String input) { //return user input
             if(input.equals("D")||input.equals("d")) { return "Diamonds";}
        else if(input.equals("S") ||input.equals("s")) {return "Spades";}
        else if(input.equals("C")||input.equals("c")) {return "Clubs";}
        else if(input.equals("H")||input.equals("h")) {return "Hearts"; }
        return "";
    }

    public static void instructions() { //instructions method
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("    How To Play Horse Racing    ");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("The dealer extracts the four aces from the deck and places them in a column. " +
                        "\nThe cards are then shuffled and cut, and the dealer deals seven cards face up in a " +
                        "\nhorizontal row a long the top of the layout to mark out the course as shown in the diagram.");
        System.out.println("\nThe dealer sets the odds on each horse based on how many cards are dealt:");
        System.out.println("0 cards: 1-in-1");
        System.out.println("1 card: 2-in-1");
        System.out.println("2 cards: 3-in-1");
        System.out.println("3 cards: 5-in-1");
        System.out.println("4 cards: 10-in-1");
        System.out.println("(5 or more cards is rare, but resets the deck because it's impossible for that suit to win)");
        System.out.println("The dealer deals cards from the remainder of the deck one at a time face up onto a pile. " +
                "\nEach time a card is dealt, the horse of that suit moves one space to the right along the course. The " +
                "\nfirst horse to cross the finish line (which will happen when eight cards of that suit have been dealt) wins" +
                "\n the race. The dealer pays out the bets on the winning horse and collects the bets on the others.");

    }

    public static void header() { //header method
        System.out.println();
        System.out.println();
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("    Welcome to Horse Racing     ");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
    }
}
