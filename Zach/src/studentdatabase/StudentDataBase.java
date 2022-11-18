package studentdatabase;
import java.util.Scanner;

public class StudentDataBase {
    public static void main(String[] args) {
        header();

        //user inputs the number of students to enroll
        System.out.print("Enter the number of students to enroll: ");
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();

        //students array
        Student[] students = new Student[n];

        //bulk of the code, for loop goes through each iteration of the array
        for(int i = 0; i < n; i++) {
            students[i] = new Student(); //creates student using Student class
            students[i].enroll(); //enroll function
            students[i].payTuition(); //pay tuition function

        }
        for (int i = 0; i < n; i++) {
            System.out.println(students[i].toString()); //after user inputs, print the students entered
        }

    }
    public static void header() { //header
        System.out.println("=================================");
        System.out.println("====STUDENT MANAGEMENT SYSTEM====");
        System.out.println("=================================\n");
    }
}
