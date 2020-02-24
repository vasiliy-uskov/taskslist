package task;

import com.google.gson.*;

import java.lang.reflect.Type;

public class TaskParser implements JsonDeserializer<Task> {
    public Task deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject data = jsonElement.getAsJsonObject();
        int id = data.get("id").getAsInt();
        String message = data.get("message").getAsString();
        TaskStatus status = TaskStatus.fromString(data.get("status").getAsString());
        return new Task(id, message, status);
    }
}
