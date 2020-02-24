package command.taskcommand;

import tasklist.TaskList;
import tasklist.TaskListManager;

public class DeleteTaskCommand extends BaseTaskCommandWithFilter {
    public DeleteTaskCommand(TaskListManager taskListManager) {
        super(taskListManager);
    }

    @Override
    protected String commandName() {
        return "delete task";
    }

    @Override
    protected void executeImpl(TaskList list, TaskFilterProps filters) {
        int itemsToDeleteCount = list.getItems(filters).size();
        list.remove(filters);
        System.out.println("Deleted " + itemsToDeleteCount + " tasks");
    }
}
