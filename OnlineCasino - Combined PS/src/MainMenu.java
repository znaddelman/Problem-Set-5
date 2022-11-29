/**********************************************************************
 * @file MainMenu.java
 * @brief An online casino with multiple games and a banking system. The
 * games include blackjack, roulette, horse racing, and more. This page
 * is our main menu code.
 * @authors Kevin Chuang, Christian Hayden, Alison LaPat, Zach Naddelman
 * @date: 11/30/2022
 * @acknowledgement: N/A
 ***********************************************************************/

import Wordle.PlayGame;

import java.util.Scanner;
public class MainMenu {
    public static void main(String[] args) {
        int playerMoney = 1000;
        Scanner sc = new Scanner(System.in);
        boolean runMainMenu = true;
        while (runMainMenu) {

            System.out.println("=-=-=-=-=-=-=-=-=-=-Main Menu-=-=-=-=-=-=-=-=-=-=");
            System.out.println("1 - Bank Account Menu");
            System.out.println("2 - Blackjack");
            System.out.println("3 - Roulette");
            System.out.println("4 - Horse Racing");
            System.out.println("5 - Wordle");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(" ");
            System.out.print("Type a number corresponding to a menu option: ");
            int userChoice = sc.nextInt();
            playerMoney = directOutcome(userChoice, playerMoney);
        }
    }

    public static int bankMenu(int playerMoney){
        boolean bankMenuOn = true;
        Scanner sc = new Scanner(System.in);
        while (bankMenuOn) {
            System.out.println("=-=-=-=-Bank Menu-=-=-=-=");
            System.out.println("1 - Check Balance");
            System.out.println("2 - Make Deposit");
            System.out.println("3 - Exit");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("Pick a number corresponding to a menu option: ");
            int bankMenuSelection = sc.nextInt();
            if (bankMenuSelection == 1){
                System.out.println("You have $" + playerMoney + ".");
            }
            if (bankMenuSelection == 2){
                System.out.print("Enter a whole number deposit amount: ");
                int depositAmount = sc.nextInt();
                playerMoney += depositAmount;
                System.out.println("We've received your deposit of $" + depositAmount + ". You now have $" + playerMoney + " in your account.");
            }
            if (bankMenuSelection == 3){
                bankMenuOn = false;
                break;
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        return playerMoney;
    }

    public static int directOutcome(int userChoice, int playerMoney){
        if (userChoice == 1) {
            playerMoney = bankMenu(playerMoney);
        }
        if (userChoice == 2){
            playerMoney = BlackJack.runBlackjack(playerMoney);
        }
        if (userChoice == 3){
            playerMoney = Roulette.runRoulette(playerMoney);
        }
        if(userChoice == 4) {
            playerMoney = HorseRacing.raceHorses(playerMoney);
        }
        if (userChoice == 5) {
            playerMoney = PlayGame.play(playerMoney);
        }
        return playerMoney;
    }



}
