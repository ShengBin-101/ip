package hugo.ui;

import hugo.tasks.Task;

import java.util.ArrayList;

public class Formatter {

//  Example Output:
//     ____________________________________________________________ [Lines have MINOR_INDENT]
//      This is a normal indent message.    [With NORMAL_INDENT]
//        This is a major indent message.   [With MAJOR_INDENT]
//     ____________________________________________________________

    private static final int MAJOR_INDENT = 7;
    private static final int NORMAL_INDENT = 5;
    private static final int MINOR_INDENT = 4;

    public static void printLine() {
        System.out.print("____________________________________________________________________".indent(MINOR_INDENT));
    }

    public static void printBorderedMessage(String message) {
        printLine();
        System.out.print(message.indent(NORMAL_INDENT));
        printLine();
    }

    public static void printSimpleMessage(String message) {
        System.out.print(message.indent(NORMAL_INDENT));
    }


    public static void printMajorMessage(String message) {
        System.out.print(message.indent(MAJOR_INDENT));
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        printLine();
        printSimpleMessage("Here are the tasks in your list:");
        int count = 0;
        for (Task task : tasks) {
            count += 1;
            printSimpleMessage(count + "." + task.toString());
        }
        printLine();
    }

    public static void printTaskStatusChange(String message, Task task, int totalTasks) {
        printLine();
        printSimpleMessage(message);
        printMajorMessage(task.toString());
        printSimpleMessage("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }
}
