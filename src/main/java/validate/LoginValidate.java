package validate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginValidate {


    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(LoginValidate.class);

    /**
     * @param name = input user name
     * @param pw   = input user password
     * @return
     */


    public static boolean validate(String name, String pw, Connection connection) throws Exception {

        LOG.info("Inside the user validation against the database method");
        Statement stmt = null;
        ResultSet rs = null;


        try {
            LOG.info("Calling the database connection object");


            if(connection != null) {
                LOG.debug("Connection not null");
                /** create a statement*/
                stmt = connection.createStatement();
            }else
                LOG.error("Connection NULL");




            /** execute a query and the result is returned as a ResultSet*/
            LOG.info("Beginning to query the database based on entered details");
            String query = "SELECT * FROM user where username=\"" + name + "\" and password=md5(\"" + pw + "\"); ";
            //String query = "SELECT * FROM user where username =\"" + name + "\" ; ";
            LOG.info("Query Successfully returned");

            if(query == null){
                LOG.error("Query returned from database is NUll");
            }

            /**create a result set after executing the query */
            if (stmt != null) {
                rs = stmt.executeQuery(query);
            }


            if (rs != null) {
                if (rs.next()) {
                    return true;
                }
            }


        } catch (Exception e) {
            throw e;

        } finally {   /** the ResultSet , statement and connection are explicitly closed*/
            try {
                if (rs != null)
                    rs.close();
                else{
                    LOG.error("created resultset null");
                }
            } catch (SQLException e) {
                LOG.fatal("SQLException related to resultset");
            }
            try {
                if (stmt != null)
                    stmt.close();
                else{
                  LOG.error("created statement null");
                }

            } catch (SQLException e) {
                LOG.fatal("SQLException related to statement ");
            }
            if(connection != null)
                connection.close();

        }
        return false;
    }
}