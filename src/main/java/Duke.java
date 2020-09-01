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
            }
            else if(line.equals("list")){
                for(int i=0;i<listcounter;i++){
                    System.out.println((i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
            }
            else if(line.contains("done")){
                int a = Character.getNumericValue(line.charAt(5));
                list[a-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + "[" +
                        list[a-1].getStatusIcon() + "] " + list[a-1].description);

            }
            else{
                Task t = new Task();
                t.Task(line);
                list[listcounter] = t;
                System.out.println("added: " + line);
                listcounter++;
            }
        }
    }
}
