import java.util.Scanner;
import java.util.ArrayList;

public class StudentManagement {
    
    // Student data store cheyadaniki ArrayList
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Integer> ages = new ArrayList<>();
    static ArrayList<Integer> marks = new ArrayList<>();
    static ArrayList<String> classes = new ArrayList<>();
    
    static Scanner sc = new Scanner(System.in);
    static int studentCount = 0;
    
    public static void main(String[] args) {
        
        System.out.println("=====================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("=====================================");
        
        int choice;
        
        // Menu loop
        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. View Statistics");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear
            
            // Switch case for menu options
            switch(choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    viewStatistics();
                    break;
                case 0:
                    System.out.println("\nThank you! Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Try again.");
            }
            
        } while(choice != 0);
    }
    
    // ============ ADD STUDENT ============
    public static void addStudent() {
        System.out.println("\n----- ADD NEW STUDENT -----");
        
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        
        System.out.print("Enter Marks (0-100): ");
        int mark = sc.nextInt();
        sc.nextLine(); // buffer clear
        
        System.out.print("Enter Class (10/11/12): ");
        String cls = sc.nextLine();
        
        // Add to arrays
        names.add(name);
        ages.add(age);
        marks.add(mark);
        classes.add(cls);
        
        studentCount++;
        
        System.out.println("\n✓ Student added successfully!");
        System.out.println("Student ID: " + studentCount);
    }
    
    // ============ VIEW ALL STUDENTS ============
    public static void viewAllStudents() {
        System.out.println("\n----- ALL STUDENTS -----");
        
        if(studentCount == 0) {
            System.out.println("No students found!");
            return;
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("ID\tName\t\tAge\tClass\tMarks\tGrade");
        System.out.println("--------------------------------------------------");
        
        for(int i = 0; i < names.size(); i++) {
            String grade = getGrade(marks.get(i));
            System.out.println((i+1) + "\t" + names.get(i) + "\t\t" + 
                              ages.get(i) + "\t" + classes.get(i) + "\t" + 
                              marks.get(i) + "\t" + grade);
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("Total Students: " + studentCount);
    }
    
    // ============ SEARCH STUDENT ============
    public static void searchStudent() {
        System.out.println("\n----- SEARCH STUDENT -----");
        
        if(studentCount == 0) {
            System.out.println("No students to search!");
            return;
        }
        
        System.out.print("Enter student name to search: ");
        String searchName = sc.nextLine();
        
        boolean found = false;
        
        for(int i = 0; i < names.size(); i++) {
            // Check if name contains search text
            if(names.get(i).toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println("\n--- Student Found ---");
                System.out.println("ID: " + (i+1));
                System.out.println("Name: " + names.get(i));
                System.out.println("Age: " + ages.get(i));
                System.out.println("Class: " + classes.get(i));
                System.out.println("Marks: " + marks.get(i));
                System.out.println("Grade: " + getGrade(marks.get(i)));
                found = true;
            }
        }
        
        if(!found) {
            System.out.println("Student not found!");
        }
    }
    
    // ============ UPDATE STUDENT ============
    public static void updateStudent() {
        System.out.println("\n----- UPDATE STUDENT -----");
        
        if(studentCount == 0) {
            System.out.println("No students to update!");
            return;
        }
        
        // First show all students
        viewAllStudents();
        
        System.out.print("\nEnter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // buffer clear
        
        // Check valid ID
        if(id < 1 || id > names.size()) {
            System.out.println("Invalid ID!");
            return;
        }
        
        int index = id - 1; // Array index
        
        System.out.println("\nCurrent Details:");
        System.out.println("Name: " + names.get(index));
        System.out.println("Age: " + ages.get(index));
        System.out.println("Class: " + classes.get(index));
        System.out.println("Marks: " + marks.get(index));
        
        System.out.println("\nEnter new details:");
        
        System.out.print("Enter new Name: ");
        String newName = sc.nextLine();
        
        System.out.print("Enter new Age: ");
        int newAge = sc.nextInt();
        
        System.out.print("Enter new Marks: ");
        int newMarks = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter new Class: ");
        String newClass = sc.nextLine();
        
        // Update values
        names.set(index, newName);
        ages.set(index, newAge);
        marks.set(index, newMarks);
        classes.set(index, newClass);
        
        System.out.println("\n✓ Student updated successfully!");
    }
    
    // ============ DELETE STUDENT ============
    public static void deleteStudent() {
        System.out.println("\n----- DELETE STUDENT -----");
        
        if(studentCount == 0) {
            System.out.println("No students to delete!");
            return;
        }
        
        // Show all students
        viewAllStudents();
        
        System.out.print("\nEnter Student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        // Check valid ID
        if(id < 1 || id > names.size()) {
            System.out.println("Invalid ID!");
            return;
        }
        
        int index = id - 1;
        
        System.out.println("Deleting: " + names.get(index));
        System.out.print("Are you sure? (y/n): ");
        String confirm = sc.nextLine();
        
        if(confirm.equalsIgnoreCase("y")) {
            names.remove(index);
            ages.remove(index);
            marks.remove(index);
            classes.remove(index);
            studentCount--;
            
            System.out.println("\n✓ Student deleted successfully!");
        } else {
            System.out.println("Delete cancelled.");
        }
    }
    
    // ============ VIEW STATISTICS ============
    public static void viewStatistics() {
        System.out.println("\n----- STATISTICS -----");
        
        if(studentCount == 0) {
            System.out.println("No students in system!");
            return;
        }
        
        // Calculate total marks
        int totalMarks = 0;
        int highest = marks.get(0);
        int lowest = marks.get(0);
        int passCount = 0;
        int gradeACount = 0;
        
        for(int i = 0; i < marks.size(); i++) {
            int m = marks.get(i);
            totalMarks += m;
            
            // Find highest
            if(m > highest) {
                highest = m;
            }
            
            // Find lowest
            if(m < lowest) {
                lowest = m;
            }
            
            // Count pass (>=35)
            if(m >= 35) {
                passCount++;
            }
            
            // Count Grade A (>=80)
            if(m >= 80) {
                gradeACount++;
            }
        }
        
        double average = (double) totalMarks / studentCount;
        int failCount = studentCount - passCount;
        
        System.out.println("---------------------------");
        System.out.println("Total Students  : " + studentCount);
        System.out.println("Highest Marks   : " + highest);
        System.out.println("Lowest Marks    : " + lowest);
        System.out.println("Average Marks   : " + String.format("%.2f", average));
        System.out.println("Pass Count      : " + passCount);
        System.out.println("Fail Count      : " + failCount);
        System.out.println("Grade A Students: " + gradeACount);
        System.out.println("---------------------------");
    }
    
    // ============ GET GRADE ============
    public static String getGrade(int marks) {
        if(marks >= 90) return "A+";
        if(marks >= 80) return "A";
        if(marks >= 70) return "B";
        if(marks >= 60) return "C";
        if(marks >= 35) return "D";
        return "F";
    }
}
