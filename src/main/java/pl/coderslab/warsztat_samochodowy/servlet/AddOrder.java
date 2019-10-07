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
import java.sql.Timestamp;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/addOrder")
public class AddOrder extends HttpServlet {
    private OrderDAO orderDAO = OrderDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/addorder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Timestamp receiptDate = Timestamp.valueOf(req.getParameter("receiptDate"));
        Timestamp plannedRepairDate = Timestamp.valueOf(req.getParameter("plannedRepairDate"));
        int employeeId = parseInt(req.getParameter("employeeId"));
        String problemDesc = req.getParameter("problemDesc");
        int vehicleId = parseInt(req.getParameter("vehicleId"));

        try {
            Order order = new Order(receiptDate, plannedRepairDate, employeeId, problemDesc, vehicleId);
            orderDAO.createShort(order);
            List<Order> orders = orderDAO.readAll();
            req.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
