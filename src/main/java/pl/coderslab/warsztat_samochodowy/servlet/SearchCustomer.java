package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.CustomerDAO;
import pl.coderslab.warsztat_samochodowy.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchCustomer")
public class SearchCustomer extends HttpServlet {
    private CustomerDAO customerDAO = CustomerDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/searchcustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String surname = req.getParameter("surname");

        try {
            List<Customer> customers = customerDAO.findAllBySurname(surname);
            req.setAttribute("customers", customers);
            if (customers.size() == 0) {
                getServletContext().getRequestDispatcher("/WEB-INF/nosearchedcustomer.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/searchedcustomer.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
