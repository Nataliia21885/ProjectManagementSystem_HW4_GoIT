package jdbc.repository;

import jdbc.config.DatabaseManagerConnector;
import jdbc.model.dao.DeveloperDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository implements CRUDRepository<DeveloperDao> {

    private final DatabaseManagerConnector connector;
    private static final String INSERT = "INSERT INTO developers(name, age, sex, salary) VALUES(?,?,?,?)";
    private static final String ALL_DEVELOPERS = "SELECT id, name, age, sex, salary FROM developers";
    private static final String UPDATE = "UPDATE developers SET name = ?, age = ?, sex = ?, salary = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM developers WHERE id = ?";
    private static final String DEVELOPER_BY_ID = "SELECT id, name, age, sex, salary FROM developers WHERE id = ?";


    public DeveloperRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public DeveloperDao create(DeveloperDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setString(3, entity.getSex());
            statement.setInt(4, entity.getSalary());
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
    public List<DeveloperDao> findAll() {
        List<DeveloperDao> developerDaoList = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_DEVELOPERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DeveloperDao developerDao = new DeveloperDao();
                developerDao.setId(resultSet.getInt("id"));
                developerDao.setName(resultSet.getString("name"));
                developerDao.setAge(resultSet.getInt("age"));
                developerDao.setSex(resultSet.getString("sex"));
                developerDao.setSalary(resultSet.getInt("salary"));
                developerDaoList.add(developerDao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return developerDaoList;
    }

    @Override
    public DeveloperDao getByID(Integer id) {
        DeveloperDao developerDao = new DeveloperDao();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DEVELOPER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developerDao.setId(resultSet.getInt(1));
                developerDao.setName(resultSet.getString(2));
                developerDao.setAge(resultSet.getInt(3));
                developerDao.setSex(resultSet.getString(4));
                developerDao.setSalary(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return developerDao;
    }

    @Override
    public DeveloperDao update(DeveloperDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            preparedStatement.setString(3, entity.getSex());
            preparedStatement.setInt(4, entity.getSalary());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return entity;
    }

    @Override
    public void delete(DeveloperDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, entity.getId());
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("Developer don't delete");
            } else {
                System.out.printf("Developer successfully deleted");
            }
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
