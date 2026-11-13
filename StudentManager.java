
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class StudentManager {

    private ArrayList<Student> students;
    private final String FILE_NAME = "students.txt";

    // Constructor
    public StudentManager() {
        students = new ArrayList<>();
        loadStudents();
    }

    // Add student
    public boolean addStudent(Student student) {

        if (findStudent(student.getId()) != null) {
            System.out.println("Student ID already exists!");
            return false;
        }

        students.add(student);
        saveStudents();

        System.out.println("Student added successfully!");
        return true;
    }

    // Display all students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n===== STUDENT RECORDS =====");

        for (Student student : students) {
            student.displayStudent();
        }
    }

    // Search student by ID
    public Student findStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // Update student
    public boolean updateStudent(int id, String name,
                                 int age, String course) {

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return false;
        }

        student.setName(name);
        student.setAge(age);
        student.setCourse(course);

        saveStudents();

        System.out.println("Student updated successfully!");
        return true;
    }

    // Delete student
    public boolean deleteStudent(int id) {

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return false;
        }

        students.remove(student);
        saveStudents();

        System.out.println("Student deleted successfully!");
        return true;
    }

    // Save student records to file
    private void saveStudents() {

        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student student : students) {

                String record = student.getId() + "\t"
                        + student.getName() + "\t"
                        + student.getAge() + "\t"
                        + student.getCourse();

                writer.write(record);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    // Load student records from file
    private void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\t", -1);

                if (data.length != 4) {
                    System.out.println("Skipping invalid record: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String course = data[3];

                    if (id <= 0 || age <= 0 ||
                            name.isBlank() || course.isBlank() ||
                            findStudent(id) != null) {
                        System.out.println("Skipping invalid record: " + line);
                        continue;
                    }

                    students.add(
                        new Student(id, name, age, course)
                    );

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid record: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
    }
}
