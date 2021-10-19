package Servlets;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/servlet")
public class PrviJdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // implementacija
        resp.getWriter().println("TEST TEST TEST :) ");
        resp.getWriter().println("TEST TEST TEST :) ");
    }
}