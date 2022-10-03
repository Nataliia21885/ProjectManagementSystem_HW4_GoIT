package jdbc.command;

import jdbc.repository.DevelopersProjectsRepository;
import jdbc.view.View;

public class DevelopersOnProjectCommand implements Command {

    private final View view;
    private final DevelopersProjectsRepository developersProjectsRepository;
    public static final String DEVELOPERS_ON_PROJECT_COMMAND = "developers_on_projects";

    public DevelopersOnProjectCommand(View view, DevelopersProjectsRepository developersProjectsRepository) {
        this.view = view;
        this.developersProjectsRepository = developersProjectsRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DEVELOPERS_ON_PROJECT_COMMAND);
    }

    @Override
    public void execute() {
        view.write("Please, enter project name");
        developersProjectsRepository.quantityOfDevelopersInProject(view.read());
        view.write(String.format("\nPlease, write %s to see all command", HelpCommand.HELP_COMMAND));
    }
}
