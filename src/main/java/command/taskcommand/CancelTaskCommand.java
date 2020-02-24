package command.taskcommand;

import tasklist.TaskList;
import tasklist.TaskListManager;

public class CancelTaskCommand extends BaseTaskCommandWithFilter {
    public CancelTaskCommand(TaskListManager taskListManager) {
        super(taskListManager);
    }

    @Override
    protected String commandName() {
        return "cancel task";
    }

    @Override
    protected void executeImpl(TaskList list, TaskFilterProps filters) {
        list.cancel(filters);
        System.out.println("Applied for " + list.getItems(filters).size() + " tasks");
    }
}
