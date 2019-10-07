package pl.coderslab.warsztat_samochodowy.dao;

import pl.coderslab.warsztat_samochodowy.db.DbUtil;
import pl.coderslab.warsztat_samochodowy.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static final String CREATE_CUSTOMER_QUERY = "INSERT INTO customer(firstname, surname, birth_year) VALUES (?, ?, ?)";
    private static final String READ_CUSTOMER_QUERY = "SELECT firstname, surname, birth_year FROM customer WHERE id = ?";
    private static final String READ_CUSTOMER_BY_SURNAME_QUERY = "SELECT id, firstname, surname, birth_year FROM customer WHERE surname = ?";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE customer SET firstname = ?, surname = ?, birth_year = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customer WHERE id = ?";
    private static final String FIND_ALL_CUSTOMERS_QUERY = "SELECT id, firstname, surname, birth_year FROM customer";

    public static CustomerDAO getInstance() {
        return new CustomerDAO();
    }

    public Customer create(Customer customer) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++idx, customer.getFirstName());
            statement.setString(++idx, customer.getSurname());
            statement.setDate(++idx, customer.getBirthYear());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
            }
            return customer;
        }
    }

    public Customer read(int customerID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, customerID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(customerID);
                customer.setFirstName(rs.getString("firstName"));
                customer.setSurname(rs.getString("surname"));
                customer.setBirthYear(rs.getDate("birth_year"));
                return customer;
            } else {
                Customer customer = new Customer();
                return customer;
            }
        }
    }

    public List<Customer> findAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Customer> customers = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_CUSTOMERS_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setSurname(rs.getString("surname"));
                customer.setBirthYear(rs.getDate("birth_year"));

                customers.add(customer);
            }
            return customers;
        }
    }

    public List<Customer> findAllBySurname(String surname) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Customer> customers = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_CUSTOMER_BY_SURNAME_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++idx, surname);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setSurname(surname);
                customer.setBirthYear(rs.getDate("birth_year"));

                customers.add(customer);
            }
            return customers;
        }
    }

    public void update(Customer customer, int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_CUSTOMER_QUERY);
            statement.setString(++idx, customer.getFirstName());
            statement.setString(++idx, customer.getSurname());
            statement.setDate(++idx, customer.getBirthYear());
            statement.setInt(++idx, customer.getId());
            statement.executeUpdate();
        }
    }

    public boolean delete(int customerId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_CUSTOMER_QUERY);
            statement.setInt(++idx, customerId);
            return statement.executeUpdate() == 1;
        }
    }
}
