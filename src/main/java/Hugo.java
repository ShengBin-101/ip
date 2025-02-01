import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hugo {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        displayWelcomeMessage();
        askInput();
    }

    public static void askInput() {
        boolean isAskingInput = true;
        String userInputLine;
        Scanner scanner = new Scanner(System.in);
        while (isAskingInput) {
            userInputLine = scanner.nextLine();
            String[] inputs = userInputLine.split(" ");
            if (inputs[0].equals("")) {
                // Handle empty input
                Formatter.printLine();
                Formatter.printMessage("Please enter a command or task name to add");
                Formatter.printLine();
            } else {
                switch (inputs[0]) {
                case "deadline":
                    addDeadline(inputs);
                    break;
                case "todo":
                    addToDo(userInputLine);
                    break;
                case "event":
                    addEvent(inputs);
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTaskAsDone(inputs);
                    break;
                case "unmark":
                    markTaskAsUndone(inputs);
                    break;
                case "bye":
                    displayExitMessage();
                    isAskingInput = false;
                    break;
                default:
//                    addTask(userInputLine);
                    addToDo(userInputLine);
                    break;
                }
            }
        }
    }

    public static void listTasks() {
        Formatter.printTaskList(tasks);
    }

    public static void markTaskAsDone(String[] inputs) {
        if (inputs.length > 1) {
            int taskId = Integer.parseInt(inputs[1]) - 1;
            if (taskId < 0 || taskId >= tasks.size()) {
                Formatter.printLine();
                Formatter.printMessage("Invalid task id.");
                Formatter.printLine();
            } else {
                if (tasks.get(taskId).getIsDone()) {
                    Formatter.printLine();
                    Formatter.printMessage("Task is already marked.");
                    Formatter.printLine();
                    return;
                }
                tasks.get(taskId).markAsDone();
                Formatter.printTaskStatusChange("Nice! I've marked this task as done:",
                        tasks.get(taskId), tasks.size());
            }
        } else {
            Formatter.printLine();
            Formatter.printMessage("Please enter task number to mark!");
            Formatter.printLine();
        }
    }

    public static void markTaskAsUndone(String[] inputs) {
        if (inputs.length > 1) {
            int taskId = Integer.parseInt(inputs[1]) - 1;
            if (taskId < 0 || taskId >= tasks.size()) {
                Formatter.printLine();
                Formatter.printMessage("Invalid task id.");
                Formatter.printLine();
            } else {
                if (!tasks.get(taskId).getIsDone()) {
                    Formatter.printLine();
                    Formatter.printMessage("Task is already unmarked.");
                    Formatter.printLine();
                    return;
                }
                tasks.get(taskId).markAsUndone();
                Formatter.printTaskStatusChange("OK, I've marked this task as not done yet:",
                        tasks.get(taskId), tasks.size());
            }
        } else {
            Formatter.printLine();
            Formatter.printMessage("Please enter task number to unmark!");
            Formatter.printLine();
        }
    }

    // Adding task of no specific type
    public static void addTask(String userInput) {
        Task newTask = new Task(userInput);
        tasks.add(newTask);
        Formatter.printLine();
        Formatter.printMessage("added: " + userInput);
        Formatter.printMessage("Now you have " + tasks.size() + " tasks in the list.");
        Formatter.printLine();
    }

    public static void addDeadline(String[] inputs) {
        // TODO: extract description
        // TODO: extract string after /by
        // TODO: Create Deadline item
        String description = "";
        String dueDate = "";
        int byIndex = -1;

        // Find the index of "/by"
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        // Extract task description (between "deadline" and "/by")
        String taskDescription = (byIndex > 1)
                ? String.join(" ", Arrays.copyOfRange(inputs, 1, byIndex))
                : "";

        // Extract deadline (words after "/by")
        String deadline = (byIndex != -1 && byIndex < inputs.length - 1)
                ? String.join(" ", Arrays.copyOfRange(inputs, byIndex+1, inputs.length))
                : "";

        Deadline deadlineTask = new Deadline(taskDescription, deadline);
        tasks.add(deadlineTask);
        Formatter.printTaskStatusChange("Got it. I've added this task:",deadlineTask,tasks.size());
    }

    public static void addToDo(String description) {
        // TODO: extract description
        // TODO: Create Todo item
        Todo todoTask = new Todo(description);
        tasks.add(todoTask);
        Formatter.printTaskStatusChange("Got it. I've added this task:", todoTask, tasks.size());
    }

    public static void addEvent(String[] inputs) {
        // TODO: extract description
        // TODO: extract string between /from and /to and string after /to
        // TODO: Create Event item
    }

    public static void displayWelcomeMessage() {
        Formatter.printLine();
        Formatter.printMessage("Hello! I'm Hugo\nWhat can I do for you?");
        Formatter.printLine();
    }

    public static void displayExitMessage() {
        Formatter.printLine();
        Formatter.printMessage("Bye. Hope to see you again soon!");
        Formatter.printLine();
    }
}
