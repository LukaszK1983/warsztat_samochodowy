package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.OrderDAO;
import pl.coderslab.warsztat_samochodowy.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/profits")
public class Profits extends HttpServlet {
    private OrderDAO orderDAO = OrderDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/profits.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date dateFrom = Date.valueOf(req.getParameter("dateFrom"));
        Date dateTo = Date.valueOf(req.getParameter("dateTo"));

        try {
            List<Order> orders = orderDAO.profits(dateFrom, dateTo);
            req.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/WEB-INF/profits.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
