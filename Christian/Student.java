import java.util.Scanner;
public class Student {
    private String firstName;
    private String lastName;
    private int schoolYear;
    private String classes;
    private String sid;
    private int tuitionBalance;
    private static int costOfCourses = 600;
    private static int id = 1000;
    private static boolean done = false;

    public Student(){ //constructor class to get student information. Sets ID when done.
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter new student first name: ");
        this.firstName = scnr.nextLine();
        System.out.print("Enter new student last name: ");
        this.lastName = scnr.nextLine();
        System.out.print("Enter new student grade 1-4 (Freshman(1), Sophomore(2), Junior(3), Senior(4)): ");
        this.schoolYear = scnr.nextInt();
        setID();
    }
    private String setID() {
        id++;
        this.sid = schoolYear + "" +  id;
        return sid;
    }

    public void enrollStudent(){ //enrolls a singular student
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter a course to enroll(type 'QUIT' to stop): ");
        String course;
        classes = scnr.nextLine();
        tuitionBalance = tuitionBalance + costOfCourses;
        while (!done) {
            System.out.print("Enter a course to enroll(type 'QUIT' to stop): ");
            course = scnr.nextLine();
            if (course.equals("QUIT")) {
                done = false;
                break;
            } else {
                classes = classes + "\n" + course;
                tuitionBalance += costOfCourses;
            }
        }
    }
    public void payTuition(){ //pays tuition for student, deducting the desired amount from the total tuition amount(calculated in enrollStudent method)
        Scanner scnr = new Scanner(System.in);
        System.out.print("Type payment amount: ");
        int paymentAmt = scnr.nextInt();
        tuitionBalance = tuitionBalance - paymentAmt;
        System.out.println("You paid $" + paymentAmt + ".");
        System.out.println("Remaining balance is $" + tuitionBalance + ".");
    }

    public void printStudentInformation(){ //prints out the students information
        String whichYr = getYear(schoolYear);
        System.out.println("Full Name: " + firstName + " " + lastName);
        System.out.println("Student ID: " + sid);
        System.out.println("Year: " + whichYr);
        System.out.println("Tuition balance: $" + tuitionBalance);
        System.out.println("-=-=-=-=-=-=-=-=-");
        System.out.println("Courses:\n" + classes);
        System.out.println("-=-=-=-=-=-=-=-=-");
        System.out.println("==============================================================");
    }


    public String getYear(int numYear){ //takes 1, 2, 3, or 4 as an input and converts it to the corresponding year, returning a string.
        String year;
        year = "";
        if (numYear == 1){
            year = "Freshman";
        }
        if (numYear == 2){
            year = "Sophomore";
        }
        if (numYear == 3){
            year = "Junior";
        }
        if (numYear == 4){
            year = "Senior";
        }
        return year;
    }
    public String returnID(){ // returns Student ID
        return this.sid;
    }
}
