package hugo.tasks;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.setDueDate(dueDate);
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
}
