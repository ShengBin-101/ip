public class Hugo {

    public static void main(String[] args) {
        displayWelcomeMessage();
        TaskManager taskManager = new TaskManager();
        taskManager.askInput();
    }

    public static void displayWelcomeMessage() {
        Formatter.printMessage("Hello! I'm Hugo\nWhat can I do for you?", true);
    }
}
