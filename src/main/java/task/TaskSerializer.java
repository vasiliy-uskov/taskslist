package task;

import com.google.gson.*;

import java.lang.reflect.Type;

public class TaskSerializer implements JsonSerializer<IReadonlyTask> {
    public JsonElement serialize(IReadonlyTask task, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject taskData = new JsonObject();
        taskData.add("id", new JsonPrimitive(task.id()));
        taskData.add("message", new JsonPrimitive(task.message()));
        taskData.add("status", new JsonPrimitive(task.status().toString()));
        return taskData;
    }
}
