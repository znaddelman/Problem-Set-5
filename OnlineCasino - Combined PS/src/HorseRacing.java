/**********************************************************************
 * @file HorseRacing.java
 * @brief Created a horse racing card game in Java. Inputs user's money
 * and plays until user quits
 * @author Zach Naddelman
 * @date: 11/28/2022
 * @acknowledgement: N/A
 ***********************************************************************/
import java.util.Scanner;


public class HorseRacing extends Deck { //horse racing extends deck of cards
    public static int raceHorses(int playerMoney) {
        //printing introduction and establishing variables & scanner
        System.out.println("");
        System.out.println("");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("    Welcome to Horse Racing     ");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        Scanner scnr = new Scanner(System.in);



        int heartMult, diamondMult, spadeMult, clubMult;
        String input;

        boolean validWager, userPlay = true;

        Deck deck = new Deck();
        deck.setDeckNoAces(); //sets deck without aces (aces are what are being moved along)
        deck.shuffle();

        System.out.println("You have $" + playerMoney + " left.");
        System.out.println(" ");
        while(userPlay) {

            boolean playAnswer = true;
            validWager = true;
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

            input = scnr.next();

            System.out.print("DIAMOND: ");
            howFar(diamondLength);
            System.out.print("CLUB:    ");
            howFar(clubLength);
            System.out.print("SPADE:   ");
            howFar(spadeLength);
            System.out.print("HEART:   ");
            howFar(heartLength);
            System.out.println(playerMoney + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if(race(deck, clubLength, diamondLength, heartLength, spadeLength).equals(userWins(input))) {
                System.out.println(userWins(input) + " !!!!!!!!!!!EIJFDSLJFLSKDFJM");
                System.out.println("Wager: " + wager);
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
                else{
                    System.out.println("Did not work!");
                }

                System.out.println("You win!");
                System.out.println("Your remaining balance is " + playerMoney);
            } else {
                System.out.println(userWins(input) + " !!!!!!!!!!!EIJFDSLJFLSKDFJM");
                playerMoney -= wager;
                System.out.println("You lose!");
                System.out.println("Your remaining balance is " + playerMoney);
            }

            while (playAnswer) {
                System.out.print("Would you like to play again? [y/n]: "); // asks for input to play again or not
                String playAgain = scnr.next();
                if (playAgain.equals("y") || playAgain.equals("Y")) { //keeps the mainloop going by re-establishing that userPlay is true.
                    userPlay = true;
                    playAnswer = false;
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
        else if(input.equals("S")) {return "Spades";}
        else if(input.equals("C")) {return "Clubs";}
        else if(input.equals("H")) {return "Hearts"; }
        return "";
    }
}
