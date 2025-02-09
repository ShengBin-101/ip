public class InputParser {

    /**
     * Parses input for an event task.
     * Expected format: "event_description /from start_time /to end_time"
     * @param input The raw user input string.
     * @return A String array containing [eventDescription, startTime, endTime], or null if input is invalid.
     */
    public static String[] parseEventArgs(String input) {
        // Ensure input contains both required keywords "/from" and "/to"
        if (!input.contains("/from") || !input.contains("/to")) {
            return null; // Invalid format
        }

        // Split input at "/from" while keeping event description
        String[] firstSplit = input.split("/from", 2);
        if (firstSplit.length < 2) {
            return null; // Ensure "from" keyword is correctly positioned
        }

        String eventDesc = firstSplit[0].trim();

        // Split the remaining part at "/to" to extract start and end time
        String[] secondSplit = firstSplit[1].split("/to", 2);
        if (secondSplit.length < 2) {
            return null; // Ensure "to" keyword is correctly positioned
        }

        String start = secondSplit[0].trim();
        String end = secondSplit[1].trim();

        // Ensure extracted values are non-empty
        if (eventDesc.isEmpty() || start.isEmpty() || end.isEmpty()) {
            return null; // Reject malformed inputs
        }

        return new String[]{eventDesc, start, end};
    }

    /**
     * Parses input for a deadline task.
     * Expected format: "task_description /by due_date"
     * @param input The raw user input string.
     * @return A String array containing [taskDescription, dueDate], or null if input is invalid.
     */
    public static String[] parseDeadlineArgs(String input) {
        // Ensure input contains the required keyword "/by"
        if (!input.contains("/by")) {
            return null; // Invalid format
        }

        // Split input at "/by" while keeping task description
        String[] deadlineArgParts = input.split("/by", 2);
        if (deadlineArgParts.length < 2) {
            return null; // Ensure "/by" keyword is correctly positioned
        }

        String taskDescription = deadlineArgParts[0].trim();
        String dueDate = deadlineArgParts[1].trim();

        // Ensure extracted values are non-empty
        if (taskDescription.isEmpty() || dueDate.isEmpty()) {
            return null; // Reject malformed inputs
        }

        return new String[]{taskDescription, dueDate};
    }
}
