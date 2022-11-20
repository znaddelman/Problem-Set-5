/******************************************************************************************************************************
 * @StudentDatabaseApp.java
 * @breif Copying the video proved to be very easy, so I took the liberty to add in a menu system and a way of individually paying tuition as opposed to enrolling one batch of students and paying their tuition at the same time.
 * @author Christian Hayden
 * @date 19 November 2022
 * @acknowledgement: n/a
 *******************************************************************************************************************************/
import java.util.Scanner;
public class StudentDatabaseApp {

    public static void main(String[] args) { // where control starts
        runMainMenu();
    }
    public static void runMainMenu(){
        Student[] studentsList = new Student[500]; // database can hold up to 500 students
        //creating variables and sertting things up
        int numEnrolling;
        Scanner scnr = new Scanner(System.in);
        boolean run = true;
        int totalStudents = 0;
        int i;
        boolean found = false;
        String idIn;
        int selectionValue;
        while (run) { //loop to perpetuate the main menu
            System.out.println("=========================STUDENT DATABASE============================"); // prints out the main menu options
            System.out.println("=-=-=-=-=-=-=-Main Menu-=-=-=-=-=-=-=-=-");
            System.out.println("1 - Enroll New Students");
            System.out.println("2 - View Entire Directory");
            System.out.println("3 - Get Single Student Information");
            System.out.println("4 - Pay Tuition");
            System.out.println("5 - Quit");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("");
            System.out.print("Type a number corresponding to a command: ");
            selectionValue = scnr.nextInt();
            scnr.nextLine();


            if (selectionValue == 1) { //if statements for each possible selection in the menu.
                System.out.print("Enter the number of students you want to enroll: ");
                numEnrolling = scnr.nextInt();
                for (i = 0 + totalStudents; i < numEnrolling + totalStudents; i++) { // for loop that creates one enrollment for each selected by the user
                    studentsList[i] = new Student();
                    studentsList[i].enrollStudent(); //calls on the enroll method
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-NEW STUDENT PROFILE-=-=-=-=-=-=-=-=-=-=-=");
                    studentsList[i].printStudentInformation();
                }
                totalStudents += numEnrolling;
            }

            if (selectionValue == 2) {
                System.out.println(totalStudents);
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-STUDENT DIRECTORY-=-=-=-=-=-=-=-=-=-=-=-");
                for (i = 0; i < totalStudents; i++) { // for loop that moves through the studentsList array once for each student. Calls on the printStudentInformation command from "Student" class.
                    if (studentsList[i] != null) {
                        studentsList[i].printStudentInformation();
                    }
                }
            }
            if (selectionValue == 3) {
                found = false;
                boolean optionFourRun = true;
                while (optionFourRun){
                    System.out.print("Type the SID of the student(or type 'EXIT'): ");
                    idIn = scnr.nextLine();
                    if (idIn.equals("EXIT")){
                        optionFourRun = false;
                        break;
                    }
                    for (i = 0; i < totalStudents; i++) { //searches through each student to see if the input matches any student ID's. If it does not then it prints not found.
                        if (studentsList[i].returnID().equals(idIn)) {
                            studentsList[i].printStudentInformation();
                            i = totalStudents;
                            optionFourRun = false;
                            found = true;
                        }
                    }
                    if (found == false){
                        System.out.println("SID Not Found...");
                    }
                }
            }
            if (selectionValue == 4) {
                found = false;
                boolean optionFourRun = true;
                while (optionFourRun){
                    System.out.print("Type the SID of the student(or type 'EXIT'): ");
                    idIn = scnr.nextLine();
                    if (idIn.equals("EXIT")){
                        optionFourRun = false;
                        break;
                    }
                    for (i = 0; i < totalStudents; i++) {//searches through each student to see if the input matches any student ID's. If it does not then it prints not found.
                        if (studentsList[i].returnID().equals(idIn)) {
                            studentsList[i].payTuition();
                            i = totalStudents;
                            optionFourRun = false;
                            found = true;
                        }
                    }
                    if (found == false){
                        System.out.println("SID Not Found...");
                    }
                }
            }

            if (selectionValue == 5){ // command to exit app
                run = false;
                break;
            }
        }
    }
}
