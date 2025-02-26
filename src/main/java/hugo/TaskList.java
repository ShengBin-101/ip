package hugo;

import hugo.exceptions.TaskNotFoundException;
import hugo.exceptions.InvalidTaskStateException;

import hugo.tasks.Task;
import hugo.ui.Formatter;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given saved tasks.
     *
     * @param savedTasks The list of saved tasks.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = new ArrayList<Task>(savedTasks);  // Defensive copy
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        Formatter.printTaskStatusChange("Got it. I've added this task:", task, tasks.size());
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex The index of the task to delete.
     * @throws TaskNotFoundException if the task index is invalid.
     */
    public void deleteTask(int taskIndex) throws TaskNotFoundException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new TaskNotFoundException("Invalid task id.");
        }
        Task removedTask = tasks.remove(taskIndex);
        Formatter.printTaskStatusChange("Noted. I've removed this task:", removedTask, tasks.size());
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task to mark as done.
     * @throws TaskNotFoundException     if the task index is invalid.
     * @throws InvalidTaskStateException if the task is already marked as done.
     */
    public void markTaskAsDone(int taskIndex) throws TaskNotFoundException, InvalidTaskStateException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new TaskNotFoundException("Invalid task id.");
        }
        Task task = tasks.get(taskIndex);
        if (task.getIsDone()) {
            throw new InvalidTaskStateException("Task is already marked.");
        }
        task.setIsDone(true);
        Formatter.printTaskStatusChange("Nice! I've marked this task as done:", task, tasks.size());
    }

    /**
     * Marks a task as undone.
     *
     * @param taskIndex The index of the task to mark as undone.
     * @throws TaskNotFoundException     if the task index is invalid.
     * @throws InvalidTaskStateException if the task is already unmarked.
     */
    public void markTaskAsUndone(int taskIndex) throws TaskNotFoundException, InvalidTaskStateException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new TaskNotFoundException("Invalid task id.");
        }
        Task task = tasks.get(taskIndex);
        if (!task.getIsDone()) {
            throw new InvalidTaskStateException("Task is already unmarked.");
        }
        task.setIsDone(false);
        Formatter.printTaskStatusChange("OK, I've marked this task as not done yet:", task, tasks.size());
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        Formatter.printTaskList(tasks);
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        Formatter.printMatchingTaskList(matchingTasks);
    }

    /**
     * Returns a defensive copy of the task list.
     *
     * @return A defensive copy of the task list.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<Task>(tasks);  // Returns a defensive copy
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the given task index is valid.
     *
     * @param index The task index to check.
     * @return True if the task index is valid, false otherwise.
     */
    private boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
