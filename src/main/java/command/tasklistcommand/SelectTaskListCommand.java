package command.tasklistcommand;

import com.google.gson.JsonParseException;
import command.BaseCommand;
import tasklist.TaskListManager;

import java.io.IOException;

public class SelectTaskListCommand extends BaseCommand {
    private final TaskListManager taskListManager;

    public SelectTaskListCommand(TaskListManager taskListManager) {
        this.taskListManager = taskListManager;
    }

    @Override
    protected String commandName() {
        return "use";
    }

    @Override
    protected String commandArgumentsFormat() {
        return "{path}";
    }

    @Override
    protected void executeImpl(String path) {
        try {
            taskListManager.readTaskList(path.trim());
            System.out.println("Task list selected");
        } catch (JsonParseException e) {
            System.out.println("Can not parse task in " + path);
        } catch (IOException e) {
            System.out.println("Can not use " + path);
        }
    }
}
