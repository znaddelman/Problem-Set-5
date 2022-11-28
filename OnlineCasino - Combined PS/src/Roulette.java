import java.util.Scanner;
import java.util.Random;
public class Roulette {
    public static int[] redNumbers = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    public static int[] blackNumbers = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    public static int[] greenNumbers = {0};
    static int runRoulette(int playerMoney){
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
            if (playerMoney <= 0){
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
            if (wagerOption == 1){
                playerMoney = playColor(playerMoney, sc);
            }
            if (wagerOption == 2){
                playerMoney = playNumber(playerMoney, sc);
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
    private static int getWagerAmount(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a wager amount: ");
        int wagerAmt = sc.nextInt();
        return wagerAmt;
    }
    private static int playNumber(int playerMoney, Scanner sc){
        System.out.println("====================");
        System.out.print("Enter a number to wager on (0 - 36): ");
        int playerNum = sc.nextInt();
        int wager = getWagerAmount();
        int num = getNumber();
        int color = getColor(num);
        if (num == playerNum){
            System.out.print("OUTCOME: ");
            printOutcome(num, color);
            System.out.println("... You Win!");
            playerMoney += wager;
        } else if (num != playerNum){
            printOutcome(num, color);
            System.out.println("... You Loose!");
            playerMoney -= wager;
        }
        return playerMoney;
    }
    private static int playColor(int playerMoney, Scanner sc){
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
        if (pickColor == color){
            System.out.print("OUTCOME: ");
            printOutcome(num, color);
            System.out.println("... You win!");
            playerMoney += wagerAmt;
        }
        else if (pickColor != color){
            printOutcome(num, color);
            System.out.println("... You loose!");
            playerMoney -= wagerAmt;
        }



        return playerMoney;
    }
    private static int getColor(int num){
        boolean found = false;
        int color = 500;
        int i;
        if (num == 0){
            found = true;
            color = 3;
        }
        while (!found){
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
        return color;
    }
    private static int getNumber(){
        Random rand = new Random();
        int num = rand.nextInt(37);
        return num;
    }
    private static void printOutcome(int num, int color){
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
