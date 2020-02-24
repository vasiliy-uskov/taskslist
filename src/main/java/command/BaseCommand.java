package command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseCommand implements ICommand {
    @Override
    public boolean is(String command) {
        return command != null && command.matches(commandPattern());
    }

    @Override
    public void execute(String command) {
        Matcher addTaskMatcher = Pattern.compile(commandPattern()).matcher(command);
        if (addTaskMatcher.find()) {
            String arguments = addTaskMatcher.group(1);
            executeImpl(arguments);
        } else {
            processWrongCommandError();
        }
    }

    @Override
    public String help() {
        return commandName() + " " + commandArgumentsFormat();
    }

    private String commandPattern() {
        return "^" + commandName() + "(.*)$";
    }

    protected final void processWrongCommandError() {
        System.out.println("Wrong command. Use:");
        System.out.println(help());
    }

    protected abstract String commandName();

    protected abstract String commandArgumentsFormat();

    protected abstract void executeImpl(String arguments);
}
