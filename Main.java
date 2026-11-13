
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        int choice;

        System.out.println("==================================");
        System.out.println("    STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");

        do {

            System.out.println("\n----------- MAIN MENU -----------");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("---------------------------------");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent(manager);
                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    searchStudent(manager);
                    break;

                case 4:
                    updateStudent(manager);
                    break;

                case 5:
                    deleteStudent(manager);
                    break;

                case 6:
                    System.out.println("Thank you for using Student Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }

    // Add student
    private static void addStudent(StudentManager manager) {

        System.out.println("\n===== ADD STUDENT =====");

        int id = readInt("Enter Student ID: ");

        String name = readText("Enter Student Name: ");

        int age = readInt("Enter Student Age: ");

        String course = readText("Enter Student Course: ");

        if (id <= 0 || age <= 0) {
            System.out.println("ID and age must be positive numbers!");
            return;
        }

        Student student = new Student(id, name, age, course);

        manager.addStudent(student);
    }

    // Search student
    private static void searchStudent(StudentManager manager) {

        System.out.println("\n===== SEARCH STUDENT =====");

        int id = readInt("Enter Student ID: ");

        Student student = manager.findStudent(id);

        if (student != null) {
            System.out.println("\nStudent found!");
            student.displayStudent();
        } else {
            System.out.println("Student not found!");
        }
    }

    // Update student
    private static void updateStudent(StudentManager manager) {

        System.out.println("\n===== UPDATE STUDENT =====");

        int id = readInt("Enter Student ID to update: ");

        Student student = manager.findStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Enter new details:");

        String name = readText("Enter New Name: ");

        int age = readInt("Enter New Age: ");

        String course = readText("Enter New Course: ");

        if (age <= 0) {
            System.out.println("Age must be a positive number!");
            return;
        }

        manager.updateStudent(id, name, age, course);
    }

    // Delete student
    private static void deleteStudent(StudentManager manager) {

        System.out.println("\n===== DELETE STUDENT =====");

        int id = readInt("Enter Student ID to delete: ");

        manager.deleteStudent(id);
    }

    // Read integer safely
    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    // Read non-empty text
    private static String readText(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty() &&
                    !input.contains("\t")) {
                return input;
            }

            System.out.println("Input cannot be empty or contain tabs!");
        }
    }
}

