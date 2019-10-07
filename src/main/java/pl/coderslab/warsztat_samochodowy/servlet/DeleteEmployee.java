package pl.coderslab.warsztat_samochodowy.servlet;

import pl.coderslab.warsztat_samochodowy.dao.EmployeeDAO;
import pl.coderslab.warsztat_samochodowy.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/deleteEmployee")
public class DeleteEmployee extends HttpServlet {
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            employeeDAO.delete(id);
            List<Employee> employees = employeeDAO.findAll();
            req.setAttribute("employees", employees);
            getServletContext().getRequestDispatcher("/WEB-INF/employees.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
