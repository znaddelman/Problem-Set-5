import java.util.Random;
import java.util.Scanner;

public class PlayGame {
    public static int play(int playerMoney) {


        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("      WELCOME TO WORDLE");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");

        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the wager amount: ");


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
        } else {
            System.out.println("Incorrect, The answer was " + board.getWord());
        }
        input.close();
        return playerMoney;
    }
}
