public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        try {
            if(description=="") {
                throw new DukeException();
            }
            else{
                this.description = description;
                this.isDone = false;
            }
        } catch (DukeException e){
            System.out.println("Description cannot be empty!");
        }
    }

    /**
     * status icon of the task whether done/not done
     * @return string of a tick/cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}