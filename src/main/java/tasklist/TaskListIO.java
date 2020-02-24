package tasklist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import task.IReadonlyTask;
import task.Task;
import task.TaskParser;
import task.TaskSerializer;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

class TaskListIO {
    static void writeTaskList(TaskList list, Writer writer) throws IOException {
        Gson serializer = TaskListIO.createSerializer();
        String json = serializer.toJson(list, TaskList.class);
        writer.write(json);
        writer.close();
    }

    static TaskList readTaskList(Reader reader) throws JsonIOException, JsonSyntaxException {
        Gson parser = TaskListIO.createParser();
        return parser.fromJson(reader, TaskList.class);
    }

    private static Gson createSerializer() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IReadonlyTask.class, new TaskSerializer());
        builder.registerTypeAdapter(TaskList.class, new TaskListSerializer());
        return builder.create();
    }

    private static Gson createParser() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Task.class, new TaskParser());
        builder.registerTypeAdapter(TaskList.class, new TaskListParser());
        return builder.create();
    }
}
