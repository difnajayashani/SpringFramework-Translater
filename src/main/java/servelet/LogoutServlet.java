package servelet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {


/**
 * @param request servlet instance we create to transport data to the servlet
 * @param response servlet instance we use to obtain data from the servlet
 * @throws javax.servlet.ServletException
 * @throws java.io.IOException
 */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
        //response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}