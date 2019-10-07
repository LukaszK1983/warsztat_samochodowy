package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.OrderDAO;
import pl.coderslab.warsztat_samochodowy.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/ordersCustomer")
public class OrdersCustomer extends HttpServlet {
    private OrderDAO orderDAO = OrderDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        int customerId = parseInt(req.getParameter("id"));

        try {
            List<Order> orders = orderDAO.readCustomerOrders(customerId);
            req.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/WEB-INF/orderscustomer.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
