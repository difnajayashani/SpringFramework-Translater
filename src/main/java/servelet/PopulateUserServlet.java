package servelet;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import usermanage.UserPopulate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/** This servlet is to initially populate the user set on the bootstrap table*/
public class PopulateUserServlet extends HttpServlet {

    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);

    UserPopulate u1=new UserPopulate();


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
        LOG.info("JSON object initialized");
        JSONArray allUsers=new JSONArray();


        String userSearched = request.getParameter("sname");
        LOG.trace("searched user name is set", userSearched);


        try {

            /** connect to the database pool**/
            DatabaseUtility dbPool = (DatabaseUtility) getServletContext().getAttribute("DBManager");
            connection = dbPool.getConnection();
            LOG.info("Database connection obtained for user populate");

            LOG.trace("method to poplulate the user_list is called");
            allUsers= u1.populateUsers(userSearched,connection);
            out.print(allUsers);

            LOG.info("Set the obtained list of users as a JSON array");
            /** set the attribute values**/
            request.setAttribute("users_list",allUsers);



        } catch (SQLException e) {
            LOG.error("SQLException in searching user");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally{

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("exception in creating the user search connection");
                }
            }



        }

    }


}