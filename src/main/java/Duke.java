import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String[] list = new String[100];
        int listcounter = 0;
        while(true) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(line.equals("list")){
                for(int i=0;i<listcounter;i++){
                    System.out.println((i+1) + ". " + list[i]);
                }
            }
            else{
                list[listcounter] = line;
                System.out.println("added: " + line);
                listcounter++;
            }
        }
    }
}
