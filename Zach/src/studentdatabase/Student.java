
package studentdatabase;

import java.util.Scanner;

public class Student {
    private String first, last, year, courses, studentID;
    private int tuition, yearInput;
    private static final int cost = 600;
    private static int id = 1000;


    //constructor asks student to enter first name, last name, and grade. it also generates a student ID
    public Student() {
        //asks for first name
        Scanner scnr = new Scanner(System.in);
        System.out.print("\nEnter student's first name ");
        this.first = scnr.nextLine();

        //asks for last name
        System.out.print("Enter student's last name ");
        this.last = scnr.nextLine();


        this.yearInput = 0;

        //only accepts input from 1-4
        // sets year as freshman, sophomore, junior, senior based on user input
        while(this.yearInput != 1 && this.yearInput != 2 && this.yearInput != 3 && this.yearInput != 4) {
            System.out.print("Enter student's grade/year (1 - freshman, 2 - sophomore, 3 - junior, 4 - senior) ");
            this.yearInput = scnr.nextInt();
            if(this.yearInput == 1) {this.year = "Freshman";}
            else if(this.yearInput == 2) {this.year = "Sophomore";}
            else if(this.yearInput == 3) {this.year = "Junior";}
            else if(this.yearInput == 4) {this.year = "Senior";}
        }
        setID(); //calls setID function

    }

    //method to generate id
    private void setID() {
        id++; //add 1 to id
        this.studentID = yearInput + "" + id; //student year (1-4) + id
    }
    //enroll in courses
    public void enroll() { //enroll method
        String course = "";
        Scanner scnr = new Scanner(System.in); //scanner

        //while user has not quit the function...
        while(!course.equals("Q")) {
            System.out.print("Enter course to enroll (Press 'Q' to quit) "); //ask user for input
            course = scnr.nextLine();
            if (!course.equals("Q")) { //if user did not quit, add it to the course
                courses += "\n " + course;
                tuition += cost; //add 600 to tuition
            } else {break;}
        }
    }

    //view balance
    public void viewBalance() { System.out.println("Your balance is: $" + tuition); } //view balance print statement
    //pay tuition
    public void payTuition() { //subtract payment by tuition
        Scanner scnr = new Scanner(System.in);
        viewBalance();
        int payment = 99999999;
        while(tuition < payment) { //while loop ensures that the user does not enter a payment larger than the tuition
            System.out.print("Enter payment amount: $");
            payment = scnr.nextInt();
            if(tuition > payment) {
                tuition -= payment;
                System.out.println("You have paid $" + payment);
                viewBalance();
                break;
            } else {
                System.out.println("Error! The payment is larger than the tuition");
            }

        }

    }


    //print information(show status)

    public String toString() {
        return "\nName: " + this.first + " " + this.last +
                "\n Grade:  " + this.year +
                "\n Student ID: " + this.studentID +
                "\nCourses Enrolled: " + this.courses +
                "\nBalance: $" + this.tuition;
    }
}
