# Hugo User Guide

Hugo is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Hugo can help you manage your tasks efficiently.

## Quick start

1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest `hugo.jar` file from [here](#).
3. Copy the file to the folder you want to use as the home folder for Hugo.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hugo.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. e.g., typing `help` and pressing Enter will open the help window.

Some example commands you can try:
- `list` : Lists all tasks.
- `todo borrow book` : Adds a todo task named "borrow book" to the task list.
- `delete 3` : Deletes the 3rd task shown in the current list.
- `bye` : Exits the app.

Refer to the Features below for details of each command.

## Features

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

### Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo borrow book`

### Adding a deadline task: `deadline`
Adds a deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by yyyy-MM-dd HH:mm`

Examples:
- `deadline return book /by 2023-10-10 18:00`

### Adding an event task: `event`
Adds an event task to the task list.

Format: `event TASK_DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm`

Examples:
- `event project meeting /from 2023-10-10 14:00 /to 2023-10-10 16:00`

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`

Examples:
- `delete 2`

### Marking a task as done: `mark`
Marks the specified task as done.

Format: `mark INDEX`

Examples:
- `mark 1`

### Marking a task as undone: `unmark`
Marks the specified task as undone.

Format: `unmark INDEX`

Examples:
- `unmark 1`

### Finding tasks by keyword: `find`
Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

Examples:
- `find book`
- `find project meeting`

### Exiting the program: `bye`
Exits the program.

Format: `bye`

## Saving the data
Hugo data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file
Hugo data is saved automatically as a text file `[JAR file location]/data/hugo.txt`. Advanced users are welcome to update data directly by editing that data file.

## Command summary

| Action | Format, Examples |
|--------|------------------|
| Add todo | `todo TASK_DESCRIPTION`<br>e.g., `todo borrow book` |
| Add deadline | `deadline TASK_DESCRIPTION /by yyyy-MM-dd HH:mm`<br>e.g., `deadline return book /by 2023-10-10 18:00` |
| Add event | `event TASK_DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm`<br>e.g., `event project meeting /from 2023-10-10 14:00 /to 2023-10-10 16:00` |
| List | `list` |
| Delete | `delete INDEX`<br>e.g., `delete 2` |
| Mark | `mark INDEX`<br>e.g., `mark 1` |
| Unmark | `unmark INDEX`<br>e.g., `unmark 1` |
| Find | `find KEYWORD [MORE_KEYWORDS]`<br>e.g., `find book` |
| Exit | `bye` |