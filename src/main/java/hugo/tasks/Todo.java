package hugo.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    // Called when loading data from data file
    public Todo(String description, boolean isDone){
        super(description);
        this.setIsDone(isDone);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toFileString() {
        // Format: T | isDone | description
        return "T | " + (this.getIsDone() ? "1" : "0") + " | " + this.description;
    }
}
