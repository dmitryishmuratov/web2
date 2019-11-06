package servlet;

import model.User;
import service.UserService;
import util.PageGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
    private final UserService userService;

    public RegistrationServlet() {
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pass =  req.getParameter("password");
        String email = req.getParameter("email");

        boolean emailExist = false;  //userService.isExistsEmailUser(email);
        resp.getWriter().println(emailExist);

        if ((pass == null || email == null)||(pass.isEmpty() ||email.isEmpty())) {
            resp.getWriter().println("Bad data, please enter new");
        } else if(emailExist) {
            resp.getWriter().println("This email is already exist, please enter new or login");
        }
        else {
            userService.addUser(new User(email, pass));
            resp.getWriter().println("Thank you for registration");
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("registerPage.html");
        requestDispatcher.forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       resp.getWriter().println("Please, enter pass/email");
        String email = req.getParameter("email");
        if (email == null) {
            email = "please enter email";
        }
        String pass = req.getParameter("password");
        if (pass == null) {
            pass = "please enter pass";
        }


        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("registerPage.html");
        requestDispatcher.forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
