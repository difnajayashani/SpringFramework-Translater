package validate;


import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;

public class PermissionValidate {

    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(LoginValidate.class);


    public static ArrayList<String> permission(String username,Connection connection){

        LOG.info("Inside the permission validation method for a user");
        Statement stmt = null;
        ResultSet rs = null;

        /**create a new array list */
        ArrayList<String> permissionList = new ArrayList<String>();



        String permissionQuery="SELECT permission FROM permission WHERE id IN (SELECT permission_id FROM " +
                "permission_group WHERE group_id=(SELECT group_id FROM user_group WHERE user_id=(SELECT id FROM user " +
                "WHERE username=\"" + username + "\")))";



        try {

            /** create a statement*/
            stmt = connection.createStatement();
            LOG.trace("statement created");

            rs= stmt.executeQuery(permissionQuery);
            LOG.trace("Query to obtain permission list executed");

            //below code returns meta data about the result set

   /*         ResultSetMetaData metadata = rs.getMetaData();

            System.out.println("Total columns: "+metadata.getColumnCount());
            System.out.println("Column Name of 1st column: "+metadata.getColumnName(1));
            System.out.println("Column Type Name of 1st column: "+metadata.getColumnTypeName(1));*/

            int size = 0;

            while (rs.next()) {

                permissionList.add(size, rs.getString("permission"));

                size++;

            }

        } catch (SQLException e) {
            LOG.error("Got an SQL exception in obtaing the permission set! : {}", e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }


            } catch (SQLException ex) {
                LOG.error("Got an exception in closing result set! : {}", ex.getMessage());
            }
        }
        LOG.trace("The permission list obtained");
        return permissionList;


    }


    public static void main(String args[]) throws SQLException {

        ArrayList<String> ex1 ;
        String searchString ="Login";
        String otherString ="Translate";
        DatabaseUtility d = new DatabaseUtility();
        Connection con = null;

        try {
            con = d.getConnection();

            ex1 = PermissionValidate.permission("difna",con);

            LOG.info("Permission list is: {}.", ex1);

            /** arraylist  is checked gor the Login permission**/
            boolean logPermission = false;
            boolean transPermission = false;

            LOG.trace("check if Login permission exists in the permission arraylist");

            for (String val :   ex1) {
                if( val.contains(searchString) ){
                    logPermission=true;
                    LOG.info("LOG permission stay");
                }else if( val.contains(otherString)){
                   transPermission=true;
                    LOG.info("LOG permission stay");

                }
            }
            LOG.info("logPermission is: {}",logPermission);

            LOG.info("transPermission is: {}",transPermission);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{

            if (con != null) {
                con.close();
            }
        }




    }

}


