package command.taskcommand;

import tasklist.ITaskFilter;

public class TaskFilterProps implements ITaskFilter {
    private int id = -1;
    private String message;

    void setTaskId(int id) {
        this.id = id;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public int id() {
        return id;
    }

    public String message() {
        return message;
    }
}
