import java.util.InputMismatchException;
import java.util.Scanner; // Importing Scanner class for user input

public class StudentEnrollment {

    // Constants for maximum subjects and fee per unit
    private static final int MAX_SUBJECTS = 10;
    private static final int FEE_PER_UNIT = 1000;

    // Student class to store individual student information
    static class Student {
        String name;
        String course;
        String courseCode;
        int totalUnits;

        // Constructor to initialize a student
        public Student(String name, String course, String courseCode, int totalUnits) {
            this.name = name;
            this.course = course;
            this.courseCode = courseCode;
            this.totalUnits = totalUnits;
        }

        // Method to compute the total fee based on the number of units
        public int computeTotalFee() {
            return totalUnits * FEE_PER_UNIT;
        }

        // Method to display student details and total enrollment fee
        public void displayStudentInfo() {
            System.out.println("\n--- Enrollment Summary ---");
            System.out.println("Student Name: " + name);
            System.out.println("Course: " + course);
            System.out.println("Course Code: " + courseCode);
            System.out.println("Total Units: " + totalUnits);
            System.out.println("Total Enrollment Fee: Php " + computeTotalFee());
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); // Scanner object to get input from the user

        // Input student name, course, and course code
        System.out.print("Enter Student Name: ");
        String studentName = input.nextLine();

        System.out.print("Enter Course: ");
        String course = input.nextLine();

        System.out.print("Enter Course Code: ");
        String courseCode = input.nextLine();

        // Input number of subjects
        System.out.print("Enter Number of Subjects (Max " + MAX_SUBJECTS + "): ");
        int numberOfSubjects = input.nextInt();

        // Ensure that the number of subjects is within the allowed limit
        numberOfSubjects = Math.min(numberOfSubjects, MAX_SUBJECTS); // Restrict the input to max subjects

        int totalUnits = 0; // To store total units

        // Input the number of units for each subject
        for (int i = 1; i <= numberOfSubjects; i++) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Enter Units for Subject " + i + ": ");
                    int units = input.nextInt();
                    totalUnits += units; // Add the units to the total
                    validInput = true; // If no exception, input is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number for units.");
                    input.next(); // Consume the invalid input
                }
            }
        }

        // Create a Student object and store the enrollment information
        Student student = new Student(studentName, course, courseCode, totalUnits);

        // Display the student's information and computed total fee
        student.displayStudentInfo();

        // Ask for the payment amount
        System.out.print("Enter Payment Amount: ");
        int payment = input.nextInt();

        // Payment handling logic
        int totalFee = student.computeTotalFee(); // Calculate total fee
        if (payment == totalFee) {
            System.out.println("Fully Paid.");
        } else if (payment < totalFee) {
            System.out.println("Partial Payment. Amount Paid: Php " + payment);
            System.out.println("Remaining Balance: Php " + (totalFee - payment));
        } else {
            System.out.println("Overpayment! You paid more than the required fee.");
            System.out.println("Excess Amount: Php " + (payment - totalFee));
        }

        // Thank the student
        System.out.println("\nThank you for enrolling, " + studentName + "!");
    }
}
