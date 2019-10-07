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

import static java.lang.Integer.parseInt;

@WebServlet("/customers")
public class Customers extends HttpServlet {
    private CustomerDAO customerDAO = CustomerDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Customer> customers = customerDAO.findAll();
            req.setAttribute("customers", customers);
            getServletContext().getRequestDispatcher("/WEB-INF/customers.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        int id = parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String surname = req.getParameter("surname");
        Date birthYear = Date.valueOf(req.getParameter("birthYear"));

        try {
            Customer customer = new Customer(id, firstName, surname, birthYear);
            customerDAO.update(customer, id);
            List<Customer> customers = customerDAO.findAll();
            req.setAttribute("customers", customers);
            getServletContext().getRequestDispatcher("/WEB-INF/customers.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
