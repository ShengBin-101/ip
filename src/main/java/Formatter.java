import java.util.ArrayList;

public class Formatter {

    private static final int MAJOR_INDENT = 7;
    private static final int NORMAL_INDENT = 5;
    private static final int MINOR_INDENT = 4;

    public static void printLine() {
        System.out.print("____________________________________________________________".indent(MINOR_INDENT));
    }

    public static void printMessage(String message, boolean hasLines) {
        if (hasLines) {
            printLine();
            System.out.print(message.indent(NORMAL_INDENT));
            printLine();
        }
        else {
            System.out.print(message.indent(NORMAL_INDENT));
        }
    }

    public static void printMajorMessage(String message) {
        System.out.print(message.indent(MAJOR_INDENT));
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        printLine();
        printMessage("Here are the tasks in your list:", false);
        int count = 0;
        for (Task task : tasks) {
            printMessage(++count + "." + task.toString(), false);
        }
        printLine();
    }

    public static void printTaskStatusChange(String message, Task task, int totalTasks) {
        printLine();
        printMessage(message, false);
        printMajorMessage(task.toString());
        printMessage("Now you have " + totalTasks + " tasks in the list.", false);
        printLine();
    }
}
