import java.io.FileNotFoundException;
import java.io.IOException;

public class TaskList {
    public static Task[] list = new Task[100];
    public static int listcounter = 0;

    public TaskList() {
        try {
            Storage.printFileContents("DukeList.txt", this);
        } catch (FileNotFoundException | DukeException e) {
            System.out.println("File not found. Please re-create your list.");
        }
    }

    /**
     * Refreshes the task list
     *
     * @param TL current task list
     * @throws IOException catches input output error
     */
    public static void refreshList(TaskList TL) throws IOException {
        Storage.refreshList(TL);
    }

    /**
     * Lists all the current tasks in the task list
     */
    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listcounter; i++) {
            System.out.println((i + 1) + "." + list[i].toString());
        }
    }

    /**
     * Add a todo item into the task list
     *
     * @param action the todo description
     * @throws DukeException catches format error
     */
    public static void addTodo(String action) throws DukeException {
        try {
            String description = action;
            Todo td = new Todo(description);
            list[listcounter] = td;
            System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                    + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
            listcounter++;
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("Oops! The description of a todo cannot be empty!");
        }
    }

    /**
     * Adds a deadline item into the task list
     *
     * @param action the deadline description
     * @throws DukeException catches format error
     */
    public static void addDeadline(String action) throws DukeException {
        try {
            int slashIndex = action.indexOf("/");
            String deadlineBy = action.substring(slashIndex + 1);
            Deadline dl = new Deadline(action.substring(0, slashIndex - 1), deadlineBy);
            list[listcounter] = dl;
            System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                    + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
            listcounter++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a deadline cannot be empty!");
        }
    }

    /**
     * Adds an event item into the task list
     *
     * @param action the event description
     * @throws DukeException catches format error
     */
    public static void addEvent(String action) throws DukeException {
        try {
            int slashIndex = action.indexOf("/");
            String eventBy = action.substring(slashIndex + 1);
            Event e = new Event(action.substring(0, slashIndex - 1), eventBy);
            list[listcounter] = e;
            System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                    + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
            listcounter++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! The description of an event cannot be empty!");
        }
    }

    /**
     * Deletes a current task
     *
     * @param action the task to delete
     */
    public static void deleteTask(String action) {
        System.out.println("Noted. I've removed this task:");
        int indexToDelete = Integer.parseInt(action);
        System.out.println(list[indexToDelete - 1]);
        listcounter--;
        for (int j = (indexToDelete - 1); j < listcounter; j++) {
            list[j] = list[j + 1];
        }
        System.out.println("Now you have " + listcounter + " tasks in the list.");
    }

    /**
     * Marks a current task as done
     *
     * @param action the task to be marked
     */
    public static void addDone(String action) {
        try {
            int a = Integer.parseInt(action);
            list[a - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + list[a - 1].toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a done cannot be empty!");
        }
    }

    /**
     * Finds a current task in the task list
     *
     * @param action the task keyword/phrase to find
     */
    public static void findTask(String action) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < listcounter; i++) {
            if (list[i].description.contains(action)) {
                System.out.println((i + 1) + "." + list[i].toString());
            }
        }
    }
}
