package jdbc.repository;

import jdbc.config.DatabaseManagerConnector;
import jdbc.model.dao.CustomerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements CRUDRepository<CustomerDao> {

    private final DatabaseManagerConnector connector;
    private static final String INSERT = "INSERT INTO customers(name, contact) VALUES(?,?)";
    private static final String ALL_CUSTOMERS = "SELECT id, name, contact FROM customers;";
    private static final String UPDATE = "UPDATE customers SET name = ?, contact = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM customers WHERE name = ?;";
    private static final String CUSTOMER_BY_ID = "SELECT id, name, contact FROM customers WHERE id = ?;";


    public CustomerRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }


    @Override
    public CustomerDao create(CustomerDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getContact());
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
    public List<CustomerDao> findAll() {
        List<CustomerDao> customerDaoList = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_CUSTOMERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CustomerDao customerDao = new CustomerDao();
                customerDao.setId(resultSet.getInt("id"));
                customerDao.setName(resultSet.getString("name"));
                customerDao.setContact(resultSet.getString("contact"));
                customerDaoList.add(customerDao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customerDaoList;
    }

    @Override
    public CustomerDao getByID(Integer id) {
        CustomerDao customerDao = new CustomerDao();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerDao.setId(resultSet.getInt(1));
                customerDao.setName(resultSet.getString(2));
                customerDao.setContact(resultSet.getString(3));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customerDao;
    }

    @Override
    public CustomerDao update(CustomerDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getContact());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return entity;
    }

    @Override
    public void delete(CustomerDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, entity.getName());
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
