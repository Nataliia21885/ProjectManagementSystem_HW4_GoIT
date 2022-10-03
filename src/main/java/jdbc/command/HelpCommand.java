package jdbc.command;

import jdbc.view.View;

public class HelpCommand implements Command {

    public static final String HELP_COMMAND = "help";
    private final View view;

    public HelpCommand(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP_COMMAND);
    }

    @Override
    public void execute() {
        view.write(String.format("Please, enter %s to see menu of creating entity", CreateCommand.CREATE_NEW_ENTITY));
        view.write(String.format("Please, enter %s to see menu of deleting entity", DeleteCommand.DELETE_ENTITY));
        view.write(String.format("Please, enter %s to see menu of updating entity", UpdateCommand.UPDATE_ENTITY));
        view.write(String.format("Please, enter %s to see menu of finding entity", FindAllCommand.FIND_ALL));
        view.write(String.format("Please, enter %s to see menu of finding entity by id", FindByIdCommand.FIND_BY_ID_COMMAND));
        view.write(String.format("Please, enter %s to find developers on certain project", DevelopersOnProjectCommand.DEVELOPERS_ON_PROJECT_COMMAND));
        view.write(String.format("Please, enter %s to find all project", ListOfProjectsByDateCommand.LIST_OF_PROJECT_COMMAND));
        view.write(String.format("Please, enter %s to find developer's salary on certain project", SalaryByProjectCommand.SALARY_BY_PROJECT_COMMAND));
        view.write(String.format("Please, enter %s to find developers by language", SelectByLanguageCommand.DEVELOPERS_BY_LANGUAGE_COMMAND));
        view.write(String.format("Please, enter %s to find developers by level", SelectByLevelCommand.DEVELOPERS_BY_LEVEL_COMMAND));
        view.write(String.format("Please, enter %s to exit", ExitCommand.EXIT_COMMAND));
    }
}
