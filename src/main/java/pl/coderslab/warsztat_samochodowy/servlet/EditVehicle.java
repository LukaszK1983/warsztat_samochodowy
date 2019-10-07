package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.VehicleDAO;
import pl.coderslab.warsztat_samochodowy.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editVehicle")
public class EditVehicle extends HttpServlet {
    private VehicleDAO vehicleDAO = VehicleDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Vehicle vehicle = vehicleDAO.read(id);
            List<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(vehicle);
            req.setAttribute("vehicles", vehicles);
            getServletContext().getRequestDispatcher("/WEB-INF/editvehicle.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych");
        }
    }
}
