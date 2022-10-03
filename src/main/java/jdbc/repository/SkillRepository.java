package jdbc.repository;

import jdbc.config.DatabaseManagerConnector;
import jdbc.model.dao.SkillDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository implements CRUDRepository<SkillDao> {

    private final DatabaseManagerConnector connector;
    private static final String INSERT = "INSERT INTO skills(language, level) VALUES(?,?)";
    private static final String ALL_SKILLS = "SELECT id, language, level FROM skills";
    private static final String UPDATE = "UPDATE skills SET language = ?, level = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM skills WHERE id = ?";
    private static final String SKILL_BY_ID = "SELECT id, language, level FROM skills WHERE id = ?";


    public SkillRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public SkillDao create(SkillDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getLanguage());
            statement.setString(2, entity.getLevel());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return entity;
    }

    @Override
    public List<SkillDao> findAll() {
        List<SkillDao> skillDaoList = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_SKILLS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SkillDao skillDao = new SkillDao();
                skillDao.setId(resultSet.getInt("id"));
                skillDao.setLanguage(resultSet.getString("language"));
                skillDao.setLevel(resultSet.getString("level"));
                skillDaoList.add(skillDao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return skillDaoList;
    }

    @Override
    public SkillDao getByID(Integer id) {
        SkillDao skillDao = new SkillDao();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SKILL_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skillDao.setId(resultSet.getInt(1));
                skillDao.setLanguage(resultSet.getString(2));
                skillDao.setLevel(resultSet.getString(3));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return skillDao;
    }

    @Override
    public SkillDao update(SkillDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getLanguage());
            preparedStatement.setString(2, entity.getLevel());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return entity;
    }

    @Override
    public void delete(SkillDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
