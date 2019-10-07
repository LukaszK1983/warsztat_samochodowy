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

@WebServlet("/addEmployee")
public class AddEmployee extends HttpServlet {
    private EmployeeDAO employeeDAO = EmployeeDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/addemployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int phone = parseInt(req.getParameter("phone"));
        String note = req.getParameter("note");
        double manhour = parseDouble(req.getParameter("manhour"));
        try {
            Employee employee = new Employee(firstname, surname, email, phone, note, manhour);
            employeeDAO.create(employee);
            List<Employee> employees = employeeDAO.findAll();
            req.setAttribute("employees", employees);
            getServletContext().getRequestDispatcher("/WEB-INF/employees.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Wystąpił błąd połączenia z bazą danych.");
        }
    }
}
