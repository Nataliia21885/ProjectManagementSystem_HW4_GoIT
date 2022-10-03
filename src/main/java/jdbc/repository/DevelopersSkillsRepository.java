package jdbc.repository;

import jdbc.config.DatabaseManagerConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersSkillsRepository {
    private final DatabaseManagerConnector connector;
    private static final String INSERT = "INSERT INTO developers_skills(developer_id, skill_id) VALUES(?,?)";
    private static final String DEVELOPERS_BY_LANGUAGE = "SELECT developers.\"name\", skills.\"language\" " +
            "FROM developers_skills INNER JOIN skills ON skills.id = developers_skills.skill_id " +
            "INNER JOIN developers ON developers.id = developers_skills.developer_id " +
            "WHERE skills.\"language\" IN (?) GROUP BY developers.\"name\", skills.\"language\";";
    private static final String DEVELOPERS_BY_LEVEL = "SELECT developers.\"name\", skills.\"level\" " +
            "FROM developers_skills INNER JOIN skills ON skills.id = developers_skills.skill_id " +
            "INNER JOIN developers ON developers.id = developers_skills.developer_id " +
            "WHERE skills.\"level\" IN (?) GROUP BY developers.\"name\", skills.\"level\";";

    public DevelopersSkillsRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    public void create(Integer developerId, Integer skillId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, developerId);
            statement.setInt(2, skillId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void developersByLanguage(String name) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEVELOPERS_BY_LANGUAGE)) {
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

    public void developersByLevel(String name) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DEVELOPERS_BY_LEVEL)) {
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
}
