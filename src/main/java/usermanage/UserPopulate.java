package usermanage;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/** This class populate the user records on the bootstrap table
 *  Output is a json array
 *  **/

public class UserPopulate {
    /**
     * create the logger object for logging
     */
    private static final Logger LOG = LogManager.getLogger(UserInteract.class);


    public JSONArray populateUsers(String user, Connection con) throws SQLException, PropertyVetoException {

        /**create a JSON array */
        JSONArray userList = new JSONArray();


        Statement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        LOG.trace("Inside the populate users method");

        try {


            st = con.createStatement();

            rs1 = st.executeQuery("SELECT * , DATE_FORMAT(birth_date,'%m/%d/%Y') AS niceDate FROM user  ORDER BY id ASC;");
            LOG.trace("Query to search user executed ");


            LOG.trace("Loop through the resultset ");
            int size = 1;
            while (rs1.next()) {

                /**create a JSON objecty */
                JSONObject a1= new JSONObject();

                //System.out.println(rs.getString("ID") + "," + rs.getString("user_name") + "," + rs.getString("f_name") + "," + rs.getString("l_name") + "," + rs.getString("niceDate") + "," + rs.getString("country") + "," + rs.getString("e_mail") + "," + rs.getString("mobile"));//or getString(1) for coloumn 1 etc

                LOG.info("Size of loop is:", size);

                a1.append("id", rs1.getString("id"));
                a1.append("user_name",rs1.getString("username"));
                a1.append("f_name",rs1.getString("f_name"));
                a1.append("l_name",rs1.getString("l_name"));
                a1.append("niceDate",rs1.getString("niceDate"));
                a1.append("country",rs1.getString("country"));
                a1.append("e_mail",rs1.getString("email"));
                a1.append("mobile",rs1.getString("mobile"));

                String cityQuery="SELECT name FROM city" +
                        " WHERE id= " +Integer.parseInt(rs1.getString("city_id")) +"; ";


                st = con.createStatement();
                rs2=st.executeQuery(cityQuery);

                while(rs2.next()){
                    a1.append("city_id",rs2.getString("name"));
                }

                size++;
                System.out.println("al :: " + a1);
                userList.put(a1);

            }


            System.out.println(size);

        } finally {
            try {
                if (rs1 != null) {
                    rs1.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                LOG.error("Got an exception in populating users! : {}", ex.getMessage());
            }
        }
        return userList;
    }


    /** main function is for testing purpose of the above methods in the class**/
    public static void main(String args[]) throws SQLException {

        JSONArray ex1;
        DatabaseUtility d = new DatabaseUtility();
        UserPopulate u = new UserPopulate();
        Connection con = null;

        try {
            con = d.getConnection();

            ex1 = u.populateUsers("Difna", con);
            LOG.info("Language list is: {}.", ex1);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }finally{

            if (con != null) {
                con.close();
            }
        }
    }
}
