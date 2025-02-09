import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void askInput() {
        boolean isAskingInput = true;
        String userInputLine;
        Scanner scanner = new Scanner(System.in);
        while (isAskingInput) {

            if (!scanner.hasNextLine()) {  // Prevents NoSuchElementException
                break;
            }

            userInputLine = scanner.nextLine();
            if (userInputLine.isEmpty()) {
                Formatter.printBorderedMessage("Please enter a command or task name to add");
            } else {
                String[] inputs = userInputLine.split(" ", 2);
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
                    Formatter.printBorderedMessage("Unknown command.");
                    break;
                }
            }
        }
    }

    private void addDeadline(String[] inputs) {
        if (inputs.length < 2) {
            Formatter.printBorderedMessage("Please provide a deadline " +
                    "description and due date.");
            return;
        }
        String[] deadlineArgs = InputParser.parseDeadlineArgs(inputs[1]);
        if (deadlineArgs == null) {
            Formatter.printBorderedMessage("Invalid deadline format. " +
                    "Use: deadline <description> /by <due date>");
            return;
        }
        Deadline deadlineTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        tasks.add(deadlineTask);
        Formatter.printTaskStatusChange("Got it. I've added this task:", deadlineTask, tasks.size());
    }

    private void addToDo(String[] inputs) {
        if (inputs.length < 2) {
            Formatter.printBorderedMessage("Please provide a todo description.");
            return;
        }
        Todo todoTask = new Todo(inputs[1].trim());
        tasks.add(todoTask);
        Formatter.printTaskStatusChange("Got it. I've added this task:", todoTask, tasks.size());
    }

    private void addEvent(String[] inputs) {
        if (inputs.length < 2) {
            Formatter.printBorderedMessage("Please provide an event description, " +
                    "start time, and end time.");
            return;
        }
        String[] eventArgs = InputParser.parseEventArgs(inputs[1]);
        if (eventArgs == null) {
            Formatter.printBorderedMessage("Invalid event format. Use: event <description> " +
                    "/from <start time> /to <end time>");
            return;
        }
        Event eventTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
        tasks.add(eventTask);
        Formatter.printTaskStatusChange("Got it. I've added this task:", eventTask, tasks.size());
    }

    private void listTasks() {
        Formatter.printTaskList(tasks);
    }

    private void markTaskAsDone(String[] inputs) {
        if (inputs.length < 2) {
            Formatter.printBorderedMessage("Please provide a task number to mark as done.");
            return;
        }
        int taskId = Integer.parseInt(inputs[1]) - 1;
        if (taskId < 0 || taskId >= tasks.size()) {
            Formatter.printBorderedMessage("Invalid task id.");
            return;
        }
        if (tasks.get(taskId).getIsDone()) {
            Formatter.printBorderedMessage("Task is already marked.");
            return;
        }
        tasks.get(taskId).setIsDone(true);
        Formatter.printTaskStatusChange("Nice! I've marked this task as done:",
                tasks.get(taskId), tasks.size());
    }

    private void markTaskAsUndone(String[] inputs) {
        if (inputs.length < 2) {
            Formatter.printBorderedMessage("Please provide a task number to unmark.");
            return;
        }
        int taskId = Integer.parseInt(inputs[1]) - 1;
        if (taskId < 0 || taskId >= tasks.size()) {
            Formatter.printBorderedMessage("Invalid task id.");
            return;
        }
        if (!tasks.get(taskId).getIsDone()) {
            Formatter.printBorderedMessage("Task is already unmarked.");
            return;
        }
        tasks.get(taskId).setIsDone(false);
        Formatter.printTaskStatusChange("OK, I've marked this task as not done yet:",
                tasks.get(taskId), tasks.size());
    }

    private void displayExitMessage() {
        Formatter.printBorderedMessage("Bye. Hope to see you again soon!");
    }
}