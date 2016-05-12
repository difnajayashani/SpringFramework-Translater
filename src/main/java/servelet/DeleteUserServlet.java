package servelet;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import usermanage.UserInteract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by hsenid on 5/3/16.
 */
public class DeleteUserServlet  extends HttpServlet {


    /**create the logger object for logging*/
    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);


    /**
     * @param request servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String delUser= request.getParameter("val");

        LOG.info("The requsted deleteing user",delUser);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection connection=null;



        try {

            LOG.info("Database connection obtained for user deletion");
            /** connect to the database pool**/
            DatabaseUtility dbPool = (DatabaseUtility) getServletContext().getAttribute("DBManager");
            connection = dbPool.getConnection();

            int delCon;

            LOG.trace("method to delete the given user is called");
           delCon=UserInteract.deletetUser(connection,delUser);

            if(delCon==1){
                LOG.info("User deleted successfully");
                out.println(delCon);

            }


        } catch (SQLException e) {
            LOG.error("SQLException in deleting user");
        } catch (Exception e) {
            LOG.error("Exception in deleting user");
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }




    }

}
