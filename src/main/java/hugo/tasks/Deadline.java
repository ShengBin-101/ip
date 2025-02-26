package hugo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.setDueDateTime(dueDateTime);
    }

    // Called when loading data from data file
    public Deadline(String description, LocalDateTime dueDateTime, boolean isDone) {
        super(description);
        this.setDueDateTime(dueDateTime);
        this.setIsDone(isDone);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getDueDateTime().format(formatter) + ")";
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    @Override
    public String toFileString() {
        // Format: D | isDone | description | by
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.description + " | " + this.getDueDateTime().format(formatter);
    }
}
