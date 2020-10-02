import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    public static Task[] list = new Task[100];
    public static int listcounter = 0;

    /**
     * prints the existing Duke file task list and adds them to program's task list
     * @param filePath  directory of file Duke.txt
     * @throws FileNotFoundException when file Duke.txt do not exist
     * @throws DukeException when there are other errors in the Duke.txt text
     */
    public static void printFileContents(String filePath) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        System.out.println("Duke List file found! Adding tasks to the list...");

        while(s.hasNext()){
            String a = s.nextLine();

            if(a.charAt(0)=='T'){
                addTodo(a.substring(4));

                if(a.charAt(2)=='1'){
                    list[listcounter-1].markAsDone();
                }
            } else if(a.charAt(0)=='D'){
                String action = a.substring(4);
                int slashIndex = action.indexOf('|');
                String by = action.substring(slashIndex+1);
                String actiond = action.substring(0, slashIndex);

                Deadline d = new Deadline(actiond, by);
                if(a.charAt(2)=='1'){
                    d.markAsDone();
                }
                list[listcounter] = d;
                listcounter++;
            } else{
                String action = a.substring(4);
                int slashIndex = action.indexOf('|');
                String by = action.substring(slashIndex+1);
                String actione = action.substring(0, slashIndex);

                Event e = new Event(actione, by);
                if(a.charAt(2)=='1'){
                    e.markAsDone();
                }
                list[listcounter] = e;
                listcounter++;
            }
        }
        s.close();
    }

    /**
     * rewrite the Duke.txt file before program exits
     * @throws IOException when there is error in the text to write
     * @throws FileNotFoundException when there is no existing Duke.txt file to delete
     * @throws NoSuchFileException same as above
     */
    public static void refreshList() throws IOException, FileNotFoundException, NoSuchFileException {
        File newFile = new File("DukeList.txt");
        try{
            Files.delete(Paths.get(String.valueOf(newFile)));
        } catch (FileNotFoundException | NoSuchFileException e) {
            ;
        }
        File DukeFile = new File("DukeList.txt");
        FileWriter fw = new FileWriter(DukeFile, true);
        int counter=0;

        for (Task t:list) {
            if (t.getClass() == Event.class) {
                fw.write("E|");
                if (t.isDone == true) {
                    fw.write("1|" + t.description + "|" + ((Event) t).by + "\n");
                } else {
                    fw.write("0|" + t.description + "|" + ((Event) t).by + "\n");
                }
            } else if (t.getClass() == Todo.class) {
                fw.write("T|");
                if (t.isDone == true) {
                    fw.write("1|" + t.description + "\n");
                } else {
                    fw.write("0|" + t.description + "\n");
                }
            } else {
                fw.write("D|");
                if (t.isDone == true) {
                    fw.write("1|" + t.description + "|" + ((Deadline) t).by + "\n");
                } else {
                    fw.write("0|" + t.description + "|" + ((Deadline) t).by + "\n");
                }
            }
            counter++;
            if (counter == listcounter) {
                fw.close();
                break;
            }

        }
    }

    /**
     * constructs the task manager of the program
     */
    public TaskManager(){
        try{
            printFileContents("DukeList.txt");
        } catch(FileNotFoundException | DukeException e){
            System.out.println("File not found. Please re-create your list.");
        }
    }

    /**
     * lists all the current tasks in the program
     */
    public static void listTask(){
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<listcounter;i++){
            System.out.println((i + 1) + "." + list[i].toString());
        }
    }

    /**
     * adds a todo task into the task list
     * @param action the task to do
     * @throws DukeException when the user gives the wrong syntax for adding a todo
     */
    public static void addTodo(String action)throws DukeException {
        try {
            String description = action;
            Todo td = new Todo(description);
            list[listcounter] = td;
            System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                    + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
            listcounter++;
        } catch(ArrayIndexOutOfBoundsException | DukeException e){
            System.out.println("Oops! The description of a todo cannot be empty!");
        }
    }

    /**
     * adds a deadline task into the task list
     * @param action the task to do
     * @throws DukeException when the user gives the wrong syntax for adding a todo
     */
    public static void addDeadline(String action)throws DukeException {
        try {
            int slashIndex = action.indexOf("/");
            String deadlineBy = action.substring(slashIndex+1);
            Deadline dl = new Deadline(action.substring(0,slashIndex-1), deadlineBy);
            list[listcounter] = dl;
            System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                    + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
            listcounter++;
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Oops! The description of a deadline cannot be empty!");
        }
    }

    /**
     * adds an event task into the task list
     * @param action the task to do
     * @throws DukeException when the user gives the wrong syntax for adding a todo
     */
    public static void addEvent(String action)throws DukeException{
        try {
            int slashIndex = action.indexOf("/");
            String eventBy = action.substring(slashIndex+1);
            Event e = new Event(action.substring(0, slashIndex-1), eventBy);
            list[listcounter] = e;
            System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                    + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
            listcounter++;
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Oops! The description of an event cannot be empty!");
        }
    }

    /**
     * deletes a task from the task list
     * @param action the task to delete
     */
    public static void deleteTask(String action){
        System.out.println("Noted. I've removed this task:");
        int indexToDelete = Integer.parseInt(action);
        System.out.println(list[indexToDelete-1]);
        listcounter--;
        for(int j=(indexToDelete-1); j<listcounter; j++){
            list[j] = list[j+1];
        }
        System.out.println("Now you have " + listcounter + " tasks in the list.");
    }

    /**
     * marks a task from the task list as done
     * @param action the task to mark
     */
    public static void addDone(String action){
        try {
            int a = Integer.parseInt(action);
            list[a - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + list[a - 1].toString());
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Oops! The description of a done cannot be empty!");
        }
    }

    /**
     * finds the matching task from the task list and prints them out
     * @param action the task to find
     */
    public static void findTask(String action){
        System.out.println("Here are the matching tasks in your list:");
        for(int i=0;i<listcounter;i++){
            if(list[i].description.contains(action)){
                System.out.println((i+1) + "." + list[i].toString());
            }
        }
    }
}
