package hugo.tasks;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.setDueDate(dueDate);
    }

    // Called when loading data from data file
    public Deadline(String description, String dueDate, boolean isDone) {
        super(description);
        this.setDueDate(dueDate);
        this.setIsDone(isDone);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription();
    }

    @Override
    public String getDescription() {
        return (super.getDescription() + " (by: " + getDueDate() + ")");
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toFileString() {
        // Format: D | isDone | description | by
        return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.description + " | " + this.getDueDate();
    }
}
