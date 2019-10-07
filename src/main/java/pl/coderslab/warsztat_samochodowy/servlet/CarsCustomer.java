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
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/carsCustomer")
public class CarsCustomer extends HttpServlet {
    private CustomerDAO customerDAO = CustomerDAO.getInstance();
    private VehicleDAO vehicleDAO = VehicleDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        int customerId = parseInt(req.getParameter("id"));

        try {
            List<Vehicle> vehicles = vehicleDAO.findAllByCustomer(customerId);
            req.setAttribute("vehicles", vehicles);
            getServletContext().getRequestDispatcher("/WEB-INF/carscustomer.jsp?customerId=" + customerId + "").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
