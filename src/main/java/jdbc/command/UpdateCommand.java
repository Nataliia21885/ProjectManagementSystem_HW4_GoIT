package jdbc.command;

import jdbc.model.dto.*;
import jdbc.service.*;
import jdbc.view.View;

import java.sql.Date;

public class UpdateCommand implements Command {
    private final View view;
    public static final String UPDATE_ENTITY = "update";
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final DeveloperService developerService;
    private final ProjectService projectService;
    private final SkillService skillService;

    public UpdateCommand(View view, CompanyService companyService, CustomerService customerService,
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
        return input.equals(UPDATE_ENTITY);
    }

    @Override
    public void execute() {
        boolean start = true;
        while (start) {
            view.write("Please, write the entity's name which you're going to update: " +
                    "company, customer, developer, project, skill");
            String temp = view.read();
            switch (temp) {
                case "company" -> {
                    updateCompany();
                    start = false;
                }
                case "customer" -> {
                    updateCustomer();
                    start = false;
                }
                case "developer" -> {
                    updateDeveloper();
                    start = false;
                }
                case "project" -> {
                    updateProject();
                    start = false;
                }
                case "skill" -> {
                    updateSkill();
                    start = false;
                }
            }
            view.write(String.format("Please, write %s to see all command", HelpCommand.HELP_COMMAND));
        }
    }

    private void updateCompany() {
        CompanyDto companyDto = new CompanyDto();
        view.write("Enter id of updating entity");
        while (true) {
            try {
                companyDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new company name");
        companyDto.setName(view.read());
        view.write("Enter new company hrm");
        companyDto.setHrm(view.read());
        companyService.update(companyDto);
        view.write("Company successfully updated");
    }

    private void updateCustomer() {
        CustomerDto customerDto = new CustomerDto();
        view.write("Enter id of updating entity");
        while (true) {
            try {
                customerDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new customer name");
        customerDto.setName(view.read());
        view.write("Enter new customer contact");
        customerDto.setContact(view.read());
        customerService.update(customerDto);
        view.write("Customer successfully updated");
    }

    private void updateDeveloper() {
        DeveloperDto developerDto = new DeveloperDto();
        view.write("Enter id of updating entity");
        while (true) {
            try {
                developerDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new developer name");
        developerDto.setName(view.read());
        view.write("Enter new developer age");
        while (true) {
            try {
                developerDto.setAge(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new developer sex");
        developerDto.setSex(view.read());
        view.write("Enter new developer salary");
        while (true) {
            try {
                developerDto.setSalary(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        developerService.update(developerDto);
        view.write("Developer successfully updated");
    }

    private void updateProject() {
        ProjectDto projectDto = new ProjectDto();
        view.write("Enter id of updating entity");
        while (true) {
            try {
                projectDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new project name");
        projectDto.setProjectName(view.read());
        view.write("Enter new project country");
        projectDto.setCountry(view.read());
        view.write("Enter new project cost");
        while (true) {
            try {
                projectDto.setCost(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new company id");
        while (true) {
            try {
                projectDto.setCompanyId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new customer id");
        while (true) {
            try {
                projectDto.setCustomerId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new date of creation project");
        while (true) {
            try {
                projectDto.setDateOfCreation(Date.valueOf(view.read()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please, use format of date: YYYY-MM-DD!");
            }
        }
        projectService.update(projectDto);
        view.write("Project successfully updated");
    }

    private void updateSkill() {
        SkillDto skillDto = new SkillDto();
        view.write("Enter id of updating entity");
        while (true) {
            try {
                skillDto.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        view.write("Enter new language");
        skillDto.setLanguage(view.read());
        view.write("Enter new level");
        skillDto.setLevel(view.read());
        skillService.update(skillDto);
        view.write("Skill successfully updated");
    }
}
