package pl.coderslab.warsztat_samochodowy.dao;

import pl.coderslab.warsztat_samochodowy.db.DbUtil;
import pl.coderslab.warsztat_samochodowy.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String CREATE_EMPLOYEE_QUERY = "INSERT INTO employee(firstName, surname, email, phone, note, man_hour) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_EMPLOYEE_QUERY = "SELECT firstName, surname, email, phone, note, man_hour FROM employee where id = ?";
    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE employee SET firstname = ?, surname = ?, email = ?, phone = ?, note = ?, man_hour = ? where id = ?";
    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM employee WHERE id = ?";
    private static final String FIND_ALL_EMPLOYEES_QUERY = "SELECT id, firstName, surname, email, phone, note, man_hour FROM employee";

    public static EmployeeDAO getInstance() {
        return new EmployeeDAO();
    }

    public Employee create(Employee employee) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++idx, employee.getFirstName());
            statement.setString(++idx, employee.getSurname());
            statement.setString(++idx, employee.getEmail());
            statement.setInt(++idx, employee.getPhone());
            statement.setString(++idx, employee.getNote());
            statement.setDouble(++idx, employee.getManHour());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
            }
            return employee;
        }
    }

    public Employee read(int employeeID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, employeeID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(employeeID);
                employee.setFirstName(rs.getString("firstName"));
                employee.setSurname(rs.getString("surname"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getInt("phone"));
                employee.setNote(rs.getString("note"));
                employee.setManHour(rs.getDouble("man_hour"));
                return employee;
            } else {
                Employee employee = new Employee();
                return employee;
            }
        }
    }

    public List<Employee> findAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Employee> employees = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EMPLOYEES_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("firstName"));
                employee.setSurname(rs.getString("surname"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getInt("phone"));
                employee.setNote(rs.getString("note"));
                employee.setManHour(rs.getDouble("man_hour"));

                employees.add(employee);
            }
            return employees;
        }
    }

    public void update(Employee employee, int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_EMPLOYEE_QUERY);
            statement.setString(++idx, employee.getFirstName());
            statement.setString(++idx, employee.getSurname());
            statement.setString(++idx, employee.getEmail());
            statement.setInt(++idx, employee.getPhone());
            statement.setString(++idx, employee.getNote());
            statement.setDouble(++idx, employee.getManHour());
            statement.setInt(++idx, employee.getId());
            statement.executeUpdate();
        }
    }

    public boolean delete(int employeeId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_EMPLOYEE_QUERY);
            statement.setInt(++idx, employeeId);
            return statement.executeUpdate() == 1;
        }
    }
}
