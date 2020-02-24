import command.ICommand;
import command.taskcommand.*;
import command.tasklistcommand.CreateTaskListCommand;
import command.tasklistcommand.SaveTaskListCommand;
import command.tasklistcommand.SelectTaskListCommand;
import tasklist.TaskListManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) {
        TaskListManager taskListManager = new TaskListManager();
        List<? extends ICommand> commands = Arrays.asList(
                new CancelTaskCommand(taskListManager),
                new CreateTaskCommand(taskListManager),
                new DeleteTaskCommand(taskListManager),
                new GetTasksCommand(taskListManager),
                new MarkTaskAsDoneCommand(taskListManager),
                new CreateTaskListCommand(taskListManager),
                new SaveTaskListCommand(taskListManager),
                new SelectTaskListCommand(taskListManager)
        );
        String input = readLine();
        while (null != input) {
            ICommand command = findCommand(commands, input);
            if (command != null) {
                command.execute(input);
            } else {
                commands.forEach(com -> System.out.println(com.help()));
            }
            input = readLine();
        }
    }

    private static String readLine() {
        if (System.console() != null) {
            return System.console().readLine();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Can not read input");
            return null;
        }
    }

    private static ICommand findCommand(List<? extends ICommand> commands, String commandText) {
        for (ICommand command : commands) {
            if (command.is(commandText)) {
                return command;
            }
        }
        return null;
    }
}
