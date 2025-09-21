import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the To-Do List CLI App!");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    listTasks();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add a task");
        System.out.println("2. Remove a task");
        System.out.println("3. List all tasks");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            choice = -1;
        }
        return choice;
    }

    private static void addTask() {
        System.out.print("Enter the task description: ");
        String task = scanner.nextLine();
        if (!task.isEmpty()) {
            tasks.add(task);
            System.out.println("Task added.");
        } else {
            System.out.println("Empty task description is not allowed.");
        }
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }
        listTasks();
        System.out.print("Enter the task number to remove: ");
        try {
            int taskNum = Integer.parseInt(scanner.nextLine());
            if (taskNum >= 1 && taskNum <= tasks.size()) {
                String removedTask = tasks.remove(taskNum - 1);
                System.out.println("Removed task: " + removedTask);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
