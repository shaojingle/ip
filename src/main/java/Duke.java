import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Task[] list = new Task[100];
        int listcounter = 0;

        while(true){
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            String[] action = line.split(" ", 2);

            if(action[0].equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if(action[0].equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<listcounter;i++){
                    System.out.println((i + 1) + "." + list[i].toString());
                }

            } else if(action[0].equals("done")){
                try {
                    int a = Integer.parseInt(action[1]);
                    list[a - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + list[a - 1].toString());
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Oops! The description of a done cannot be empty!");
                }
            } else if(action[0].equals("todo")) {
                try {
                    Todo td = new Todo(action[1]);
                    list[listcounter] = td;
                    System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                            + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
                    listcounter++;
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Oops! The description of a todo cannot be empty!");
                }

            } else if(action[0].equals("deadline")){
                try {
                    int slashIndex = action[1].indexOf("/");
                    String deadlineBy = action[1].substring(slashIndex+1);
                    Deadline dl = new Deadline(action[1].substring(0,slashIndex-1), deadlineBy);
                    list[listcounter] = dl;
                    System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                            + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
                    listcounter++;
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Oops! The description of a deadline cannot be empty!");
                }

            } else if (action[0].equals("event")){
                try {
                    int slashIndex = action[1].indexOf("/");
                    String eventBy = action[1].substring(slashIndex+1);
                    Event e = new Event(action[1].substring(0, slashIndex-1), eventBy);
                    list[listcounter] = e;
                    System.out.println("Got it. I've added this task:" + System.lineSeparator() + list[listcounter].toString()
                            + System.lineSeparator() + "Now you have " + (listcounter + 1) + " tasks in the list.");
                    listcounter++;
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Oops! The description of an event cannot be empty!");
                }

            } else if (action[0].equals("delete")){
                System.out.println("Noted. I've removed this task:");
                int indexToDelete = Integer.parseInt(action[1]);
                System.out.println(list[indexToDelete-1]);
                listcounter--;
                for(int j=(indexToDelete-1); j<listcounter; j++){
                    list[j] = list[j+1];
                }
                System.out.println("Now you have " + listcounter + " tasks in the list.");
            } else{
                System.out.println("Oops! I'm sorry I have no idea what that means!");
            }
        }
    }
}
