package jdbc;

import jdbc.command.*;
import jdbc.config.DatabaseManagerConnector;
import jdbc.config.PropertiesConfig;
import jdbc.repository.*;
import jdbc.service.*;
import jdbc.service.converter.*;
import jdbc.view.Console;
import jdbc.view.Controller;
import jdbc.view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        String dbPassword = System.getenv("dbpassword");
        String dbUserName = System.getenv("dbusername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, dbUserName, dbPassword);
        Connection connection = dbManager.getConnection();

        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        CompanyRepository companyRepository = new CompanyRepository(dbManager);
        CustomerRepository customerRepository = new CustomerRepository(dbManager);
        DeveloperRepository developerRepository = new DeveloperRepository(dbManager);
        DevelopersProjectsRepository developersProjectsRepository = new DevelopersProjectsRepository(dbManager);
        DevelopersSkillsRepository developersSkillsRepository = new DevelopersSkillsRepository(dbManager);
        ProjectRepository projectRepository = new ProjectRepository(dbManager);
        SkillRepository skillRepository = new SkillRepository(dbManager);

        CompanyConverter companyConverter = new CompanyConverter();
        CustomerConverter customerConverter = new CustomerConverter();
        DeveloperConverter developerConverter = new DeveloperConverter();
        ProjectConverter projectConverter = new ProjectConverter();
        SkillConverter skillConverter = new SkillConverter();

        CompanyService companyService = new CompanyService(companyRepository, companyConverter);
        CustomerService customerService = new CustomerService(customerRepository, customerConverter);
        DeveloperService developerService = new DeveloperService(developerRepository, developerConverter);
        ProjectService projectService = new ProjectService(projectRepository, projectConverter);
        SkillService skillService = new SkillService(skillRepository, skillConverter);

        List<Command> commandList = new ArrayList<>();
        commandList.add(new CreateCommand(view, companyService, customerService, developerService, projectService, skillService));
        commandList.add(new UpdateCommand(view, companyService, customerService, developerService, projectService, skillService));
        commandList.add(new DeleteCommand(view, companyService, customerService, developerService, projectService, skillService));
        commandList.add(new FindAllCommand(view, companyService, customerService, developerService, projectService, skillService));
        commandList.add(new FindByIdCommand(view, companyService, customerService, developerService, projectService, skillService));
        commandList.add(new SalaryByProjectCommand(view, developersProjectsRepository));
        commandList.add(new DevelopersOnProjectCommand(view, developersProjectsRepository));
        commandList.add(new ListOfProjectsByDateCommand(view, developersProjectsRepository));
        commandList.add(new SelectByLanguageCommand(view, developersSkillsRepository));
        commandList.add(new SelectByLevelCommand(view, developersSkillsRepository));
        commandList.add(new ExitCommand(view));
        commandList.add(new HelpCommand(view));

        Controller controller = new Controller(view, commandList);
        controller.run();
    }
}
