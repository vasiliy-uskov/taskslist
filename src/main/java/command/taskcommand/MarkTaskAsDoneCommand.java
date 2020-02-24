package command.taskcommand;

import tasklist.TaskList;
import tasklist.TaskListManager;

public class MarkTaskAsDoneCommand extends BaseTaskCommandWithFilter {
    public MarkTaskAsDoneCommand(TaskListManager taskListManager) {
        super(taskListManager);
    }

    @Override
    protected String commandName() {
        return "task done";
    }

    @Override
    protected void executeImpl(TaskList list, TaskFilterProps filters) {
        list.markAsDone(filters);
        System.out.println("Applied for " + list.getItems(filters).size() + " tasks");
    }
}