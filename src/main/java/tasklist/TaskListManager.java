package tasklist;

import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class TaskListManager {
    private String taskListPath;
    private TaskList currentTaskList;

    public TaskList taskList() throws TaskListNotSelectedException {
        checkTaskListSelected();
        return currentTaskList;
    }

    public void createTaskList(String name, String taskListPath) throws JsonSyntaxException, IOException {
        if (isTaskListSelected()) {
            saveTaskList();
        }
        TaskList currentTaskList = new TaskList(name, new LinkedList<>());
        saveTaskListImpl(currentTaskList, taskListPath);
        this.currentTaskList = currentTaskList;
        this.taskListPath = taskListPath;
    }

    public void readTaskList(String taskListPath) throws JsonSyntaxException, IOException {
        if (isTaskListSelected()) {
            saveTaskListImpl(currentTaskList, this.taskListPath);
        }
        currentTaskList = TaskListIO.readTaskList(new FileReader(taskListPath));
        this.taskListPath = taskListPath;
    }

    public void saveTaskList() throws TaskListNotSelectedException, IOException {
        checkTaskListSelected();
        saveTaskListImpl(currentTaskList, taskListPath);
    }

    private void checkTaskListSelected() throws TaskListNotSelectedException {
        if (!isTaskListSelected()) {
            throw new TaskListNotSelectedException();
        }
    }

    private void saveTaskListImpl(TaskList list, String path) throws IOException {
        TaskListIO.writeTaskList(list, new FileWriter(path));
    }

    private boolean isTaskListSelected() {
        return currentTaskList != null;
    }
}
