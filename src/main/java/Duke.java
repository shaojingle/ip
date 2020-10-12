import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        Ui.welcome();
        TaskList TL = new TaskList();
        boolean exit = false;
        while(!exit) {
            String[] action = Ui.read().split(" ", 2);
            Parser parse = new Parser(action, TL);
            exit = parse.exit;
        }
    }
}
