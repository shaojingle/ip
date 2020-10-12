import java.io.IOException;

public class Parser {
    public boolean exit = false;

    /**
     * Determines the action to do based on command given
     * @param action the action to be implemented
     * @param TL current task list
     * @throws IOException
     * @throws DukeException
     */
    public Parser(String[] action, TaskList TL) throws IOException, DukeException {
        if(action[0].equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            TL.refreshList(TL);
            exit = true;
        } else if(action[0].equals("list")) {
            TL.listTask();

        } else if(action[0].equals("done")) {
            TL.addDone(action[1]);

        } else if(action[0].equals("todo")) {
            TL.addTodo(action[1]);

        } else if(action[0].equals("deadline")) {
            TL.addDeadline(action[1]);

        } else if (action[0].equals("event")) {
            TL.addEvent(action[1]);

        } else if (action[0].equals("delete")) {
            TL.deleteTask(action[1]);

        } else if (action[0].equals("find")){
            TL.findTask(action[1]);

        } else {
            System.out.println("Oops! I'm sorry I have no idea what that means!");
        }
    }
}
