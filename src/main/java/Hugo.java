import java.util.ArrayList;
import java.util.Scanner;

public class Hugo {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final int MAJOR_INDENT = 7;
    private static final int NORMAL_INDENT = 5;
    private static final int MINOR_INDENT = 4;

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
                printLine();
                System.out.print("Please enter a command or task name to add".indent(NORMAL_INDENT));
                printLine();
            } else {
                switch (inputs[0]) {
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
                    addTask(userInputLine);
                    break;
                }
            }
        }
    }

    public static void listTasks() {
        int count = 0;
        printLine();
        System.out.print("Here are the tasks in your list:".indent(NORMAL_INDENT));
        for (Task task : tasks) {
            String outputLine = (++count + ". [" + task.getStatusIcon() + "] " + task.getDescription());
            System.out.print(outputLine.indent(NORMAL_INDENT));
        }
        printLine();
    }

    public static void markTaskAsDone(String[] inputs) {
        if (inputs.length > 1) {
            int taskId = Integer.parseInt(inputs[1]) - 1;
            if (taskId < 0 || taskId >= tasks.size()) {
                printLine();
                System.out.print("Invalid task id.".indent(NORMAL_INDENT));
                printLine();
            } else {
                tasks.get(taskId).markAsDone();
                String markMessage = "Nice! I've marked this task as done:";
                String outputLine = "[" + tasks.get(taskId).getStatusIcon()
                        + "] " + tasks.get(taskId).getDescription();
                printLine();
                System.out.print(markMessage.indent(NORMAL_INDENT));
                System.out.print(outputLine.indent(MAJOR_INDENT));
                printLine();
            }
        } else {
            printLine();
            System.out.print("Please enter task number to mark!".indent(NORMAL_INDENT));
            printLine();
        }
    }

    public static void markTaskAsUndone(String[] inputs) {
        if (inputs.length > 1) {
            int taskId = Integer.parseInt(inputs[1]) - 1;
            if (taskId < 0 || taskId >= tasks.size()) {
                printLine();
                System.out.print("Invalid task id.".indent(NORMAL_INDENT));
                printLine();
            } else {
                tasks.get(taskId).markAsUndone();
                String unmarkMessage = "OK, I've marked this task as not done yet:";
                String outputLine = "[" + tasks.get(taskId).getStatusIcon()
                        + "] " + tasks.get(taskId).getDescription();
                printLine();
                System.out.print(unmarkMessage.indent(NORMAL_INDENT));
                System.out.print(outputLine.indent(MAJOR_INDENT));
                printLine();
            }
        } else {
            printLine();
            System.out.print("Please enter task number to unmark!".indent(NORMAL_INDENT));
            printLine();
        }
    }

    public static void addTask(String userInput) {
        Task newTask = new Task(userInput);
        tasks.add(newTask);
        String echoMessage = "added: " + userInput + "\n";
        printLine();
        System.out.print(echoMessage.indent(NORMAL_INDENT));
        printLine();
    }

    public static void displayWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Hugo\n" + "What can I do for you?\n";
        printLine();
        System.out.print(welcomeMessage.indent(NORMAL_INDENT));
        printLine();
    }

    public static void displayExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!\n";
        printLine();
        System.out.print(exitMessage.indent(NORMAL_INDENT));
        printLine();
    }

    public static void printLine() {
        System.out.print("____________________________________________________________".indent(MINOR_INDENT));
    }
}
