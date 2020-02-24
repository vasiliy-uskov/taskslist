package tasklist;

import com.google.gson.*;
import task.IReadonlyTask;

import java.lang.reflect.Type;
import java.util.List;

class TaskListSerializer implements JsonSerializer<TaskList> {
    public JsonElement serialize(TaskList list, Type type, JsonSerializationContext context) {
        JsonObject listData = new JsonObject();
        listData.add("name", new JsonPrimitive(list.name()));
        listData.add("tasks", serializeTasksData(list, context));
        return listData;
    }

    private JsonArray serializeTasksData(TaskList list, JsonSerializationContext context) {
        JsonArray tasksData = new JsonArray();
        List<? extends IReadonlyTask> tasks = list.getItems();
        tasks.forEach(task -> tasksData.add(context.serialize(task, IReadonlyTask.class)));
        return tasksData;
    }
}
