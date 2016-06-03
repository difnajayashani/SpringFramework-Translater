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
import java.sql.SQLException;

public class UpdateUserServlet extends HttpServlet {


    /**
     * create the logger object for logging
     */
    private static final Logger LOG = LogManager.getLogger(UpdateUserServlet.class);



    /**
     * @param request  servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException            *
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection connection=null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        LOG.info("Obtain attribute name set from user add form");

        String uu_name = request.getParameter("uu_name");

        String uf_name = request.getParameter("uf_name");
        String ul_name = request.getParameter("ul_name");
        String udate = request.getParameter("udate");
        String ucountry = request.getParameter("ucountry");
        String ucity = request.getParameter("ucity");
        String uemail = request.getParameter("uemail");
        String umobile = request.getParameter("umobile");
        String upw = request.getParameter("upw");

        String[] ugroup= request.getParameterValues("ugroup");

        LOG.info("Date read from form! : {}", udate);

        /** connect to the database pool**/
        DatabaseUtility dbPool = (DatabaseUtility) getServletContext().getAttribute("DBManager");


        try {
            String insertQuery = "UPDATE user SET  password = \"" + upw + "\",f_name =\"" + uf_name + "\" ," +
                    "l_name =\"" + ul_name + "\",birth_date =STR_TO_DATE(\"" + udate + "\",'%m/%d/%Y')," +
                    "country = \"" + ucountry + "\" ,city_id =(SELECT id FROM city WHERE name= \""
                    +ucity+ "\"),email= \"" + uemail + "\"," + "mobile = \"" + umobile + "\" " +
                    "WHERE username= \"" + uu_name + "\"";

            connection = dbPool.getConnection();
            if (connection != null) {
                LOG.info("Connection not null");


                /** create a statement*/
              stmt = connection.prepareStatement(insertQuery);
                LOG.trace(" update Statement created");

                // insert the data
                LOG.trace("Update Queary executed 1");
                int updateSuccess= stmt.executeUpdate();
                LOG.trace("Queary executed 2");

                if (updateSuccess ==1) {
                    LOG.info("Query to update the user_group table on update");

                    int rs2=0;
                    for(int i=0; i< ugroup.length; i++) {

                        String extraQuery = "UPDATE user_group SET  group_id=(SELECT id FROM functional_group " +
                                "WHERE name=\"" + ugroup[i] + "\") WHERE user_id=(SELECT id FROM user " +
                                "WHERE username= \"" + uu_name + "\") ";

                        stmt2 = connection.prepareStatement(extraQuery);
                        LOG.info("Query on table update executed !");
                        rs2= stmt2.executeUpdate();
                    }

                    if(rs2 == 1) {
                        LOG.info("Output the user update result");
                        out.println(1);
                    }

                }
                else
                    out.println(0);
                LOG.info("Updating not success ");

            }

        } catch (SQLException e) {
            LOG.error("exception in user update: {}",e);
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("exception in creating the connection");
                }

            }
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    LOG.error("exception in the resultset");
                }

            }
            if(stmt2!=null){
                try {

                    stmt2.close();
                } catch (SQLException e) {
                    LOG.error("exception in the resultset");
                }

            }

        }

    }
}