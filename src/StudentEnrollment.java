import java.util.InputMismatchException;
import java.util.Scanner; // Import Scanner for reading user input

public class StudentEnrollment {

    // Constants for maximum allowed subjects and cost per unit of study
    private static final int MAX_SUBJECTS = 10;
    private static final int FEE_PER_UNIT = 1000;

    // Nested Student class to encapsulate the information related to a student
    static class Student {
        String name;      // Name of the student
        String course;    // Course the student is enrolling in
        String courseCode; // Unique code representing the course
        int totalUnits;   // Total number of units the student is enrolling for

        // Constructor to initialize student information
        public Student(String name, String course, String courseCode, int totalUnits) {
            this.name = name;
            this.course = course;
            this.courseCode = courseCode;
            this.totalUnits = totalUnits;
        }

        // Method to calculate the total fee based on the number of units
        public int computeTotalFee() {
            return totalUnits * FEE_PER_UNIT; // Multiply units by fee per unit
        }

        // Method to print the student's enrollment details and total fees
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

        Scanner input = new Scanner(System.in); // Create scanner object to collect input

        // Prompt the user to input their name, course, and course code
        System.out.print("Enter Student Name: ");
        String studentName = input.nextLine();

        System.out.print("Enter Course: ");
        String course = input.nextLine();

        System.out.print("Enter Course Code: ");
        String courseCode = input.nextLine();

        // Ask for the number of subjects the student is enrolling in (limited by MAX_SUBJECTS)
        System.out.print("Enter Number of Subjects (Max " + MAX_SUBJECTS + "): ");
        int numberOfSubjects = input.nextInt();

        // Ensure the number of subjects does not exceed the maximum allowed
        numberOfSubjects = Math.min(numberOfSubjects, MAX_SUBJECTS); // Cap the subjects at MAX_SUBJECTS

        int totalUnits = 0; // Variable to track total enrolled units

        // Loop to collect unit count for each subject
        for (int i = 1; i <= numberOfSubjects; i++) {
            boolean validInput = false; // Boolean flag to control validation loop
            while (!validInput) {
                try {
                    System.out.print("Enter Units for Subject " + i + ": ");
                    int units = input.nextInt(); // Get the number of units for the subject
                    totalUnits += units; // Add the units to the total count
                    validInput = true; // If input is valid, exit the loop
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number for units.");
                    input.next(); // Clear the invalid input and prompt again
                }
            }
        }

        // Create a Student object using the collected information
        Student student = new Student(studentName, course, courseCode, totalUnits);

        // Show the student's enrollment details and calculate the total fee
        student.displayStudentInfo();

        // Prompt for the student's payment amount
        System.out.print("Enter Payment Amount: ");
        int payment = input.nextInt();

        // Payment processing logic: Check if the payment matches, is under, or exceeds the total fee
        int totalFee = student.computeTotalFee(); // Get the calculated total fee
        if (payment == totalFee) {
            System.out.println("Fully Paid."); // Payment exactly matches the total fee
        } else if (payment < totalFee) {
            System.out.println("Partial Payment. Amount Paid: Php " + payment);
            System.out.println("Remaining Balance: Php " + (totalFee - payment)); // Display the balance due
        } else {
            System.out.println("Overpayment! You paid more than the required fee.");
            System.out.println("Excess Amount: Php " + (payment - totalFee)); // Show the overpaid amount
        }

        // Thank the student for completing the enrollment process
        System.out.println("\nThank you for enrolling, " + studentName + "!");
    }
}
