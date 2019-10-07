package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.CustomerDAO;
import pl.coderslab.warsztat_samochodowy.dao.VehicleDAO;
import pl.coderslab.warsztat_samochodowy.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/addVehicleCustomer")
public class AddVehicleCustomer extends HttpServlet {
    private VehicleDAO vehicleDAO = VehicleDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = parseInt(req.getParameter("customerId"));
        getServletContext().getRequestDispatcher("/WEB-INF/addvehiclecustomer.jsp?customerId=" + customerId + "").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = parseInt(req.getParameter("customerId"));
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int made = parseInt(req.getParameter("made"));
        String registrationNr = req.getParameter("registrationNr");
        Date nextExam = Date.valueOf(req.getParameter("nextExam"));

        try {
            Vehicle vehicle = new Vehicle(customerId, brand, model, made, registrationNr, nextExam);
            vehicleDAO.createForCustomer(vehicle);

            List<Vehicle> vehicles = vehicleDAO.findAllByCustomer(customerId);
            req.setAttribute("vehicles", vehicles);
            getServletContext().getRequestDispatcher("/WEB-INF/carscustomer.jsp?customerId=" + customerId + "").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}