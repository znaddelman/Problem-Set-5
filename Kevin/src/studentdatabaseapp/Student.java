/*/
Student.java
No problem!
Kevin Chuang
11/21/2022
Code via JAVA Project-Student Management
/*/

package studentdatabaseapp;
import java.util.Scanner;
public class Student {

    private String firstName;
    private String lastName;
    private int gradeYear;
    private String studentID;
    private String courses = "";
    private int tuitionBalance = 0;
    private static int costOfCourse = 600;
    private static int id = 1000;

    public Student(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter student first name: ");
        this.firstName = in.nextLine();

        System.out.print("Enter student last name: ");
        this.lastName = in.nextLine();

        System.out.print("1 - Freshmen\n2 - Sophmore\n3 - Junior\n4 - Senior\nEnter student class level: ");
        this.gradeYear = in.nextInt();

        setStudentID();

    }
    private void setStudentID(){
        id++;
        this.studentID = gradeYear + "" + id;
    }
    public void enroll(){
        do {
            System.out.print("Enter course to enroll (Q to quit): ");
            Scanner in = new Scanner(System.in);
            String course = in.nextLine();
            if (!course.equals ("Q")) {
                courses = courses + "\n " + course;
                tuitionBalance = tuitionBalance + costOfCourse;
            }
            else {
                System.out.println("BREAK!");
                break;
            }
        } while (1 != 0);
    }
    public void viewBalance(){
        System.out.println("Your balance is: $" + tuitionBalance);
    }
    public void payTuition(){
        viewBalance();
        System.out.print("Enter your payment: $");
        Scanner in = new Scanner(System.in);
        int payment = in.nextInt();
        tuitionBalance = tuitionBalance - payment;
        System.out.println("Thank you for your payment of $" + payment);
        viewBalance();
    }
    public String showInfo(){
        return "Name:" + firstName + " " + lastName +
                "\nGraade Level: " + gradeYear +
                "\nStudent ID: " + studentID +
                "\nCourses Enrolled:" + courses +
                "\nBalance: $" + tuitionBalance;
    }
}
