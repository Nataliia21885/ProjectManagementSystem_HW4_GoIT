package jdbc.command;

import jdbc.repository.DevelopersProjectsRepository;
import jdbc.view.View;

public class ListOfProjectsByDateCommand implements Command {

    private final View view;
    private final DevelopersProjectsRepository developersProjectsRepository;
    public static final String LIST_OF_PROJECT_COMMAND = "list_of_projects";

    public ListOfProjectsByDateCommand(View view, DevelopersProjectsRepository developersProjectsRepository) {
        this.view = view;
        this.developersProjectsRepository = developersProjectsRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LIST_OF_PROJECT_COMMAND);
    }

    @Override
    public void execute() {
        developersProjectsRepository.listOfProjects();
        view.write(String.format("\nPlease, write %s to see all command", HelpCommand.HELP_COMMAND));
    }
}
