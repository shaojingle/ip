** Shao Jing Duke User Guide **

1. Open the ./ip project in Intellij.
2. Build & Run the file (Duke.java is the main file!)
3. The program will search for a Duke.txt file from the ./ip project directory. If there is an existing file,
the program will read and create each line. If not, the program will prompt the user to create one.
4. Refer to the following commands and syntax for the use of the program:
-List the current tasks:
list
-Add a todo task:
todo task
-Add a deadline task:
deadline task /by
-Add an event task:
event task /by
-Mark a task as done:
done task
-Delete a task from task list:
delete task
-Find tasks:
find task
-Exit the program:
bye
5. When the user inputs "bye", the program will overwrite the Duke.txt file with the updated task list before exitting the program.