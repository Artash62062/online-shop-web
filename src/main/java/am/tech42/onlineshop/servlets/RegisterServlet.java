package am.tech42.onlineshop.servlets;

import am.tech42.onlineshop.model.User;
import am.tech42.onlineshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        User user = UserService.register(phoneNumber,password,name);
        if (user!=null) {
            req.getSession().setAttribute("loggedInUser", user);
            resp.setStatus(302);
            resp.setHeader("Location","/");

        } else {
            resp.setStatus(302);
            resp.setHeader("Location","/register?error");
        }
    }
}
