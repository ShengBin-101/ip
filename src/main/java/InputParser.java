public class InputParser {

    public static String[] parseEventArgs(String input) {
        String[] eventArgParts = input.split("/", 3);
        if (eventArgParts.length < 3) {
            return null;
        }
        String eventDesc = eventArgParts[0].trim();
        String start = eventArgParts[1].split("from", 2)[1].trim();
        String end = eventArgParts[2].split("to", 2)[1].trim();
        return new String[]{eventDesc, start, end};
    }

    public static String[] parseDeadlineArgs(String input) {
        String[] deadlineArgParts = input.split("/by", 2);
        if (deadlineArgParts.length < 2) {
            return null;
        }
        String taskDescription = deadlineArgParts[0].trim();
        String dueDate = deadlineArgParts[1].trim();
        return new String[]{taskDescription, dueDate};
    }
}
