package jdbc.repository;

import jdbc.config.DatabaseManagerConnector;
import jdbc.model.dao.CompanyDao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository implements CRUDRepository<CompanyDao> {

    private final DatabaseManagerConnector connector;
    private static final String INSERT = "INSERT INTO companies(name, hrm) VALUES(?,?)";
    private static final String ALL_COMPANY = "SELECT id, name, hrm FROM companies;";
    private static final String UPDATE = "UPDATE companies SET name = ?, hrm = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM companies " +
            "SELECT * FROM companies " +
            "INNER JOIN projects ON companies.id = projects.company_id " +
            "INNER JOIN developers_projects ON projects.id = developers_projects.project_id " +
            "WHERE companies.id = ?;";
    private static final String COMPANY_BY_ID = "SELECT id, name, hrm FROM companies WHERE id = ?";


    public CompanyRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public CompanyDao create(CompanyDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getHrm());
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
    public List<CompanyDao> findAll() {
        List<CompanyDao> companyDaoList = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_COMPANY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CompanyDao companyDao = new CompanyDao();
                companyDao.setId(resultSet.getInt("id"));
                companyDao.setName(resultSet.getString("name"));
                companyDao.setHrm(resultSet.getString("hrm"));
                companyDaoList.add(companyDao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return companyDaoList;
    }

    @Override
    public CompanyDao getByID(Integer id) {
        CompanyDao companyDao = new CompanyDao();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COMPANY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                companyDao.setId(resultSet.getInt(1));
                companyDao.setName(resultSet.getString(2));
                companyDao.setHrm(resultSet.getString(3));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return companyDao;
    }

    @Override
    public CompanyDao update(CompanyDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getHrm());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return entity;
    }

    @Override
    public void delete(CompanyDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
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
