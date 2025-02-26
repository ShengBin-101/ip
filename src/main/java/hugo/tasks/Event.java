package hugo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.setFrom(from);
        this.setTo(to);
    }

    // Called when loading data from data file
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description);
        this.setFrom(from);
        this.setTo(to);
        this.setIsDone(isDone);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + "[" + getStatusIcon() + "] " + this.getDescription() +
                " (from: " + this.getFrom().format(formatter) +
                " to: " + this.getTo().format(formatter) + ")";
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toFileString() {
        // Format: E | isDone | description | from | to
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + (this.getIsDone() ? "1" : "0") + " | " + this.description + " | " + this.from.format(formatter) + " | " + this.to.format(formatter);
    }
}
