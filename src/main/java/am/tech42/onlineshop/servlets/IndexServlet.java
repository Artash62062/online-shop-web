package am.tech42.onlineshop.servlets;

import am.tech42.onlineshop.model.PostHeader;
import am.tech42.onlineshop.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List postHeaders = PostService.getPostHeaders(1);
            req.setAttribute("posts", postHeaders);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }
}
