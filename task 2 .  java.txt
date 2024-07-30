import java.util.Scanner;

public class GradeCalculator {
    private static final int NUM_SUBJECTS = 5;
    private static final int MAX_MARKS = 100;
    private static final int MIN_MARKS = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numStudents = 0;
        boolean validInput = false;

        // Prompt user for number of students with error handling
        while (!validInput) {
            try {
                System.out.print("Enter number of students: ");
                numStudents = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine(); // consume the remaining input line
            }
        }

        scanner.nextLine(); // consume the newline character after nextInt()

        for (int student = 1; student <= numStudents; student++) {
            System.out.println("\nStudent " + student + ":");
            int[] marks = new int[NUM_SUBJECTS];

            // Input marks for each subject
            for (int i = 0; i < NUM_SUBJECTS; i++) {
                marks[i] = getValidMarks(scanner, i + 1);
            }

            // Calculate total marks
            int totalMarks = calculateTotalMarks(marks);

            // Calculate average percentage
            double averagePercentage = calculateAveragePercentage(totalMarks);

            // Determine grade based on average percentage
            char grade = calculateGrade(averagePercentage);

            // Display results
            displayResults(marks, totalMarks, averagePercentage, grade);
        }

        scanner.close();
    }

    // Method to input valid marks for a subject
    private static int getValidMarks(Scanner scanner, int subjectNum) {
        int marks;
        do {
            System.out.print("Enter marks for Subject " + subjectNum + " (0-100): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // consume non-integer input
            }
            marks = scanner.nextInt();
            if (marks < MIN_MARKS || marks > MAX_MARKS) {
                System.out.println("Marks should be between " + MIN_MARKS + " and " + MAX_MARKS + ".");
            }
        } while (marks < MIN_MARKS || marks > MAX_MARKS);
        return marks;
    }

    // Method to calculate total marks
    private static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Method to calculate average percentage
    private static double calculateAveragePercentage(int totalMarks) {
        return (double) totalMarks / NUM_SUBJECTS;
    }

    // Method to determine grade based on average percentage
    private static char calculateGrade(double averagePercentage) {
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }
        return grade;
    }

    // Method to display results
    private static void displayResults(int[] marks, int totalMarks, double averagePercentage, char grade) {
        System.out.println("\nMarks obtained in each subject:");
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks[i]);
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}