package command.taskcommand;

import tasklist.TaskList;
import tasklist.TaskListManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTaskCommand extends BaseTaskCommand {
    public CreateTaskCommand(TaskListManager taskListManager) {
        super(taskListManager);
    }

    protected String commandName() {
        return "add task";
    }

    protected String commandArgumentsFormat() {
        return "\"message\"";
    }

    protected void executeImpl(TaskList list, String arguments) {
        Matcher argumentMatcher = Pattern.compile("^\\s+\"(.*)\"$").matcher(arguments);
        if (!argumentMatcher.find()) {
            processWrongCommandError();
        } else {
            String message = argumentMatcher.group(1);
            list.add(message);
            System.out.println("Task created");
        }
    }
}
