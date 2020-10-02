import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        TaskManager TM = new TaskManager();

        while(true){
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();

            String[] action = line.split(" ", 2);

            if(action[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                TM.refreshList();
                break;

            } else if (action[0].equals("list")) {
                TM.listTask();

            } else if (action[0].equals("done")) {
                TM.addDone(action[1]);

            } else if (action[0].equals("todo")) {
                TM.addTodo(action[1]);

            } else if (action[0].equals("deadline")) {
                TM.addDeadline(action[1]);

            } else if (action[0].equals("event")) {
                TM.addEvent(action[1]);

            } else if (action[0].equals("delete")) {
                TM.deleteTask(action[1]);

            } else if (action[0].equals("find")){
                TM.findTask(action[1]);

            } else {
                System.out.println("Oops! I'm sorry I have no idea what that means!");
            }
        }
    }
}
