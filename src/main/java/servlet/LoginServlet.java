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

public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pass =  req.getParameter("password");
        String email = req.getParameter("email");


        if ((pass == null || email == null)||(pass.isEmpty()||email.isEmpty())) {
            resp.getWriter().println("Please enter correct email/pass");
        } else {
            User user = new User(email, pass);

            if (userService.isExistsThisUser(user)){
                userService.authUser(user);
                resp.getWriter().println("Thank you for login");
                }
                else {
                    resp.getWriter().println("You already login");
                }
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher( "authPage.html");
        requestDispatcher.forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("Please, enter pass/email");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        if (email == null) {
            email = "please enter email";
        }

        if (pass == null) {
            pass = "please enter pass";
        }
        resp.getWriter().println("email");
        resp.getWriter().println("password");
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher( "authPage.html");
        requestDispatcher.forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
