package task;


public interface IReadonlyTask {
    int id();

    String message();

    TaskStatus status();
}
