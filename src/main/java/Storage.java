import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    /**
     * Reads and prints current task list in DukeList.txt file
     * @param filePath a relative pathing to DukeList.txt file in same directory
     * @param TL current task list
     * @throws FileNotFoundException catches missing file error
     * @throws DukeException catches format error
     */
    public static void printFileContents(String filePath, TaskList TL) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        System.out.println("Duke List file found! Adding tasks to the list...");

        while(s.hasNext()) {
            String a = s.nextLine();

            if(a.charAt(0)=='T') {
                TaskList.addTodo(a.substring(4));

                if(a.charAt(2)=='1') {
                    TL.list[TL.listcounter-1].markAsDone();
                }
            } else if(a.charAt(0)=='D') {
                String action = a.substring(4);
                int slashIndex = action.indexOf('|');
                String by = action.substring(slashIndex+1);
                String actiond = action.substring(0, slashIndex);

                Deadline d = new Deadline(actiond, by);
                if(a.charAt(2)=='1') {
                    d.markAsDone();
                }
                TL.list[TL.listcounter] = d;
                TL.listcounter++;
            } else {
                String action = a.substring(4);
                int slashIndex = action.indexOf('|');
                String by = action.substring(slashIndex+1);
                String actione = action.substring(0, slashIndex);

                Event e = new Event(actione, by);
                if(a.charAt(2)=='1') {
                    e.markAsDone();
                }
                TL.list[TL.listcounter] = e;
                TL.listcounter++;
            }
        }
        s.close();
    }

    /**
     * Saves into a newly recreated DukeList.txt file before exiting the program
     * @param TL current task list
     * @throws IOException catches input output error
     * @throws FileNotFoundException catches missing file error
     * @throws NoSuchFileException catches missing file error
     */
    public static void refreshList(TaskList TL) throws IOException, FileNotFoundException, NoSuchFileException {
        File newFile = new File("DukeList.txt");
        try {
            Files.delete(Paths.get(String.valueOf(newFile)));
        } catch (FileNotFoundException | NoSuchFileException e) {
            ;
        }
        File DukeFile = new File("DukeList.txt");
        FileWriter fw = new FileWriter(DukeFile, true);
        int counter=0;

        for (Task t:TL.list) {
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
            if (counter == TL.listcounter) {
                fw.close();
                break;
            }
        }
    }
}
