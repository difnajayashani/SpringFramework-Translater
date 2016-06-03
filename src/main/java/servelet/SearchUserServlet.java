package servelet;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

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
 * This servlet is for user search functionality
 */
public class SearchUserServlet extends HttpServlet {

    /**
     * create the logger object for logging
     */
    private static final Logger LOG = LogManager.getLogger(UpdateUserServlet.class);


    /**
     * @param request  servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException      *
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        Statement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        String userSearched = request.getParameter("snamef");
        LOG.trace("searched user name is set", userSearched);

        /**create a JSON array */
        JSONArray userList = new JSONArray();
        LOG.info("JSON object initialized");


        try {

            /** connect to the database pool**/
            DatabaseUtility dbPool = (DatabaseUtility) getServletContext().getAttribute("DBManager");
            connection = dbPool.getConnection();
            LOG.info("Database connection obtained for user Search");

            st = connection.createStatement();
            String sql ="SELECT * , DATE_FORMAT(birth_date,'%m/%d/%Y') AS niceDate FROM user  WHERE username" +
                    " LIKE \"" +userSearched + "%\" ;";

            // String sql ="SELECT * , DATE_FORMAT(birth_date,'%m/%d/%Y') AS niceDate FROM user_data WHERE user_name REGEXP '^[\"" +userSearched + "\"].*$' ;";
            rs1 = st.executeQuery(sql);
            LOG.trace("Query to search user executed ");


            LOG.trace("Loop through the resultset ");
            int size = 1;
            while (rs1.next()) {

                /**create a JSON objecty */
                JSONObject a1 = new JSONObject();

                LOG.info("append the each database read objects to a json object ");

                a1.append("id", rs1.getString("id"));
                a1.append("user_name", rs1.getString("username"));
                a1.append("f_name", rs1.getString("f_name"));
                a1.append("l_name", rs1.getString("l_name"));
                a1.append("niceDate", rs1.getString("niceDate"));
                a1.append("country", rs1.getString("country"));
                a1.append("e_mail", rs1.getString("email"));
                a1.append("mobile", rs1.getString("mobile"));


                String cityQuery="SELECT name FROM city" +
                        " WHERE id= " +Integer.parseInt(rs1.getString("city_id")) +"; ";


                st = connection.createStatement();
                rs2=st.executeQuery(cityQuery);

                while(rs2.next()){
                    a1.append("city_id",rs2.getString("name"));
                }

                LOG.info("Size of loop is:", size);
                size++;
                //System.out.println("al :: " + a1);
                userList.put(a1);

            }


        } catch (SQLException e) {
            LOG.error("SQL Exception error in searching a user");
        } finally {
            try {
                if (rs1 != null) {
                    rs1.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                LOG.error("SQL Error in closing the connections, statements in user Search");
            }


        }
        LOG.info("Search user output given:", userList);
        out.print(userList);
    }
}
