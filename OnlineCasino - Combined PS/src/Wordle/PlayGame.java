package Wordle;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class PlayGame {
    public static int play (int playerMoney) {


        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("      WELCOME TO WORDLE");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");

        System.out.println("Enter the wager amount: ");
        Scanner sc = new Scanner(System.in);
        int wagerAmount = sc.nextInt();



        String[] words = {"MONEY", "EARTH", "CABLE", "RAISE", "GHOST", "PEACH", "ALONE", "UNITE", "YOUTH", "AWARD"};
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
            System.out.println("Congratulations, you guessed correctly");
            playerMoney = playerMoney - wagerAmount;
        } else {
            System.out.println("Incorrect, The answer was " + board.getWord());
        }
        input.close();
        return playerMoney;
    }
}
