package command.tasklistcommand;

import com.google.gson.JsonParseException;
import command.BaseCommand;
import tasklist.TaskListManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTaskListCommand extends BaseCommand {
    static private final Pattern argumentsPattern = Pattern.compile("^\\s+\"(.+)\"\\s+(.+)$");
    private final TaskListManager taskListManager;

    public CreateTaskListCommand(TaskListManager taskListManager) {
        this.taskListManager = taskListManager;
    }

    @Override
    protected String commandName() {
        return "create";
    }

    @Override
    protected String commandArgumentsFormat() {
        return "\"{name}\" {path}";
    }

    @Override
    protected void executeImpl(String arguments) {
        Matcher argumentsMatcher = argumentsPattern.matcher(arguments);
        if (!argumentsMatcher.find()) {
            processWrongCommandError();
            return;
        }
        createTask(argumentsMatcher.group(1), argumentsMatcher.group(2));
    }

    private void createTask(String name, String path) {
        try {
            taskListManager.createTaskList(name, path);
            System.out.println("Task list created");
        } catch (JsonParseException e) {
            System.out.println("Can not parse task in " + path);
        } catch (IOException e) {
            System.out.println("Can not read " + path);
        }
    }
}