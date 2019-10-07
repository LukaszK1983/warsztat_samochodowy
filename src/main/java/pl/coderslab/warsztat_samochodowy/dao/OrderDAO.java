package pl.coderslab.warsztat_samochodowy.dao;

import pl.coderslab.warsztat_samochodowy.db.DbUtil;
import pl.coderslab.warsztat_samochodowy.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String CREATE_ORDER_QUERY = "INSERT INTO orders(receipt_date, planned_repair_date, start_repair_date, id_employee, problem_desc, status, id_vehicle, repair_cost, parts_cost, man_hour_cost, number_man_hours) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CREATE_SHORT_ORDER_QUERY = "INSERT INTO orders(receipt_date, planned_repair_date, id_employee, problem_desc, id_vehicle) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_ORDER_QUERY = "SELECT receipt_date, planned_repair_date, start_repair_date, id_employee, problem_desc, repair_desc, status, id_vehicle, repair_cost, parts_cost, man_hour_cost, number_man_hours FROM orders WHERE id = ?";
    private static final String READ_ACTIVE_ORDERS_QUERY = "SELECT id, receipt_date, planned_repair_date, start_repair_date, id_employee, problem_desc, repair_desc, status, id_vehicle, repair_cost, parts_cost, man_hour_cost, number_man_hours FROM orders WHERE status = 'W naprawie'";
    private static final String INDEX_READ_ACTIVE_ORDERS_QUERY = "SELECT id, receipt_date, problem_desc, status FROM orders WHERE status = 'W naprawie'";
    private static final String READ_ALL_ORDERS_QUERY = "SELECT id, receipt_date, problem_desc, status FROM orders";
    private static final String READ_ALL_ORDERS_VEHICLE_QUERY = "SELECT id, receipt_date, planned_repair_date, start_repair_date, id_employee, problem_desc, repair_desc, status, repair_cost, parts_cost, man_hour_cost, number_man_hours FROM orders WHERE id_vehicle = ?";
    private static final String READ_ALL_ORDERS_EMPLOYEE_QUERY = "SELECT id, receipt_date, planned_repair_date, start_repair_date, problem_desc, repair_desc, status, id_vehicle, repair_cost, parts_cost, man_hour_cost, number_man_hours FROM orders WHERE id_employee = ?";
    private static final String READ_ALL_ORDERS_CUSTOMER_QUERY = "SELECT o.id, o.receipt_date, o.planned_repair_date, o.start_repair_date, o.id_employee, o.problem_desc, o.repair_desc, o.status, o.id_vehicle, o.repair_cost, o.parts_cost, o.man_hour_cost, o.number_man_hours FROM orders o INNER JOIN vehicle v ON o.id_vehicle = v.id WHERE v.id_customer = ?";
    private static final String UPDATE_ORDER_QUERY = "UPDATE orders SET receipt_date = ?, planned_repair_date = ?, start_repair_date = ?, id_employee = ?, problem_desc = ?, repair_desc = ?, status = ?, id_vehicle = ?, repair_cost = ?, parts_cost = ?, man_hour_cost = ?, number_man_hours = ? WHERE id = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE id = ?";
    private static final String WORK_HOURS_QUERY = "SELECT e.firstname, e.surname, SUM(number_man_hours) AS sum FROM orders o INNER JOIN employee e ON o.id_employee = e.id WHERE o.start_repair_date BETWEEN ? AND ? GROUP BY o.id_employee";
    private static final String PROFITS_QUERY = "SELECT SUM(repair_cost) AS repair_sum, SUM(parts_cost) AS parts_sum, SUM(man_hour_cost * number_man_hours) AS man_hour_sum FROM orders WHERE start_repair_date BETWEEN ? AND ?";

    public static OrderDAO getInstance() {
        return new OrderDAO();
    }

    public Order create(Order order) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(++idx, order.getReceiptDate());
            statement.setTimestamp(++idx, order.getPlannedRepairDate());
            statement.setTimestamp(++idx, order.getStartRepairDate());
            statement.setInt(++idx, order.getEmployeeId());
            statement.setString(++idx, order.getProblemDesc());
            statement.setString(++idx, order.getRepairDesc());
            statement.setString(++idx, order.getStatus());
            statement.setInt(++idx, order.getVehicleId());
            statement.setDouble(++idx, order.getRepairCost());
            statement.setDouble(++idx, order.getPartsCost());
            statement.setDouble(++idx, order.getManHourCost());
            statement.setInt(++idx, order.getNumberManHours());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            return order;
        }
    }

    public Order createShort(Order order) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_SHORT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(++idx, order.getReceiptDate());
            statement.setTimestamp(++idx, order.getPlannedRepairDate());
            statement.setInt(++idx, order.getEmployeeId());
            statement.setString(++idx, order.getProblemDesc());
            statement.setInt(++idx, order.getVehicleId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            return order;
        }
    }

    public Order read(int orderID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, orderID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setId(orderID);
                order.setReceiptDate(rs.getTimestamp("receipt_date"));
                order.setPlannedRepairDate(rs.getTimestamp("planned_repair_date"));
                order.setStartRepairDate(rs.getTimestamp("start_repair_date"));
                order.setEmployeeId(rs.getInt("id_employee"));
                order.setProblemDesc(rs.getString("problem_desc"));
                order.setRepairDesc(rs.getString("repair_desc"));
                order.setStatus(rs.getString("status"));
                order.setVehicleId(rs.getInt("id_vehicle"));
                order.setRepairCost(rs.getDouble("repair_cost"));
                order.setPartsCost(rs.getDouble("parts_cost"));
                order.setManHourCost(rs.getDouble("man_hour_cost"));
                order.setNumberManHours(rs.getInt("number_man_hours"));
                return order;
            } else {
                Order order = new Order();
                return order;
            }
        }
    }

    public List<Order> readVehicleOrders(int vehicleID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_ALL_ORDERS_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, vehicleID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setReceiptDate(rs.getTimestamp("receipt_date"));
                order.setPlannedRepairDate(rs.getTimestamp("planned_repair_date"));
                order.setStartRepairDate(rs.getTimestamp("start_repair_date"));
                order.setEmployeeId(rs.getInt("id_employee"));
                order.setProblemDesc(rs.getString("problem_desc"));
                order.setRepairDesc(rs.getString("repair_desc"));
                order.setStatus(rs.getString("status"));
                order.setVehicleId(vehicleID);
                order.setRepairCost(rs.getDouble("repair_cost"));
                order.setPartsCost(rs.getDouble("parts_cost"));
                order.setManHourCost(rs.getDouble("man_hour_cost"));
                order.setNumberManHours(rs.getInt("number_man_hours"));

                orders.add(order);
            }
            return orders;
        }
    }

    public List<Order> readEmployeeOrders(int employeeID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_ALL_ORDERS_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, employeeID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setReceiptDate(rs.getTimestamp("receipt_date"));
                order.setPlannedRepairDate(rs.getTimestamp("planned_repair_date"));
                order.setStartRepairDate(rs.getTimestamp("start_repair_date"));
                order.setVehicleId(employeeID);
                order.setProblemDesc(rs.getString("problem_desc"));
                order.setRepairDesc(rs.getString("repair_desc"));
                order.setStatus(rs.getString("status"));
                order.setVehicleId(rs.getInt("id_vehicle"));
                order.setRepairCost(rs.getDouble("repair_cost"));
                order.setPartsCost(rs.getDouble("parts_cost"));
                order.setManHourCost(rs.getDouble("man_hour_cost"));
                order.setNumberManHours(rs.getInt("number_man_hours"));

                orders.add(order);
            }
            return orders;
        }
    }

    public List<Order> readCustomerOrders(int customerID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_ALL_ORDERS_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, customerID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setReceiptDate(rs.getTimestamp("receipt_date"));
                order.setPlannedRepairDate(rs.getTimestamp("planned_repair_date"));
                order.setStartRepairDate(rs.getTimestamp("start_repair_date"));
                order.setEmployeeId(rs.getInt("id_employee"));
                order.setProblemDesc(rs.getString("problem_desc"));
                order.setRepairDesc(rs.getString("repair_desc"));
                order.setStatus(rs.getString("status"));
                order.setId(rs.getInt("id_vehicle"));
                order.setRepairCost(rs.getDouble("repair_cost"));
                order.setPartsCost(rs.getDouble("parts_cost"));
                order.setManHourCost(rs.getDouble("man_hour_cost"));
                order.setNumberManHours(rs.getInt("number_man_hours"));

                orders.add(order);
            }
            return orders;
        }
    }

    public List<Order> readAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ALL_ORDERS_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setReceiptDate(rs.getTimestamp("receipt_date"));
                order.setProblemDesc(rs.getString("problem_desc"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }
            return orders;
        }
    }

    public List<Order> readAllActive() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(READ_ACTIVE_ORDERS_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setReceiptDate(rs.getTimestamp("receipt_date"));
                order.setPlannedRepairDate(rs.getTimestamp("planned_repair_date"));
                order.setStartRepairDate(rs.getTimestamp("start_repair_date"));
                order.setEmployeeId(rs.getInt("id_employee"));
                order.setProblemDesc(rs.getString("problem_desc"));
                order.setRepairDesc(rs.getString("repair_desc"));
                order.setStatus(rs.getString("status"));
                order.setId(rs.getInt("id_vehicle"));
                order.setRepairCost(rs.getDouble("repair_cost"));
                order.setPartsCost(rs.getDouble("parts_cost"));
                order.setManHourCost(rs.getDouble("man_hour_cost"));
                order.setNumberManHours(rs.getInt("number_man_hours"));

                orders.add(order);
            }
            return orders;
        }
    }

    public List<Order> readAllActiveIndex() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(INDEX_READ_ACTIVE_ORDERS_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setReceiptDate(rs.getTimestamp("receipt_date"));
                order.setProblemDesc(rs.getString("problem_desc"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }
            return orders;
        }
    }

    public void update(Order order, int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_ORDER_QUERY);
            statement.setTimestamp(++idx, order.getReceiptDate());
            statement.setTimestamp(++idx, order.getPlannedRepairDate());
            statement.setTimestamp(++idx, order.getStartRepairDate());
            statement.setInt(++idx, order.getEmployeeId());
            statement.setString(++idx, order.getProblemDesc());
            statement.setString(++idx, order.getRepairDesc());
            statement.setString(++idx, order.getStatus());
            statement.setInt(++idx, order.getVehicleId());
            statement.setDouble(++idx, order.getRepairCost());
            statement.setDouble(++idx, order.getPartsCost());
            statement.setDouble(++idx, order.getManHourCost());
            statement.setInt(++idx, order.getNumberManHours());
            statement.setInt(++idx, order.getId());
            statement.executeUpdate();
        }
    }

    public boolean delete(int orderId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_ORDER_QUERY);
            statement.setInt(++idx, orderId);
            return statement.executeUpdate() == 1;
        }
    }

    public List<Order> workHours(Date dateFrom, Date dateTo) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(WORK_HOURS_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(++idx, dateFrom);
            statement.setDate(++idx, dateTo);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setFirstName(rs.getString("firstname"));
                order.setSurname(rs.getString("surname"));
                order.setNumberManHours(rs.getInt("sum"));

                orders.add(order);
            }
            return orders;
        }
    }

    public List<Order> profits(Date dateFrom, Date dateTo) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(PROFITS_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(++idx, dateFrom);
            statement.setDate(++idx, dateTo);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setRepairCost(rs.getDouble("repair_sum"));
                order.setPartsCost(rs.getDouble("parts_sum"));
                order.setManHourCost(rs.getDouble("man_hour_sum"));

                orders.add(order);
            }
            return orders;
        }
    }
}
