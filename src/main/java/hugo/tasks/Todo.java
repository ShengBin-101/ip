package hugo.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }
}
