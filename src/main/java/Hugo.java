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
            if (userInputLine.isEmpty()) {
                // Handle empty input
                Formatter.printLine();
                Formatter.printMessage("Please enter a command or task name to add");
                Formatter.printLine();
            } else {
                String[] inputs = userInputLine.split(" ");
                switch (inputs[0]) {
                case "deadline":
                    addDeadline(inputs);
                    break;
                case "todo":
                    addToDo(inputs);
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
                    addToDo(inputs);
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

    public static void addDeadline(String[] inputs) {
        String taskDescription;
        String dueDate;
        int byIndex = -1;

        // Find the index of "/by"
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        // Extract task description (between "deadline" and "/by")
        taskDescription = (byIndex > 1)
                ? String.join(" ", Arrays.copyOfRange(inputs, 1, byIndex))
                : "";

        // Extract deadline (words after "/by")
        dueDate = (byIndex != -1 && byIndex < inputs.length - 1)
                ? String.join(" ", Arrays.copyOfRange(inputs, byIndex + 1, inputs.length))
                : "";

        Deadline deadlineTask = new Deadline(taskDescription, dueDate);
        tasks.add(deadlineTask);
        Formatter.printTaskStatusChange("Got it. I've added this task:", deadlineTask, tasks.size());
    }

    public static void addToDo(String[] inputs) {
        // Extract task description
        String taskDescription = "";
        if (inputs[0].equals("todo")) {
            if (inputs.length > 1) {
                taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
            }
        } else {
            taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 0, inputs.length));
        }

        Todo todoTask = new Todo(taskDescription);
        tasks.add(todoTask);
        Formatter.printTaskStatusChange("Got it. I've added this task:", todoTask, tasks.size());
    }

    public static void addEvent(String[] inputs) {
        String taskDescription;
        String from;
        String to;
        int fromIndex = -1;
        int toIndex = -1;

        if (inputs[0].equals("event")) {
            if (inputs.length > 1) {
                // Find the indexes of "/from" and "/to"
                for (int i = 0; i < inputs.length; i++) {
                    if (inputs[i].equals("/from")) {
                        fromIndex = i;
                    } else if (inputs[i].equals("/to")) {
                        toIndex = i;
                    }
                }
                taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, fromIndex));
                from = String.join(" ", Arrays.copyOfRange(inputs, fromIndex + 1, toIndex));
                to = String.join(" ", Arrays.copyOfRange(inputs, toIndex + 1, inputs.length));

                Event event = new Event(taskDescription, from, to);
                tasks.add(event);
                Formatter.printTaskStatusChange("Got it. I've added this task:", event, tasks.size());

            } else {
                Formatter.printLine();
                Formatter.printMessage("Please enter a event description.");
                Formatter.printLine();
            }
        }
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
