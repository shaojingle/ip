import java.util.Scanner;

public class Ui {

    /**
     * Welcomes the user
     */
    public static void welcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads the command input by user
     * @return returns the given command input
     */
    public static String read() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        return line;
    }
}
