package jdbc.command;

import jdbc.model.dto.*;
import jdbc.service.*;
import jdbc.view.View;

public class FindByIdCommand implements Command {

    private final View view;
    public static final String FIND_BY_ID_COMMAND = "find_by_id";
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final DeveloperService developerService;
    private final ProjectService projectService;
    private final SkillService skillService;

    public FindByIdCommand(View view, CompanyService companyService, CustomerService customerService,
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
        return input.equals(FIND_BY_ID_COMMAND);
    }

    @Override
    public void execute() {
        boolean start = true;
        while (start) {
            view.write("Please, write the entity's name which you're going to read by id: " +
                    "company, customer, developer, project, skill");
            String temp = view.read();
            switch (temp) {
                case "company" -> {
                    findByIdCompany();
                    start = false;
                }
                case "customer" -> {
                    findByIdCustomer();
                    start = false;
                }
                case "developer" -> {
                    findByIdDeveloper();
                    start = false;
                }
                case "project" -> {
                    findByIdProject();
                    start = false;
                }
                case "skill" -> {
                    findByIdSkill();
                    start = false;
                }
            }
            view.write(String.format("Please, write %s to see all command", HelpCommand.HELP_COMMAND));
        }
    }

    private void findByIdCompany() {
        CompanyDto companyDto = new CompanyDto();
        view.write("Enter id of searching entity");
        while (true) {
            try {
                companyDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        System.out.println(companyService.getByID(companyDto.getId()));
    }

    private void findByIdCustomer() {
        CustomerDto customerDto = new CustomerDto();
        view.write("Enter id of searching entity");
        while (true) {
            try {
                customerDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        System.out.println(customerService.getByID(customerDto.getId()));
    }

    private void findByIdDeveloper() {
        DeveloperDto developerDto = new DeveloperDto();
        view.write("Enter id of searching entity");
        while (true) {
            try {
                developerDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        System.out.println(developerService.getByID(developerDto.getId()));
    }

    private void findByIdProject() {
        ProjectDto projectDto = new ProjectDto();
        view.write("Enter id of searching entity");
        while (true) {
            try {
                projectDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        System.out.println(projectService.getByID(projectDto.getId()));
    }

    private void findByIdSkill() {
        SkillDto skillDto = new SkillDto();
        view.write("Enter id of searching entity");
        while (true) {
            try {
                skillDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        System.out.println(skillService.getByID(skillDto.getId()));
    }
}
