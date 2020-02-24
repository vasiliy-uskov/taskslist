package command.taskcommand;

import tasklist.TaskList;
import tasklist.TaskListManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

abstract class BaseTaskCommandWithFilter extends BaseTaskCommand {
    static private final Pattern idPattern = Pattern.compile("id\\s(\\d+)");
    static private final Pattern messagePattern = Pattern.compile("message\\s\"(.*)\"");

    BaseTaskCommandWithFilter(TaskListManager taskListManager) {
        super(taskListManager);
    }

    protected String commandArgumentsFormat() {
        return "[id {id}] [message \"{message}\"]";
    }

    protected final void executeImpl(TaskList list, String arguments) {
        if (!isFilter(arguments)) {
            processWrongCommandError();
            return;
        }
        TaskFilterProps filter = new TaskFilterProps();
        Matcher idMatcher = idPattern.matcher(arguments);
        if (idMatcher.find()) {
            int id = parseInt(idMatcher.group(1));
            filter.setTaskId(id);
        }
        Matcher messageMatcher = messagePattern.matcher(arguments);
        if (messageMatcher.find()) {
            filter.setMessage(messageMatcher.group(1));
        }
        executeImpl(list, filter);
    }

    private static boolean isFilter(String filterStr) {
        return filterStr != null && filterStr.matches("^(\\s+" + idPattern.pattern() + ")?(\\s+" + messagePattern.pattern() + ")?$");
    }


    abstract protected void executeImpl(TaskList list, TaskFilterProps filters);
}
