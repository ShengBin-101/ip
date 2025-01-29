import java.util.ArrayList;
import java.util.Scanner;

public class Hugo {

    private static ArrayList<String> inputs = new ArrayList<>();

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
                for (String input : inputs) {
                    System.out.print((++count + ". " + input).indent(4));
                }
                printLine();
                break;
            case "bye":
                displayExitMessage();
                isAskingInput = false;
                break;
            default:
                addUserInput(userInputLine);
                break;
            }
        }
    }

    public static void addUserInput(String userInput) {
        inputs.add(userInput);
        String echoMessage = "added: "
                + userInput + "\n";
        printLine();
        System.out.print(echoMessage.indent(4));
        printLine();
    }

    public static void displayWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Hugo\n"
                + "What can I do for you?\n";
        printLine();
        System.out.print(welcomeMessage.indent(4));
        printLine();
    }

    public static void displayExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!\n";
        printLine();
        System.out.print(exitMessage.indent(4));
        printLine();
    }

    public static void printLine() {
        System.out.print("____________________________________________________________".indent(4));
    }
}
