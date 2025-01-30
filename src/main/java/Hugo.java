import java.util.ArrayList;
import java.util.Scanner;

public class Hugo {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final int MAJOR_INDENT_SIZE = 5;
    private static final int MINOR_INDENT_SIZE = 4;

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
            switch (userInputLine) {
            case "list":
                int count = 0;
                printLine();
                System.out.print("Here are the tasks in your list:".indent(MAJOR_INDENT_SIZE));
                for (Task task : tasks) {
                    String outputLine = (++count + ". [" + task.getStatusIcon() + "] " + task.getDescription());
                    System.out.print(outputLine.indent(MAJOR_INDENT_SIZE));
                }
                printLine();
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

    public static void addTask(String userInput) {
        Task newTask = new Task(userInput);
        tasks.add(newTask);
        String echoMessage = "added: "
                + userInput + "\n";
        printLine();
        System.out.print(echoMessage.indent(MAJOR_INDENT_SIZE));
        printLine();
    }

    public static void displayWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Hugo\n"
                + "What can I do for you?\n";
        printLine();
        System.out.print(welcomeMessage.indent(MAJOR_INDENT_SIZE));
        printLine();
    }

    public static void displayExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!\n";
        printLine();
        System.out.print(exitMessage.indent(MAJOR_INDENT_SIZE));
        printLine();
    }

    public static void printLine() {
        System.out.print("____________________________________________________________".indent(MINOR_INDENT_SIZE));
    }
}
