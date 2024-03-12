import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {
            Student student = iterator.next();
            if (student.getRollNumber() == rollNumber) {
                iterator.remove();
                break;
            }
        }
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class StudentManagementSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Data to File");
            System.out.println("6. Load Data from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(system, scanner);
                    break;
                case 2:
                    removeStudent(system, scanner);
                    break;
                case 3:
                    searchStudent(system, scanner);
                    break;
                case 4:
                    displayAllStudents(system);
                    break;
                case 5:
                    saveToFile(system, scanner);
                    break;
                case 6:
                    loadFromFile(system, scanner);
                    break;
                case 7:
                    scanner.close();
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(StudentManagementSystem system, Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        System.out.print("Enter grade: ");
        String grade = scanner.next();
        Student student = new Student(name, rollNumber, grade);
        system.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void removeStudent(StudentManagementSystem system, Scanner scanner) {
        System.out.print("Enter roll number of student to remove: ");
        int rollNumber = scanner.nextInt();
        system.removeStudent(rollNumber);
        System.out.println("Student removed successfully.");
    }

    private static void searchStudent(StudentManagementSystem system, Scanner scanner) {
        System.out.print("Enter roll number of student to search: ");
        int rollNumber = scanner.nextInt();
        Student student = system.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayAllStudents(StudentManagementSystem system) {
        System.out.println("All Students:");
        system.displayAllStudents();
    }

    private static void saveToFile(StudentManagementSystem system, Scanner scanner) {
        System.out.print("Enter filename to save data: ");
        String filename = scanner.next();
        system.saveToFile(filename);
    }

    private static void loadFromFile(StudentManagementSystem system, Scanner scanner) {
        System.out.print("Enter filename to load data: ");
        String filename = scanner.next();
        system.loadFromFile(filename);
    }
}
