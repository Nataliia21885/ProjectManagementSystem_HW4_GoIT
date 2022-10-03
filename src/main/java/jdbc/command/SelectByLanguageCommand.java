package jdbc.command;

import jdbc.repository.DevelopersSkillsRepository;
import jdbc.view.View;

public class SelectByLanguageCommand implements Command {

    private final View view;
    private final DevelopersSkillsRepository developersSkillsRepository;
    public static final String DEVELOPERS_BY_LANGUAGE_COMMAND = "developers_by_language";

    public SelectByLanguageCommand(View view, DevelopersSkillsRepository developersSkillsRepository) {
        this.view = view;
        this.developersSkillsRepository = developersSkillsRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DEVELOPERS_BY_LANGUAGE_COMMAND);
    }

    @Override
    public void execute() {
        view.write("Please, enter developer's language");
        developersSkillsRepository.developersByLanguage(view.read());
        view.write(String.format("\nPlease, write %s to see all command", HelpCommand.HELP_COMMAND));
    }
}
