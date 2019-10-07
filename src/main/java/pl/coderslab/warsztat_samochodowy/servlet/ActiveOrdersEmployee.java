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

@WebServlet("/activeOrdersEmployees")
public class ActiveOrdersEmployee extends HttpServlet {
    private OrderDAO orderDAO = OrderDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Order> orders = orderDAO.readAllActive();
            req.setAttribute("orders", orders);
            if (orders.size() > 0) {
                getServletContext().getRequestDispatcher("/WEB-INF/activeordersemployee.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/noactiveordersemployee.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
