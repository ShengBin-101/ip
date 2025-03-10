package hugo;

import hugo.exceptions.TaskInputException;
import hugo.exceptions.EmptyDescriptionException;
import hugo.exceptions.TaskNotFoundException;
import hugo.exceptions.InvalidTaskStateException;
import hugo.exceptions.CommandInputException;

import hugo.tasks.Deadline;
import hugo.tasks.Event;
import hugo.tasks.Todo;
import hugo.ui.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskManager {
    private TaskList taskList;
    private Storage storage;

    public TaskManager() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
        if (taskList.size() > 0) {
            taskList.listTasks();
        }
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
                    case "delete":
                        deleteTask(inputs);
                        break;
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
                        taskList.listTasks();
                        break;
                    case "mark":
                        markTaskAsDone(inputs);
                        break;
                    case "unmark":
                        markTaskAsUndone(inputs);
                        break;
                    case "find":
                        findTasks(inputs);
                        break;
                    case "bye":
                        displayExitMessage();
                        isAskingInput = false;
                        break;
                    default:
                        throw new CommandInputException("Unknown command.");
                    }
                    storage.saveTasks(taskList.getTasks()); // Save tasks after change in tasks
                } catch (CommandInputException e) {
                    Formatter.printBorderedMessage(e.getMessage());
                }
            }
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param inputs The user input split into command and arguments.
     */
    private void deleteTask(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new TaskInputException("Please provide a task number to delete.");
            }
            int taskId = Integer.parseInt(inputs[1].trim()) - 1;
            taskList.deleteTask(taskId);
            storage.saveTasks(taskList.getTasks());
        } catch (NumberFormatException e) {
            Formatter.printBorderedMessage("Invalid task number format. Please provide a valid number.");
        } catch (TaskInputException | TaskNotFoundException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param inputs The user input split into command and arguments.
     */
    private void addDeadline(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new EmptyDescriptionException("Please provide a deadline description and due date." +
                        "\nDate/Time Format: yyyy-MM-dd HH:mm");
            }
            String[] deadlineArgs = InputParser.parseDeadlineArgs(inputs[1]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dueDateTime = LocalDateTime.parse(deadlineArgs[1], formatter);
            Deadline deadlineTask = new Deadline(deadlineArgs[0], dueDateTime);
            taskList.addTask(deadlineTask);
            storage.saveTasks(taskList.getTasks());
        } catch (TaskInputException | EmptyDescriptionException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param inputs The user input split into command and arguments.
     */
    private void addToDo(String[] inputs) {
        try {
            if (inputs.length < 2 || inputs[1].trim().isEmpty()) {
                throw new EmptyDescriptionException("Please provide a todo description.");
            }
            Todo todoTask = new Todo(inputs[1].trim());
            taskList.addTask(todoTask);
            storage.saveTasks(taskList.getTasks());
        } catch (EmptyDescriptionException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    /**
     * Adds an event task to the task list.
     *
     * @param inputs The user input split into command and arguments.
     */
    private void addEvent(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new EmptyDescriptionException("Please provide an event description, start time, and end time. " +
                        "Use: event <description> /from <start time> /to <end time>" +
                        "\nDate/Time Format: yyyy-MM-dd HH:mm");
            }
            String[] eventArgs = InputParser.parseEventArgs(inputs[1]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(eventArgs[1], formatter);
            LocalDateTime end = LocalDateTime.parse(eventArgs[2], formatter);
            Event eventTask = new Event(eventArgs[0], start, end);
            taskList.addTask(eventTask);
            storage.saveTasks(taskList.getTasks());
        } catch (TaskInputException | EmptyDescriptionException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param inputs The user input split into command and arguments.
     */
    private void markTaskAsDone(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new TaskInputException("Please provide a task number to mark as done.");
            }
            int taskId = Integer.parseInt(inputs[1].trim()) - 1;
            taskList.markTaskAsDone(taskId);
            storage.saveTasks(taskList.getTasks());
        } catch (NumberFormatException e) {
            Formatter.printBorderedMessage("Invalid task number format. Please provide a valid number.");
        } catch (TaskInputException | TaskNotFoundException | InvalidTaskStateException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param inputs The user input split into command and arguments.
     */
    private void markTaskAsUndone(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new TaskInputException("Please provide a task number to unmark.");
            }
            int taskId = Integer.parseInt(inputs[1].trim()) - 1;
            taskList.markTaskAsUndone(taskId);
            storage.saveTasks(taskList.getTasks());
        } catch (NumberFormatException e) {
            Formatter.printBorderedMessage("Invalid task number format. Please provide a valid number.");
        } catch (TaskInputException | TaskNotFoundException | InvalidTaskStateException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @param inputs The user input split into command and arguments.
     */
    private void findTasks(String[] inputs) {
        try {
            if (inputs.length < 2 || inputs[1].trim().isEmpty()) {
                throw new TaskInputException("Please provide a keyword to search for.");
            }
            String keyword = inputs[1].trim();
            taskList.findTasks(keyword);
        } catch (TaskInputException e) {
            Formatter.printBorderedMessage(e.getMessage());
        }
    }

    /**
     * Displays the exit message.
     */
    private void displayExitMessage() {
        Formatter.printBorderedMessage("Bye. Hope to see you again soon!");
    }
}
