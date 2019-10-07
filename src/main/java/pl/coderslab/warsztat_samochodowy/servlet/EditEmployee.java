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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editEmployee")
public class EditEmployee extends HttpServlet {
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Employee employee = employeeDAO.read(id);
            List<Employee> employees = new ArrayList<>();
            employees.add(employee);
            req.setAttribute("employees", employees);
            getServletContext().getRequestDispatcher("/WEB-INF/editemployee.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych");
        }
    }
}
