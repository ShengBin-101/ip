public class Formatter {

    private static final int MAJOR_INDENT = 7;
    private static final int NORMAL_INDENT = 5;
    private static final int MINOR_INDENT = 4;

    public static void printLine() {
        System.out.print("____________________________________________________________".indent(MINOR_INDENT));
    }

    public static void printMessage(String message) {
        System.out.print(message.indent(NORMAL_INDENT));
    }

    public static void printMajorMessage(String message) {
        System.out.print(message.indent(MAJOR_INDENT));
    }

    public static void printTaskList(java.util.List<Task> tasks) {
        printLine();
        printMessage("Here are the tasks in your list:");
        int count = 0;
        for (Task task : tasks) {
            String outputLine = (++count + ". [" + task.getStatusIcon() + "] " + task.getDescription());
            printMessage(outputLine);
        }
        printLine();
    }

    public static void printTaskStatusChange(String message, Task task, int totalTasks) {
        printLine();
        printMessage(message);
        printMajorMessage("[" + task.getStatusIcon() + "] " + task.getDescription());
        printMessage("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }
}
