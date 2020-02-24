package task;

public class Task implements IReadonlyTask {
    private final int id;
    private final String message;
    private TaskStatus status;

    Task(int id, String message, TaskStatus status) {
        this.id = id;
        this.message = message;
        this.status = status;
    }

    public static Task creteDefaultDate(int id, String message) {
        return new Task(id, message, TaskStatus.IN_PROGRESS);
    }

    public int id() {
        return id;
    }

    public String message() {
        return message;
    }

    public TaskStatus status() {
        return status;
    }

    public void cancel() {
        status = TaskStatus.CANCELED;
    }

    public void markAsDone() {
        status = TaskStatus.DONE;
    }

    @Override
    public String toString() {
        return "[" + id + "]" + this.message + " " + this.status;
    }
}
