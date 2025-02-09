package hugo;

import hugo.exceptions.*;
import hugo.tasks.Deadline;
import hugo.tasks.Event;
import hugo.tasks.Task;
import hugo.tasks.Todo;
import hugo.ui.Formatter;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void askInput() {
        boolean isAskingInput = true;
        String userInputLine;
        Scanner scanner = new Scanner(System.in);
        while (isAskingInput) {

            if (!scanner.hasNextLine()) {  // Prevents NoSuchElementException
                break;
            }

            userInputLine = scanner.nextLine();
            if (userInputLine.isEmpty()) {
                Formatter.printBorderedMessage("Please enter a command or task name to add");
            } else {
                String[] inputs = userInputLine.split(" ", 2);
                try {
                    switch (inputs[0]) {
                    case "deadline":
                        addDeadline(inputs);
                        break;
                    case "todo":
                        addToDo(inputs);
                        break;
                    case "event":
                        addEvent(inputs);
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                        markTaskAsDone(inputs);
                        break;
                    case "unmark":
                        markTaskAsUndone(inputs);
                        break;
                    case "bye":
                        displayExitMessage();
                        isAskingInput = false;
                        break;
                    default:
                        throw new CommandInputException("Unknown command.");
                    }
                } catch (CommandInputException e) {
                    Formatter.printBorderedMessage(e.getMessage());
                }
            }
        }
    }

    private void addDeadline(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new EmptyDescriptionException("Please provide a deadline description and due date.");
            }
            String[] deadlineArgs = InputParser.parseDeadlineArgs(inputs[1]);
            if (deadlineArgs == null) {
                throw new TaskInputException("Invalid deadline format. Use: deadline <description> /by <due date>");
            }
            Deadline deadlineTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            tasks.add(deadlineTask);
            Formatter.printTaskStatusChange("Got it. I've added this task:", deadlineTask, tasks.size());
        } catch (TaskInputException | EmptyDescriptionException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    private void addToDo(String[] inputs) {
        try {
            // Check if the description is empty or contains only whitespace
            if (inputs.length < 2 || inputs[1].trim().isEmpty()) {
                throw new EmptyDescriptionException("Please provide a todo description.");
            }
            Todo todoTask = new Todo(inputs[1].trim());
            tasks.add(todoTask);
            Formatter.printTaskStatusChange("Got it. I've added this task:", todoTask, tasks.size());
        } catch (EmptyDescriptionException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    private void addEvent(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new EmptyDescriptionException("Please provide an event description, start time, and end time. " +
                        "Use: event <description> /from <start time> /to <end time>");
            }
            // Extract [eventDescription, startTime, endTime] from inputs following "event" command
            String[] eventArgs = InputParser.parseEventArgs(inputs[1]);
            if (eventArgs == null) {
                throw new TaskInputException("Invalid event format. " +
                        "Use: event <description> /from <start time> /to <end time>");
            }
            Event eventTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
            tasks.add(eventTask);
            Formatter.printTaskStatusChange("Got it. I've added this task:", eventTask, tasks.size());
        } catch (TaskInputException | EmptyDescriptionException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    private void listTasks() {
        Formatter.printTaskList(tasks);
    }

    private void markTaskAsDone(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new TaskInputException("Please provide a task number to mark as done.");
            }
            int taskId = Integer.parseInt(inputs[1]) - 1;
            if (taskId < 0 || taskId >= tasks.size()) {
                throw new TaskNotFoundException("Invalid task id.");
            }
            if (tasks.get(taskId).getIsDone()) {
                throw new InvalidTaskStateException("Task is already marked.");
            }
            tasks.get(taskId).setIsDone(true);
            Formatter.printTaskStatusChange("Nice! I've marked this task as done:",
                    tasks.get(taskId), tasks.size());
        } catch (TaskInputException | TaskNotFoundException | InvalidTaskStateException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    private void markTaskAsUndone(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new TaskInputException("Please provide a task number to unmark.");
            }
            int taskId = Integer.parseInt(inputs[1]) - 1;
            if (taskId < 0 || taskId >= tasks.size()) {
                throw new TaskNotFoundException("Invalid task id.");
            }
            if (!tasks.get(taskId).getIsDone()) {
                throw new InvalidTaskStateException("Task is already unmarked.");
            }
            tasks.get(taskId).setIsDone(false);
            Formatter.printTaskStatusChange("OK, I've marked this task as not done yet:",
                    tasks.get(taskId), tasks.size());
        } catch (TaskInputException | TaskNotFoundException | InvalidTaskStateException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    private void displayExitMessage() {
        Formatter.printBorderedMessage("Bye. Hope to see you again soon!");
    }
}
