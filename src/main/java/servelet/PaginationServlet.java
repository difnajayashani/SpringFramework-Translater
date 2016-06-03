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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** This is the servlet for pagination **/

public class PaginationServlet extends HttpServlet{

    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);

    /**
     * @param request servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     *
     * **/
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        LOG.trace("Inside the Pagination Servlet");

        //to get the servlet output
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        /** connect to the database pool**/
        DatabaseUtility dbPool=(DatabaseUtility)getServletContext().getAttribute("DBManager");
        LOG.trace("Obtained the database connection for paginationt");

        Connection con = null;
        ResultSet rs =null;
        PreparedStatement st = null;
        int pageCount= 0;


        String pgeCountSql= " SELECT COUNT(*) AS usersCount FROM user;";
        LOG.info("Query to get the page count");

        try {
            con = dbPool.getConnection();
            st = con.prepareStatement(pgeCountSql);
            rs = st.executeQuery();
            LOG.trace("Query to get the page count executed");

            if (rs.first()) {
                pageCount = Integer.parseInt(rs.getString("usersCount"));
            }

            LOG.info("The page count is: {} ", pageCount);
            out.println(pageCount);


        } catch (SQLException e) {
           LOG.error("Error in pagination SQL query");
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                   LOG.error("SQL Exception in closing the connection");
                }
            }
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                   LOG.error("SQL Exception in closing the statement");
                }


            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOG.error("SQL Exception in closing the resultset");
                }
            }
        }

    }



}
