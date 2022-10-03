package jdbc.repository;

import jdbc.config.DatabaseManagerConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersProjectsRepository {
    private final DatabaseManagerConnector connector;
    private static final String INSERT = "INSERT INTO developers_projects(developer_id, project_id) VALUES(?,?)";
    private static final String SALARY_BY_PROJECT = "SELECT projects.id, projects.project_name, SUM(salary) " +
            "FROM developers JOIN developers_projects ON developers.id = developers_projects.developer_id " +
            "JOIN projects ON projects.id = developers_projects.project_id WHERE projects.project_name = ? " +
            "GROUP BY projects.id, projects.project_name";
    private static final String DEVELOPERS_ON_PROJECT = "SELECT developers.\"name\", projects.project_name " +
            "FROM developers INNER JOIN developers_projects ON developers.id = developers_projects.developer_id " +
            "INNER JOIN projects ON projects.id = developers_projects.project_id " +
            "WHERE projects.project_name = ? GROUP BY developers.\"name\", projects.project_name " +
            "ORDER BY projects.project_name;";
    private static final String LIST_OF_PROJECTS = "SELECT projects.date_of_creation, projects.project_name, " +
            "COUNT(*) FROM developers_projects INNER JOIN developers " +
            "ON developers.id = developers_projects.developer_id INNER JOIN projects " +
            "ON projects.id = developers_projects.project_id GROUP BY projects.date_of_creation, projects.project_name;";

    public DevelopersProjectsRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    public void create(Integer developerId, Integer projectId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, developerId);
            statement.setInt(2, projectId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salaryByProject(String name) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SALARY_BY_PROJECT)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println();
                System.out.print(resultSet.getInt(1) + " " + resultSet.getString(2) + " "
                        + resultSet.getInt(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void quantityOfDevelopersInProject(String name) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEVELOPERS_ON_PROJECT)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println();
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listOfProjects() {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_OF_PROJECTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println();
                System.out.println(resultSet.getDate(1) + " " + resultSet.getString(2) + " " +
                        resultSet.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
