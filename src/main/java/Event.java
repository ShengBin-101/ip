public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.setFrom(from);
        this.setTo(to);
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
}
