package jdbc.command;

import jdbc.repository.DevelopersSkillsRepository;
import jdbc.view.View;

public class SelectByLevelCommand implements Command {

    private final View view;
    private final DevelopersSkillsRepository developersSkillsRepository;
    public static final String DEVELOPERS_BY_LEVEL_COMMAND = "developers_by_level";

    public SelectByLevelCommand(View view, DevelopersSkillsRepository developersSkillsRepository) {
        this.view = view;
        this.developersSkillsRepository = developersSkillsRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DEVELOPERS_BY_LEVEL_COMMAND);
    }

    @Override
    public void execute() {
        view.write("Please, enter developer's level");
        developersSkillsRepository.developersByLevel(view.read());
        view.write(String.format("\nPlease, write %s to see all command", HelpCommand.HELP_COMMAND));
    }
}
