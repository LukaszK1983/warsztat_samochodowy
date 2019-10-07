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

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@WebServlet("/employees")
public class Employees extends HttpServlet {
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Employee> employees = employeeDAO.findAll();
            req.setAttribute("employees", employees);
            getServletContext().getRequestDispatcher("/WEB-INF/employees.jsp").forward(req, resp);
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
        String firstname = req.getParameter("firstname");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int phone = parseInt(req.getParameter("phone"));
        String note = req.getParameter("note");
        double manhour = parseDouble(req.getParameter("manhour"));
        try {
            Employee employee = new Employee(id, firstname, surname, email, phone, note, manhour);
            employeeDAO.update(employee, id);
            List<Employee> employees = employeeDAO.findAll();
            req.setAttribute("employees", employees);
            getServletContext().getRequestDispatcher("/WEB-INF/employees.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
