/********************************************************************
 * @file SlotMachine.Java
 * @brief Code receives amount of money bet and shows three random symbols
 * from array, calculating score and using equation to return money back
 * @author Alison LaPat
 * @date: 11/30/2022
 * @acknowledgement: N/A
 ***********************************************************************/
import java.util.Random;
import java.util.Scanner;

public class Slots {

    public static int playSlots(int playerMoney) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        double winnings = 0.0;
        double spent = 0.0;
        int integer;
        String[] symbols = new String[]{"  X  ", "berry", "grape", "apple", "lemon", "melon"};
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("     Welcome to SlotMachine");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        boolean playSlotGame = true;
        while (playSlotGame) {
            System.out.println("You have $" + playerMoney + " in your balance.");
            System.out.print("How much would you like to bet? To exit, type '0': ");
            double bet = (double) scnr.nextInt();
            if (bet == 0){
                playSlotGame = false;
                break;
            }
            if (bet <= playerMoney && bet != 0) {
                System.out.println("The machine will now spin.");
                System.out.println(" _________  _________  _________ ");
                System.out.println("|         ||         ||         |");
                System.out.print("   ");
                int slot1 = rand.nextInt(symbols.length);
                if (slot1 == 0) {
                    System.out.print(symbols[0]);
                } else if (slot1 == 1) {
                    System.out.print(symbols[1]);
                } else if (slot1 == 2) {
                    System.out.print(symbols[2]);
                } else if (slot1 == 3) {
                    System.out.print(symbols[3]);
                } else if (slot1 == 4) {
                    System.out.print(symbols[4]);
                } else if (slot1 == 5) {
                    System.out.print(symbols[5]);
                }

                System.out.print("      ");
                int slot2 = rand.nextInt(symbols.length);
                if (slot2 == 0) {
                    System.out.print(symbols[0]);
                } else if (slot2 == 1) {
                    System.out.print(symbols[1]);
                } else if (slot2 == 2) {
                    System.out.print(symbols[2]);
                } else if (slot2 == 3) {
                    System.out.print(symbols[3]);
                } else if (slot2 == 4) {
                    System.out.print(symbols[4]);
                } else if (slot2 == 5) {
                    System.out.print(symbols[5]);
                }

                System.out.print("      ");
                int slot3 = rand.nextInt(symbols.length);
                if (slot3 == 0) {
                    System.out.print(symbols[0]);
                } else if (slot3 == 1) {
                    System.out.print(symbols[1]);
                } else if (slot3 == 2) {
                    System.out.print(symbols[2]);
                } else if (slot3 == 3) {
                    System.out.print(symbols[3]);
                } else if (slot3 == 4) {
                    System.out.print(symbols[4]);
                } else if (slot3 == 5) {
                    System.out.print(symbols[5]);
                }

                System.out.println();
                System.out.println("|         ||         ||         |");
                System.out.println(" _________  _________  _________ ");
                int score;
                if (slot1 != 0 && slot2 != 0 && slot3 != 0) {
                    if (slot1 == slot2 && slot2 == slot3) {
                        System.out.println("JACKPOT!");
                        int origbet = (int) bet;
                        score = slot1 * slot2 * slot3 * origbet;
                    } else if (slot1 == slot2 && slot1 != slot3) {
                        score = slot1 * slot2 + slot3;
                    } else if (slot1 == slot3 && slot1 != slot2) {
                        score = slot2 * slot3 + slot1;
                    } else if (slot2 == slot3 && slot1 != slot2) {
                        score = slot1 * slot3 + slot2;
                    } else {
                        score = slot1 + slot2 + slot3;
                    }
                } else {
                    score = 0;
                }

                winnings += (double) score;
                spent += bet;
                if (score == 0) {
                    System.out.println("Sorry, you did not win any money.");
                } else {
                    System.out.println("Congratulations! You win $" + score);
                }

                System.out.println("You have made $" + winnings);
                System.out.println("You have spent $" + spent);
                playerMoney = playerMoney + (int) (score - bet);
                System.out.println("You have $" + playerMoney + " left in your balance.");
                System.out.println(" ");

            } else{
                System.out.println("Sorry, you do not have enough money to play.");
                System.out.println();
                break;
            }
        }
        return playerMoney;
    }
}