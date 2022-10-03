package jdbc.command;

import jdbc.service.*;
import jdbc.view.View;

public class FindAllCommand implements Command {

    private final View view;
    public static final String FIND_ALL = "find_all";
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final DeveloperService developerService;
    private final ProjectService projectService;
    private final SkillService skillService;

    public FindAllCommand(View view, CompanyService companyService, CustomerService customerService,
                          DeveloperService developerService, ProjectService projectService, SkillService skillService) {
        this.view = view;
        this.companyService = companyService;
        this.customerService = customerService;
        this.developerService = developerService;
        this.projectService = projectService;
        this.skillService = skillService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_ALL);
    }

    @Override
    public void execute() {
        boolean start = true;
        while (start) {
            view.write("Please, write the entity's name which you're going to read: " +
                    "company, customer, developer, project, skill");
            String temp = view.read();
            switch (temp) {
                case "company" -> {
                    findAllCompany();
                    start = false;
                }
                case "customer" -> {
                    findAllCustomer();
                    start = false;
                }
                case "developer" -> {
                    findAllDeveloper();
                    start = false;
                }
                case "project" -> {
                    findAllProject();
                    start = false;
                }
                case "skill" -> {
                    findAllSkill();
                    start = false;
                }
            }
            view.write(String.format("Please, write %s to see all command", HelpCommand.HELP_COMMAND));
        }
    }

    private void findAllCompany() {
        System.out.println(companyService.findAll());
    }

    private void findAllCustomer() {
        System.out.println(customerService.findAll());
    }

    private void findAllDeveloper() {
        System.out.println(developerService.findAll());
    }

    private void findAllProject() {
        System.out.println(projectService.findAll());
    }

    private void findAllSkill() {
        System.out.println(skillService.findAll());
    }
}
