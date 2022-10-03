package jdbc.view;

import jdbc.command.Command;
import jdbc.command.HelpCommand;

import java.util.List;

public class Controller {

    private final View view;
    private final List<Command> commandList;

    public Controller(View view, List<Command> commandList) {
        this.view = view;
        this.commandList = commandList;
    }

    public void run() {
        view.write(String.format("Hello, please enter %s to see all command", HelpCommand.HELP_COMMAND));
        try {
            execute();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void execute() {
        while (true) {
            String input = view.read();
            boolean isInputCorrect = false;
            for (Command command : commandList) {
                if (command.canExecute(input)) {
                    command.execute();
                    isInputCorrect = true;
                }
            }
            if (!isInputCorrect) {
                view.write(String.format("Command not found. Please enter %s to see all commands", HelpCommand.HELP_COMMAND));
            }
        }
    }
}

