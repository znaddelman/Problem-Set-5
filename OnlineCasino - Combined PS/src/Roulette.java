/**********************************************************************
 * @file Roulette.java
 * @brief a fun roulette game that allows you to bet on both colors and numbers. I made an effort to
 * create a multitude of methods so that the code would stay organized and succinct.
 * @authors Christian Hayden
 * @date: 11/25/2022
 * @acknowledgement: N/A
 ***********************************************************************/
import java.util.Scanner;
import java.util.Random;
public class Roulette {
    public static int[] redNumbers = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36}; //list of red numbers on the roulette board
    public static int[] blackNumbers = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35}; // list of black numbers on the roulette board
    public static int[] greenNumbers = {0}; // green numbers on the roulette board(this doesn't actually get used but for clarity I added it in.)




    static int runRoulette(int playerMoney){
        //This is the primary method that the MainMenu program runs. It directs the outcomes of the game and contains the main game loop.
        //Receives player money as an input and makes that the starting amount of money. Then outputs player money amount when the code finishes.
        int wagerOption = 0;
        Scanner sc = new Scanner(System.in);
        boolean playRoulette = true;
        System.out.println("");
        System.out.println("");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("      Welcome to Roulette");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println(" ");
        while (playRoulette) {
            if (playerMoney <= 0){ //checks to make sure the player actually has money left in the bank to pay
                System.out.println("You are out of money. Get out of here!");
                playRoulette = false;
                break;
            }
            System.out.println("====================");
            System.out.println("1 - Bet on a color");
            System.out.println("2 - Bet on a number");
            System.out.println("3 - Check balance");
            System.out.println("4 - Exit");
            System.out.println("====================");
            System.out.print("Type a number corresponding to an option: ");
            wagerOption = sc.nextInt();

            //here, instead of making long if statements, I created seperate methods to bet on a color vs. bet on a number.
            if (wagerOption == 1){
                playerMoney = playColor(playerMoney, sc); //redirects control to the bet on a color method
            }
            if (wagerOption == 2){
                playerMoney = playNumber(playerMoney, sc); //redirects control to bet on a number method
            }
            if (wagerOption == 3){
                System.out.println("You have $" + playerMoney + " in your account.");
            }
            if (wagerOption == 4){
                playRoulette = false;
                break;
            }
        }
        return playerMoney;
    }
    private static int getWagerAmount(){ //asks for a wager amount from the user and returns it.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a wager amount: ");
        int wagerAmt = sc.nextInt();
        return wagerAmt;
    }
    private static int playNumber(int playerMoney, Scanner sc){ //code for betting on a number
        System.out.println("====================");
        System.out.print("Enter a number to wager on (0 - 36): ");
        int playerNum = sc.nextInt(); // gets bet number
        int wager = getWagerAmount();
        int num = getNumber();
        int color = getColor(num);
        if (num == playerNum){ // if guessed correct
            System.out.print("OUTCOME: ");
            printOutcome(num, color); //this method returns what the outcome was
            System.out.println("... You Win!");
            playerMoney += wager * 35; // pays out 35:1 odds just like standard roulette.
        } else if (num != playerNum){ // if guessed incorrect
            printOutcome(num, color);
            System.out.println("... You Loose!");
            playerMoney -= wager;
        }
        return playerMoney;
    }
    private static int playColor(int playerMoney, Scanner sc){ //Method for betting on a color. Run when the option gets selected.
        int pickColor;
        Random rand = new Random();
        System.out.println("====================");
        int wagerAmt = getWagerAmount();
        System.out.println("1 - Red");
        System.out.println("2 - Black");
        System.out.println("3 - Green");
        System.out.print("Type a number corresponding to an option: ");
        pickColor = sc.nextInt();
        int num = getNumber();
        int color = getColor(num);
        if (pickColor == color){ // if guessed correct
            System.out.print("OUTCOME: ");
            printOutcome(num, color);
            System.out.println("... You win!");
            playerMoney += wagerAmt;
        }
        else if (pickColor != color){ // if guessed incorrect
            printOutcome(num, color);
            System.out.println("... You loose!");
            playerMoney -= wagerAmt;
        }



        return playerMoney;
    }
    private static int getColor(int num){ //searches through the arrays initialized at the beginning of the code to determine if a number is red black or green.
        boolean found = false;
        int color = 500;
        int i;
        if (num == 0){ //if its zero then color is green and don't run while loop.
            found = true;
            color = 3;
        }
        while (!found){ // else scan through both arrays until the number is found.
            for (i = 0; i < redNumbers.length; i++){
                if (num == redNumbers[i]) {
                    color = 1;
                    found = true;
                }
                    }
            for (i = 0; i < blackNumbers.length; i++) {
                if (num == blackNumbers[i]) {
                    color = 2;
                    found = true;
                }
            }
        }
        return color; //returns color as an integer which later gets interpreted by the printOutcome method.
    }
    private static int getNumber(){ //gets a random number on the roulette board.
        Random rand = new Random();
        int num = rand.nextInt(37);
        return num;
    }
    private static void printOutcome(int num, int color){ // takes the number and an integer value and converts it to a color, printing a statement of what the outcome was.
        String colour = "";
        if (color == 3){
            colour = "GREEN";
        }
        if (color == 2){
            colour = "BLACK";
        }
        if (color == 1){
            colour = "RED";
        }
        System.out.print(colour + " " + num);
    }
}
