package command.taskcommand;

import tasklist.TaskList;
import tasklist.TaskListManager;

public class GetTasksCommand extends BaseTaskCommandWithFilter {
    public GetTasksCommand(TaskListManager taskListManager) {
        super(taskListManager);
    }

    @Override
    protected String commandName() {
        return "get tasks";
    }

    @Override
    protected void executeImpl(TaskList list, TaskFilterProps filters) {
        System.out.println(list.name());
        list.getItems(filters).forEach(System.out::println);
    }
}