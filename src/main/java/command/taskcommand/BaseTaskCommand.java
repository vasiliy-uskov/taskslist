package command.taskcommand;

import command.BaseCommand;
import tasklist.TaskList;
import tasklist.TaskListManager;
import tasklist.TaskListNotSelectedException;


abstract class BaseTaskCommand extends BaseCommand {
    private final TaskListManager taskListManager;

    BaseTaskCommand(TaskListManager taskListManager) {
        this.taskListManager = taskListManager;
    }

    protected final void executeImpl(String arguments) {
        try {
            executeImpl(taskListManager.taskList(), arguments);
        } catch (TaskListNotSelectedException err) {
            System.out.println("No task list selected");
        }
    }

    protected abstract void executeImpl(TaskList list, String arguments);
}
