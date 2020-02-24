package command;

public interface ICommand {
    boolean is(String command);

    void execute(String command);

    String help();
}