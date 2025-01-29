import java.util.Scanner;

public class Hugo {

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
            case "bye":
                displayExitMessage();
                isAskingInput = false;
                break;
            default:
                echoUserInput(userInputLine);
                break;
            }
        }
    }

    public static void echoUserInput(String userInput) {
        String echoMessage = printLine()
                + userInput + "\n"
                + printLine();
        System.out.println(echoMessage.indent(4));
    }

    public static void displayWelcomeMessage() {
        String welcomeMessage = printLine()
                + "Hello! I'm Hugo\n"
                + "What can I do for you?\n"
                + printLine();
        System.out.println(welcomeMessage.indent(4));
    }

    public static void displayExitMessage() {
        String exitMessage = printLine()
                + "Bye. Hope to see you again soon!\n"
                + printLine();
        System.out.println(exitMessage.indent(4));
    }

    public static String printLine() {
        return "____________________________________________________________\n";
    }
}
