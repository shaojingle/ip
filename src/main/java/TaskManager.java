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

    public TaskManager(){
        try{
            printFileContents("DukeList.txt");
        } catch(FileNotFoundException | DukeException e){
            System.out.println("File not found. Please re-create your list.");
        }
    }

    public static void listTask(){
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<listcounter;i++){
            System.out.println((i + 1) + "." + list[i].toString());
        }
    }

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
    public static void addDone(String action){
        try {
            int a = Integer.parseInt(action);
            list[a - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + list[a - 1].toString());
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Oops! The description of a done cannot be empty!");
        }
    }

    public static void findTask(String action){
        System.out.println("Here are the matching tasks in your list:");
        for(int i=0;i<listcounter;i++){
            if(list[i].description.contains(action)){
                System.out.println((i+1) + "." + list[i].toString());
            }
        }
    }
}
