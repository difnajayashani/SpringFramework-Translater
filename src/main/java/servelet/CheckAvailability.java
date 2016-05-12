package servelet;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hsenid on 4/29/16.
 */
public class CheckAvailability extends HttpServlet {


    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);


    /**
     * @param request servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection connection=null;
        ResultSet rs=null;

        try {


            String uname = request.getParameter("uname");
            LOG.trace("obtained the parameter uname passed by ajax");

            /** connect to the database pool**/
            DatabaseUtility dbPool=(DatabaseUtility)getServletContext().getAttribute("DBManager");
           connection=dbPool.getConnection();
            LOG.trace("created the connection");

            Statement stmt = connection.createStatement();

            String query = "SELECT username FROM user where username =\"" + uname + "\" ; ";
            rs = stmt.executeQuery(query);
            LOG.trace("Query executed");

            if (rs != null) {
                if (rs.next()) {
                    out.println(1) ;
                }
            }

            //out.println(rs.next());

        } catch (Exception ex) {
            LOG.error("exception");
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("exception in creating the connection");
                }

            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOG.error("exception in the resultset");
                }

            }
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
