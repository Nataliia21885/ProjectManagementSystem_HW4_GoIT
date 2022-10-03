package jdbc.command;

import jdbc.model.dto.*;
import jdbc.service.*;
import jdbc.view.View;


import java.sql.Date;


public class CreateCommand implements Command {

    private final View view;
    public static final String CREATE_NEW_ENTITY = "create";
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final DeveloperService developerService;
    private final ProjectService projectService;
    private final SkillService skillService;

    public CreateCommand(View view, CompanyService companyService, CustomerService customerService,
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
        return input.equals(CREATE_NEW_ENTITY);
    }

    @Override
    public void execute() {
        boolean start = true;
        while (start) {
            view.write("Please, write the entity's name which you're going to create: " +
                    "company, customer, developer, project, skill");
            String temp = view.read();
            switch (temp) {
                case "company" -> {
                    createCompany();
                    start = false;
                }
                case "customer" -> {
                    createCustomer();
                    start = false;
                }
                case "developer" -> {
                    createDeveloper();
                    start = false;
                }
                case "project" -> {
                    createProject();
                    start = false;
                }
                case "skill" -> {
                    createSkill();
                    start = false;
                }
            }
            view.write(String.format("Please, write %s to see all command", HelpCommand.HELP_COMMAND));
        }
    }

    private void createCompany() {
        CompanyDto companyDto = new CompanyDto();
        view.write("Enter company name");
        companyDto.setName(view.read());
        view.write("Enter name of human resources manager");
        companyDto.setHrm(view.read());
        companyService.create(companyDto);
        view.write("New company successfully created");
    }

    private void createCustomer() {
        CustomerDto customerDto = new CustomerDto();
        view.write("Enter customer name");
        customerDto.setName(view.read());
        view.write("Enter customer contact(phone number)");
        customerDto.setContact(view.read());
        customerService.create(customerDto);
        view.write("New customer successfully created");
    }

    private void createDeveloper() {
        DeveloperDto developerDto = new DeveloperDto();
        view.write("Enter developer name");
        developerDto.setName(view.read());
        view.write("Enter developer age");
        while (true) {
            try {
                developerDto.setAge(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter developer sex: male of female");
        developerDto.setSex(view.read());
        view.write("Enter developer salary");
        while (true) {
            try {
                developerDto.setSalary(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        developerService.create(developerDto);
        view.write("New developer successfully created");
    }

    private void createProject() {
        ProjectDto projectDto = new ProjectDto();
        view.write("Enter project name");
        projectDto.setProjectName(view.read());
        view.write("Enter country");
        projectDto.setCountry(view.read());
        view.write("Enter company id");
        while (true) {
            try {
                projectDto.setCompanyId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter customer id");
        while (true) {
            try {
                projectDto.setCustomerId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter cost of project");
        while (true) {
            try {
                projectDto.setCost(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter date of project's creation");
        while (true) {
            try {
                projectDto.setDateOfCreation(Date.valueOf(view.read()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please, use format of date: YYYY-MM-DD!");
            }
        }
        projectService.create(projectDto);
        view.write("New project successfully created");
    }

    private void createSkill() {
        SkillDto skillDto = new SkillDto();
        view.write("Enter language");
        skillDto.setLanguage(view.read());
        view.write("Enter level");
        skillDto.setLevel(view.read());
        skillService.create(skillDto);
        view.write("New skill successfully created");
    }
}

