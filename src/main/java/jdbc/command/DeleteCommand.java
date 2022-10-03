package jdbc.command;

import jdbc.model.dto.*;
import jdbc.service.*;
import jdbc.view.View;

public class DeleteCommand implements Command {

    private final View view;
    public static final String DELETE_ENTITY = "delete";
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final DeveloperService developerService;
    private final ProjectService projectService;
    private final SkillService skillService;

    public DeleteCommand(View view, CompanyService companyService, CustomerService customerService,
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
        return input.equals(DELETE_ENTITY);
    }

    @Override
    public void execute() {
        boolean start = true;
        while (start) {
            view.write("Please, write the entity's name which you're going to delete: " +
                    "company, customer, developer, project, skill");
            String temp = view.read();
            switch (temp) {
                case "company" -> {
                    deleteCompany();
                    start = false;
                }
                case "customer" -> {
                    deleteCustomer();
                    start = false;
                }
                case "developer" -> {
                    deleteDeveloper();
                    start = false;
                }
                case "project" -> {
                    deleteProject();
                    start = false;
                }
                case "skill" -> {
                    deleteSkill();
                    start = false;
                }
            }
            view.write(String.format("Please, write %s to see all command", HelpCommand.HELP_COMMAND));
        }
    }


    private void deleteCompany() {
        CompanyDto companyDto = new CompanyDto();
        view.write("Enter company ID");
        while (true) {
            try {
                companyDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        companyService.delete(companyDto);
        view.write("Company successfully deleted");
    }

    private void deleteCustomer() {
        CustomerDto customerDto = new CustomerDto();
        view.write("Enter customer ID");
        while (true) {
            try {
                customerDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        customerService.delete(customerDto);
        view.write("Customer successfully deleted");
    }

    private void deleteDeveloper() {
        DeveloperDto developerDto = new DeveloperDto();
        view.write("Enter developer ID");
        while (true) {
            try {
                developerDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        developerService.delete(developerDto);
        view.write("Developer successfully deleted");
    }

    private void deleteProject() {
        ProjectDto projectDto = new ProjectDto();
        view.write("Enter project ID");
        while (true) {
            try {
                projectDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        projectService.delete(projectDto);
        view.write("Project successfully deleted");
    }

    public void deleteSkill() {
        SkillDto skillDto = new SkillDto();
        view.write("Enter skill ID");
        while (true) {
            try {
                skillDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        skillService.delete(skillDto);
        view.write("Skill successfully deleted");
    }

}
