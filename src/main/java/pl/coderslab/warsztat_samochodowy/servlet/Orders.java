package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.EmployeeDAO;
import pl.coderslab.warsztat_samochodowy.dao.OrderDAO;
import pl.coderslab.warsztat_samochodowy.model.Employee;
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

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@WebServlet("/orders")
public class Orders extends HttpServlet {
    private OrderDAO orderDAO = OrderDAO.getInstance();
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Order> orders = orderDAO.readAll();
            req.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(req, resp);
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
        Timestamp receiptDate = Timestamp.valueOf(req.getParameter("receiptDate"));
        Timestamp plannedRepairDate = Timestamp.valueOf(req.getParameter("plannedRepairDate"));
        Timestamp startRepairDate = Timestamp.valueOf(req.getParameter("startRepairDate"));
        int employeeId = parseInt(req.getParameter("employeeId"));
        String problemDesc = req.getParameter("problemDesc");
        String repairDesc = req.getParameter("repairDesc");
        String status = req.getParameter("status");
        int vehicleId = parseInt(req.getParameter("vehicleId"));
        double repairCost = parseDouble(req.getParameter("repairCost"));
        double partsCost = parseDouble(req.getParameter("partsCost"));
        int numberManHours = parseInt(req.getParameter("numberManHours"));

        try {
            Employee employee = employeeDAO.read(employeeId);
            double manHour = employee.getManHour();

            Order order = new Order(id, receiptDate, plannedRepairDate, startRepairDate, employeeId, problemDesc, repairDesc, status, vehicleId, repairCost, partsCost, manHour, numberManHours);
            orderDAO.update(order, id);
            List<Order> orders = orderDAO.readAll();
            req.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
