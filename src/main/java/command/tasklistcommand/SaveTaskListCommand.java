package command.tasklistcommand;

import command.ICommand;
import tasklist.TaskListManager;
import tasklist.TaskListNotSelectedException;

import java.io.IOException;

public class SaveTaskListCommand implements ICommand {
    private final TaskListManager taskListManager;

    public SaveTaskListCommand(TaskListManager taskListManager) {
        this.taskListManager = taskListManager;
    }

    @Override
    public boolean is(String command) {
        return command != null && command.equals("save");
    }

    @Override
    public void execute(String command) {
        try {
            taskListManager.saveTaskList();
            System.out.println("Task list saved");
        } catch (TaskListNotSelectedException e) {
            System.out.println("No task list selected");
        } catch (IOException e) {
            System.out.println("Can not write to file");
        }
    }

    @Override
    public String help() {
        return "save";
    }
}