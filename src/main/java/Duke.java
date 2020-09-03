import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Task[] list = new Task[100];
        int listcounter = 0;

        while(true){
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if(line.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<listcounter;i++){
                    System.out.println((i + 1) + "." + list[i].toString());
                }

            } else if((line.substring(0,4)).equals("done")){
                int a = Character.getNumericValue(line.charAt(5));
                list[a-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + list[a-1].toString());

            } else if((line.substring(0,4)).equals("todo")) {
                String todostring = line.substring(5);
                Todo td = new Todo(todostring);
                list[listcounter] = td;
                System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                + System.lineSeparator() + "Now you have " + (listcounter+1) + " tasks in the list.");
                listcounter++;

            } else if((line.substring(0,8)).equals("deadline")){
                int findindex = line.indexOf("/");
                String deadlinestring = line.substring(9,findindex);
                String deadlineby = line.substring(findindex+1);
                Deadline dl = new Deadline(deadlinestring, deadlineby);
                list[listcounter] = dl;
                System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                        + System.lineSeparator() + "Now you have " + (listcounter+1) + " tasks in the list.");
                listcounter++;

            } else if ((line.substring(0,5)).equals("event")){
                int findindex = line.indexOf("/");
                String eventstring = line.substring(6,findindex);
                String eventby = line.substring(findindex+1);
                Event e = new Event(eventstring, eventby);
                list[listcounter] = e;
                System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                        + System.lineSeparator() + "Now you have " + (listcounter+1) + " tasks in the list.");
                listcounter++;

            } else{
                Task t = new Task(line);
                list[listcounter] = t;
                System.out.println("added: " + line);
                listcounter++;
            }
        }
    }
}
