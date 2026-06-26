package COdsoft;

import java.io.*;
import java.util.*;

class Student {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNumber + "," + name + "," + grade;
    }
}

public class StudentManagementSystem {

    private static final String FILE_NAME = "students.txt";
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadStudents();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    editStudent(sc);
                    break;

                case 3:
                    removeStudent(sc);
                    break;

                case 4:
                    searchStudent(sc);
                    break;

                case 5:
                    displayStudents();
                    break;

                case 6:
                    saveStudents();
                    System.out.println("Data Saved. Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);

        sc.close();
    }

    private static void addStudent(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Roll Number: ");
        int rollNumber = sc.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                System.out.println("Roll Number already exists.");
                return;
            }
        }

        sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine().trim();

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student Added Successfully.");
    }

    private static void editStudent(Scanner sc) {
        System.out.print("Enter Roll Number to Edit: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {

                System.out.print("Enter New Name: ");
                String name = sc.nextLine().trim();

                System.out.print("Enter New Grade: ");
                String grade = sc.nextLine().trim();

                if (!name.isEmpty()) {
                    s.setName(name);
                }

                if (!grade.isEmpty()) {
                    s.setGrade(grade);
                }

                System.out.println("Student Updated Successfully.");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void removeStudent(Scanner sc) {
        System.out.print("Enter Roll Number to Remove: ");
        int roll = sc.nextInt();

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student s = iterator.next();

            if (s.getRollNumber() == roll) {
                iterator.remove();
                System.out.println("Student Removed Successfully.");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void searchStudent(Scanner sc) {
        System.out.print("Enter Roll Number to Search: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("\nStudent Details");
                System.out.println("Roll Number: " + s.getRollNumber());
                System.out.println("Name: " + s.getName());
                System.out.println("Grade: " + s.getGrade());
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No Student Records Available.");
            return;
        }

        System.out.println("\nStudent Records:");

        for (Student s : students) {
            System.out.println("Roll Number: " + s.getRollNumber()
                    + " | Name: " + s.getName()
                    + " | Grade: " + s.getGrade());
        }
    }

    private static void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error Saving Data.");
        }
    }

    private static void loadStudents() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 3) {
                    int roll = Integer.parseInt(data[0]);
                    String name = data[1];
                    String grade = data[2];

                    students.add(new Student(name, roll, grade));
                }
            }

        } catch (IOException e) {
            System.out.println("Error Loading Data.");
        }
    }
}