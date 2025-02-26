package hugo;

import hugo.exceptions.TaskNotFoundException;
import hugo.exceptions.InvalidTaskStateException;

import hugo.tasks.Task;
import hugo.ui.Formatter;

import java.util.ArrayList;

/**
 * Encapsulates the list of tasks and provides operations to modify them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = new ArrayList<Task>(savedTasks);  // Defensive copy
    }

    public void addTask(Task task) {
        tasks.add(task);
        Formatter.printTaskStatusChange("Got it. I've added this task:", task, tasks.size());
    }

    public void deleteTask(int taskIndex) throws TaskNotFoundException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new TaskNotFoundException("Invalid task id.");
        }
        Task removedTask = tasks.remove(taskIndex);
        Formatter.printTaskStatusChange("Noted. I've removed this task:", removedTask, tasks.size());
    }

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

    public void listTasks() {
        Formatter.printTaskList(tasks);
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        Formatter.printMatchingTaskList(matchingTasks);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<Task>(tasks);  // Returns a defensive copy
    }

    public int size() {
        return tasks.size();
    }

    private boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
