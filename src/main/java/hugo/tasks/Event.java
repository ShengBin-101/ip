package hugo.tasks;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.setFrom(from);
        this.setTo(to);
    }

    // Called when loading data from data file
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.setFrom(from);
        this.setTo(to);
        this.setIsDone(isDone);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.getDescription() +
                " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toFileString() {
        // Format: E | isDone | description | from | to
        return "E | " + (this.getIsDone() ? "1" : "0") + " | " + this.description + " | " + this.from + " | " + this.to;
    }

}
