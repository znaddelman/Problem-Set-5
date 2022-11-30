package Wordle;

/**********************************************************************
 * @file Roulette.java
 * @brief I made a Wordle game that the user can bet on. Usually a user has 6 chances to
   guess the word but because we are a casino, I limited it to only 5 chances of guessing.
 * @authors Kevin Chuang
 * @date: 11/27/2022
 * @acknowledgement: TA: Ash Rai
 ***********************************************************************/

import java.util.Random;
import java.util.Scanner;

public class PlayGame {
    public static int play(int playerMoney) {


        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("      WELCOME TO WORDLE");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("You have $" + playerMoney + " left.");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the wager amount: ");
        int wagerAmount = sc.nextInt();


        String[] words = {"MONEY", "EARTH", "CABLE", "RAISE", "GHOST", "PEACH", "ALONE", "UNITE", "YOUTH", "AWARD", "WATER", "BLOCK", "ZEBRA"};
        String answer = words[new Random().nextInt(words.length)].toUpperCase();

        Board board = new Board(words);

        Scanner input = new Scanner(System.in);

        Display display = new Display(board, input);
        while (!board.isGameOver()) {
            display.print();
            display.promptGuess();
        }
        display.print();
        if (board.didWin()) {
            playerMoney = playerMoney + wagerAmount;
            System.out.println("You won, you now have $" + playerMoney);
        } else {
            playerMoney = playerMoney - wagerAmount;
            System.out.println("Incorrect, The answer was " + board.getWord());
            System.out.println("you now have $" + playerMoney);
            }
        if (playerMoney<=0){
            System.out.println("You're broke, GET OUT!");
        }

        input.close();
        return playerMoney;
    }
}
