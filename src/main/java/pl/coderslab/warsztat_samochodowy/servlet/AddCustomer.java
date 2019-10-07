package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.CustomerDAO;
import pl.coderslab.warsztat_samochodowy.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addCustomer")
public class AddCustomer extends HttpServlet {
    private CustomerDAO customerDAO = CustomerDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/addcustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String surname = req.getParameter("surname");
        Date birthYear = Date.valueOf(req.getParameter("birthYear"));

        try {
            Customer customer = new Customer(firstName, surname, birthYear);
            customerDAO.create(customer);
            List<Customer> customers = customerDAO.findAll();
            req.setAttribute("customers", customers);
            getServletContext().getRequestDispatcher("/WEB-INF/customers.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
