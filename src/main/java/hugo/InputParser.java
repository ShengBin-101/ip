package hugo;

import hugo.exceptions.TaskInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputParser {

    /**
     * Parses input for an event task.
     * Expected format: "event_description /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm"
     *
     * @param input The raw user input string.
     * @return A String array containing [eventDescription, startTime, endTime].
     * @throws TaskInputException if the input format is invalid.
     */
    public static String[] parseEventArgs(String input) throws TaskInputException {
        // Ensure input contains both required keywords "/from" and "/to"
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new TaskInputException("Invalid event format. " +
                    "Use: event <description> /from <start time> /to <end time>" +
                    "\nDate Format: yyyy-MM-dd HH:mm");
        }

        // Split input at "/from" while keeping event description
        String[] firstSplit = input.split("/from", 2);
        if (firstSplit.length < 2) {
            throw new TaskInputException("Invalid event format. " +
                    "Use: event <description> /from <start time> /to <end time>" +
                    "\nDate/Time Format: yyyy-MM-dd HH:mm");
        }

        String eventDesc = firstSplit[0].trim();

        // Split the remaining part at "/to" to extract start and end time
        String[] secondSplit = firstSplit[1].split("/to", 2);
        if (secondSplit.length < 2) {
            throw new TaskInputException("Invalid event format. " +
                    "Use: event <description> /from <start time> /to <end time>" +
                    "\nDate/Time Format: yyyy-MM-dd HH:mm");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(secondSplit[0].trim(), formatter);
            LocalDateTime end = LocalDateTime.parse(secondSplit[1].trim(), formatter);
            return new String[]{eventDesc, start.format(formatter), end.format(formatter)};
        } catch (DateTimeParseException e) {
            throw new TaskInputException("Invalid date format. Use: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Parses input for a deadline task.
     * Expected format: "task_description /by yyyy-MM-dd HH:mm"
     *
     * @param input The raw user input string.
     * @return A String array containing [taskDescription, dueDateTime].
     * @throws TaskInputException if the input format is invalid.
     */
    public static String[] parseDeadlineArgs(String input) throws TaskInputException {
        // Ensure input contains the required keyword "/by"
        if (!input.contains("/by")) {
            throw new TaskInputException("Invalid deadline format. " +
                    "Use: deadline <description> /by <due date>" +
                    "\nDate/Time Format: yyyy-MM-dd HH:mm");
        }

        // Split input at "/by" while keeping task description
        String[] deadlineArgParts = input.split("/by", 2);
        if (deadlineArgParts.length < 2) {
            throw new TaskInputException("Invalid deadline format. " +
                    "Use: deadline <description> /by <due date>" +
                    "\nDate/Time Format: yyyy-MM-dd HH:mm");
        }

        String taskDescription = deadlineArgParts[0].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dueDateTime = LocalDateTime.parse(deadlineArgParts[1].trim(), formatter);
            return new String[]{taskDescription, dueDateTime.format(formatter)};
        } catch (DateTimeParseException e) {
            throw new TaskInputException("Invalid date format. Use: yyyy-MM-dd HH:mm");
        }
    }
}
