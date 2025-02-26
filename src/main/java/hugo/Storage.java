package hugo;

import hugo.tasks.Deadline;
import hugo.tasks.Event;
import hugo.tasks.Task;
import hugo.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import hugo.ui.Formatter;

public class Storage {

    private static final String FILE_PATH = "./data/hugo.txt";

    /**
     * Saves the given task list to the file.
     * Each task is saved as a line in the file.
     *
     * @param tasks The list of tasks to save.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            // Ensure the directory exists
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            // Create a FileWriter to write to the file
            FileWriter fw = new FileWriter(FILE_PATH);

            // Write each task to the file
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }

            fw.close(); // Close the FileWriter to complete the writing process
        } catch (IOException e) {
            Formatter.printBorderedMessage("Something went wrong while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            // Check if the file exists
            if (!file.exists()) {
                Formatter.printBorderedMessage("No saved tasks found. Starting with an empty list.\n" +
                        "Creating a new text file at " + FILE_PATH + ".");
                return tasks;
            }

            // Create a Scanner to read from the file
            Scanner scanner = new Scanner(file);

            // Read each line and parse it into a Task object
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            scanner.close(); // Close the Scanner
        } catch (IOException e) {
            Formatter.printBorderedMessage("Something went wrong while loading tasks: " + e.getMessage());
        }
        Formatter.printBorderedMessage("Loaded " + tasks.size() + " tasks from "+ FILE_PATH + ".");
        return tasks;
    }

    /**
     * Parses a Task object from a string representation.
     * The string is expected to be in the format saved by the `toFileString()` method of Task.
     *
     * @param line The string representation of the task.
     * @return A Task object parsed from the string, or null if parsing fails.
     */
    private Task parseTaskFromString(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Invalid format
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            if (parts.length < 4) {
                return null; // Invalid format for Deadline
            }
            return new Deadline(description, parts[3], isDone);
        case "E":
            if (parts.length < 5) {
                return null; // Invalid format for Event
            }
            return new Event(description, parts[3], parts[4], isDone);
        default:
            return null; // Unknown task type
        }
    }
}