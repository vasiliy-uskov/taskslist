package tasklist;

import task.IReadonlyTask;
import task.Task;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class EmptyTaskFilter implements ITaskFilter {
    @Override
    public int id() {
        return -1;
    }

    @Override
    public String message() {
        return null;
    }
}

public class TaskList {
    private final String name;
    private final LinkedList<Task> tasks;

    TaskList(String name, Collection<Task> tasks) {
        this.name = name;
        this.tasks = new LinkedList<>(tasks);
    }

    public String name() {
        return name;
    }

    public void add(String taskMessage) {
        int elementId = 0;
        try {
            IReadonlyTask lastTask = tasks.getLast();
            elementId = lastTask.id() + 1;
        } catch (NoSuchElementException ignored) {
        }
        tasks.add(Task.creteDefaultDate(elementId, taskMessage));
    }

    public void remove(ITaskFilter filter) {
        tasks.removeAll(filterTasks(filter).collect(Collectors.toList()));
    }

    public void cancel(ITaskFilter filter) {
        this.executeForTask(filter, Task::cancel);
    }

    public void markAsDone(ITaskFilter filter) {
        this.executeForTask(filter, Task::markAsDone);
    }

    public List<? extends IReadonlyTask> getItems(ITaskFilter filter) {
        return filterTasks(filter).collect(Collectors.toList());
    }

    List<? extends IReadonlyTask> getItems() {
        return getItems(new EmptyTaskFilter());
    }

    private void executeForTask(ITaskFilter filter, Consumer<Task> action) {
        filterTasks(filter).forEach(action);
    }

    private Stream<Task> filterTasks(ITaskFilter filter) {
        return tasks.stream()
                .filter((task) -> filter.id() < 0 || filter.id() == task.id())
                .filter((task) -> filter.message() == null || task.message().contains(filter.message()));
    }
}
