package hugo.tasks;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public abstract String toString();

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The completion status to set.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, otherwise false.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the task for file storage.
     *
     * @return The string representation of the task for file storage.
     */
    public abstract String toFileString();

}
