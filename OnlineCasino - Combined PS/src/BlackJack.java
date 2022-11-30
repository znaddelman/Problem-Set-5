/**********************************************************************
 * @file BlackJack.java
 * @brief: Since we already made a blackjack game, I made a wager component so that it would integrate with the rest of the project.
 * This did not take very long to do, so I made another game besides this one as well.
 * @authors Christian Hayden
 * @date: 11/28/2022
 * @acknowledgement: N/A
 ***********************************************************************/

import java.util.Scanner;
import java.util.Random;
public class BlackJack {
    public static int runBlackjack(int playerMoney) {
        //printing introduction and establishing variables & scanner
        System.out.println("");
        System.out.println("");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("      Welcome to Blackjack");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        int playerTotal, dealerTotal;
        int playerWins = 0;
        int dealerWins = 0;
        Random rand = new Random();
        boolean userPlay = true;
        Scanner scnr = new Scanner(System.in);
        int handNumber = 1;
        while (userPlay){ //game loop only stops if the user is says they don't want to keep playing.
            boolean playerBust = false; //resets player and dealer bust booleans at the beginning of the new round.
            boolean dealerBust = false;
            int potAmt = 0;
            boolean validWager = true;
            boolean playAnswer = true;
            playerTotal = 0;
            dealerTotal = 0;
            boolean stand = false;
            System.out.println("You have $" + playerMoney + " left.");
            if (playerMoney <= 0){
                System.out.println("You're out of money... get out of here!");
                userPlay = false;
                break;
            }
            System.out.println("===Hand #" + handNumber + "===");
            while (validWager) { //keeps looping until it gets a valid wager amount or reads that the user is out of money.
                System.out.println();
                System.out.print("Enter a valid wager amount: ");
                int wagerAmt = scnr.nextInt(); // asks for wager amount
                if (wagerAmt <= playerMoney) {
                    potAmt = wagerAmt; //creates a pot
                    validWager = false;
                } else if (playerMoney <= 0){
                    System.out.println("You are out of money. Please deposit more if you wish to keep playing.");
                    break;
                } else if (playerMoney < wagerAmt){
                    System.out.println("Insufficient funds.");
                }
            }
            System.out.println("Player's Turn +-+-+-+-+-+-+-+-+-+-+-");
            int tempCard = rand.nextInt(11) + 1;
            System.out.print("Card: " + tempCard);
            playerTotal = playerTotal + tempCard;
            System.out.println(" Current Total: " + playerTotal);
            tempCard = rand.nextInt(11) + 1;
            System.out.print("Card: " + tempCard);
            playerTotal = playerTotal + tempCard;
            System.out.println(" Current Total: " + playerTotal);
            while (playerTotal < 21 && !stand){
                if (userChoice() == 0) {
                    stand = true;
                    break;
                } else {
                    tempCard = rand.nextInt(11) + 1;
                    System.out.print("Card: " + tempCard);
                    playerTotal = playerTotal + tempCard;
                    System.out.println(" Current Total: " + playerTotal);
                }
                if (playerTotal > 21){
                    playerBust = true;
                    break;
                }
            }
            System.out.println("Player total is: " + playerTotal);
            System.out.println("Dealer's Turn +-+-+-+-+-+-+-+-+-+-+-");
            dealerTotal = 0;
            while (dealerTotal < 17){
                tempCard = rand.nextInt(11) + 1;
                System.out.print("Card: " + tempCard);
                dealerTotal = dealerTotal + tempCard;
                System.out.println(" Current Total: " + dealerTotal);
                if (dealerTotal > 21){
                    dealerBust = true;
                }
            }
            System.out.println("Dealer total is: " + dealerTotal);
            if (dealerBust && playerBust){
                System.out.println("You both busted!");
            } else if (dealerBust){
                System.out.println("You win!");
                playerMoney = playerMoney + potAmt;
                playerWins += 1;
            } else if (playerBust){
                System.out.println("Dealer wins!");
                playerMoney = playerMoney - potAmt;
                dealerWins += 1;
            } else if (playerTotal > dealerTotal){
                System.out.println("You win!");
                playerMoney = playerMoney + potAmt;
                playerWins += 1;
            } else if (dealerTotal > playerTotal){
                System.out.println("Dealer wins!");
                playerMoney = playerMoney - potAmt;
                dealerWins += 1;
            } else if (dealerTotal == playerTotal){
                System.out.println("Tie!");
            }

            while (playAnswer) {
                System.out.print("Play again? [y/n]: "); // asks for input to play again or not
                String playAgain = scnr.next();
                if (playAgain.equals("y") || playAgain.equals("Y")) { //keeps the mainloop going by re-establishing that userPlay is true.
                    userPlay = true;
                    playAnswer = false;
                    break;
                } else if (playAgain.equals("N") || playAgain.equals("n")){ //if they don't want to play spit out the game statistics and stop the code.
                    userPlay = false;
                    System.out.println("GAME STATISTICS----------");

                    System.out.println("Player won " + playerWins + " times, and ended with $" + playerMoney + ".");
                    System.out.println("Dealer won " + dealerWins + " times.");
                    System.out.println("-------------------------");
                    break;
                } else if (playerMoney <= 0){ //checks to see if you're out of money, and if you are then ends the game.
                    System.out.println("You ran out of money. Maybe consider quitting your gambling habits!");
                    System.out.println("GAME STATISTICS----------");
                    System.out.println("Player won " + playerWins + " times, and ended with $" + playerMoney + ".");
                    System.out.println("-------------------------");
                    break;
                }
            }
        }
        return playerMoney;
    }
    public static int userChoice() { //Asks the user to choose hit or stand
        boolean validAnswer = true;
        Scanner scnrTwo = new Scanner(System.in);
        Random randTwo = new Random();
        int userChoiceVar = 0;

        while (validAnswer) {
            System.out.print("(h)it or (s)tand?: ");
            String choice = scnrTwo.nextLine();
            if (choice.equals("h") || choice.equals("H")) {
                userChoiceVar = 1;
                validAnswer = false;

            } else if (choice.equals("S") || choice.equals("s")) {
                userChoiceVar = 0;
                validAnswer = false;

            }
        }
        return userChoiceVar;
    }
}
