package tasklist;

import com.google.gson.*;
import task.Task;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

class TaskListParser implements JsonDeserializer<TaskList> {
    public TaskList deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject data = jsonElement.getAsJsonObject();
        String name = data.get("name").getAsString();
        return new TaskList(name, parseTasks(data.get("tasks").getAsJsonArray(), context));
    }

    private List<Task> parseTasks(JsonArray tasksData, JsonDeserializationContext context) {
        LinkedList<Task> tasks = new LinkedList<>();
        for (JsonElement taskData : tasksData) {
            tasks.add(context.deserialize(taskData, Task.class));
        }
        return tasks;
    }
}
