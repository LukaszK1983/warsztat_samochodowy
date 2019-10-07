package pl.coderslab.warsztat_samochodowy.dao;

import pl.coderslab.warsztat_samochodowy.db.DbUtil;
import pl.coderslab.warsztat_samochodowy.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private static final String CREATE_VEHICLE_QUERY = "INSERT INTO vehicle(brand, model, made, registration_nr, next_exam) VALUES (?, ?, ?, ?, ?)";
    private static final String CREATE_VEHICLE_FOR_CUSTOMER_QUERY = "INSERT INTO vehicle(id_customer, brand, model, made, registration_nr, next_exam) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_VEHICLE_QUERY = "SELECT id_customer, brand, model, made, registration_nr, next_exam FROM vehicle where id = ?";
    private static final String UPDATE_VEHICLE_QUERY = "UPDATE vehicle SET brand = ?, model = ?, made = ?, registration_nr = ?, next_exam = ? where id = ?";
    private static final String DELETE_VEHICLE_QUERY = "DELETE FROM vehicle WHERE id = ?";
    private static final String FIND_ALL_VEHICLES_QUERY = "SELECT id, brand, model, made, registration_nr, next_exam FROM vehicle";
    private static final String FIND_ALL_CARS_CUSTOMER_QUERY = "SELECT v.id, v.id_customer, v.brand, v.model, v.made, v.registration_nr, v.next_exam FROM vehicle v INNER JOIN customer c ON v.id_customer = c.id WHERE c.id = ?";

    public static VehicleDAO getInstance() {
        return new VehicleDAO();
    }

    public Vehicle create(Vehicle vehicle) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++idx, vehicle.getBrand());
            statement.setString(++idx, vehicle.getModel());
            statement.setInt(++idx, vehicle.getMade());
            statement.setString(++idx, vehicle.getRegistrationNr());
            statement.setDate(++idx, vehicle.getNextExam());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vehicle.setId(resultSet.getInt(1));
            }
            return vehicle;
        }
    }

    public Vehicle createForCustomer(Vehicle vehicle) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(CREATE_VEHICLE_FOR_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, vehicle.getCustomerId());
            statement.setString(++idx, vehicle.getBrand());
            statement.setString(++idx, vehicle.getModel());
            statement.setInt(++idx, vehicle.getMade());
            statement.setString(++idx, vehicle.getRegistrationNr());
            statement.setDate(++idx, vehicle.getNextExam());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vehicle.setId(resultSet.getInt(1));
            }
            return vehicle;
        }
    }

    public Vehicle read(int vehicleID) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(READ_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(++idx, vehicleID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(vehicleID);
                vehicle.setCustomerId(rs.getInt("id_customer"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMade(rs.getInt("made"));
                vehicle.setRegistrationNr(rs.getString("registration_nr"));
                vehicle.setNextExam(rs.getDate("next_exam"));
                return vehicle;
            } else {
                Vehicle vehicle = new Vehicle();
                return vehicle;
            }
        }
    }

    public List<Vehicle> findAll() throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Vehicle> vehicles = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_VEHICLES_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMade(rs.getInt("made"));
                vehicle.setRegistrationNr(rs.getString("registration_nr"));
                vehicle.setNextExam(rs.getDate("next_exam"));

                vehicles.add(vehicle);
            }
            return vehicles;
        }
    }

    public List<Vehicle> findAllByCustomer(int customerId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            List<Vehicle> vehicles = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_CARS_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setCustomerId(rs.getInt("id_customer"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMade(rs.getInt("made"));
                vehicle.setRegistrationNr(rs.getString("registration_nr"));
                vehicle.setNextExam(rs.getDate("next_exam"));

                vehicles.add(vehicle);
            }
            return vehicles;
        }
    }

    public void update(Vehicle vehicle, int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(UPDATE_VEHICLE_QUERY);
            statement.setString(++idx, vehicle.getBrand());
            statement.setString(++idx, vehicle.getModel());
            statement.setInt(++idx, vehicle.getMade());
            statement.setString(++idx, vehicle.getRegistrationNr());
            statement.setDate(++idx, vehicle.getNextExam());
            statement.setInt(++idx, vehicle.getId());
            statement.executeUpdate();
        }
    }

    public boolean delete(int vehicleId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            int idx = 0;
            PreparedStatement statement = conn.prepareStatement(DELETE_VEHICLE_QUERY);
            statement.setInt(++idx, vehicleId);
            return statement.executeUpdate() == 1;
        }
    }
}