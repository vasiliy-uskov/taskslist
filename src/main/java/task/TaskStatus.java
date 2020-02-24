package task;

public enum TaskStatus {
    DONE("done"),
    IN_PROGRESS("in progress"),
    CANCELED("canceled");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public static TaskStatus fromString(String status) {
        switch (status) {
            case "done":
                return TaskStatus.DONE;
            case "in progress":
                return TaskStatus.IN_PROGRESS;
            case "canceled":
                return TaskStatus.CANCELED;
        }
        throw new Error("Can not create status from " + status);
    }

    public String toString() {
        return status;
    }
}