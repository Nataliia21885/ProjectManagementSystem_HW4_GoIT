package jdbc.command;

import jdbc.repository.DevelopersProjectsRepository;
import jdbc.view.View;

public class SalaryByProjectCommand implements Command {
    private final View view;
    private final DevelopersProjectsRepository developersProjectsRepository;
    public static final String SALARY_BY_PROJECT_COMMAND = "salary_by_projects";

    public SalaryByProjectCommand(View view, DevelopersProjectsRepository developersProjectsRepository) {
        this.view = view;
        this.developersProjectsRepository = developersProjectsRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(SALARY_BY_PROJECT_COMMAND);
    }

    @Override
    public void execute() {
        view.write("Please, enter project name");
        developersProjectsRepository.salaryByProject(view.read());
        view.write(String.format("\nPlease, write %s to see all command", HelpCommand.HELP_COMMAND));
    }
}
