
package servelet;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

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

/** this servlet is for bootstrap typehead functionality**/
public class TypeAheadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(TypeAheadServlet.class);

    /**
     * @param request servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        LOGGER.trace("Invoking to TypeaheadUSername servlet..");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sname = request.getParameter("sname");

        //String sql = "select * from user_data where user_name LIKE \'%" + sname + "%\';";
       //String sql ="SELECT * , DATE_FORMAT(birth_date,'%m/%d/%Y') AS niceDate FROM User WHERE user_name REGEXP '^[\"" + sname + "\"].*$' ;";
        String sql ="SELECT * , DATE_FORMAT(birth_date,'%m/%d/%Y') AS niceDate FROM user WHERE username" +
                " LIKE \"" +sname + "%\" ;";

        JSONArray jsonArray = new  JSONArray();
        Connection con = null;
        PreparedStatement st;
        ResultSet rs;

        try {


            /** connect to the database pool**/
            DatabaseUtility dbPool = (DatabaseUtility) getServletContext().getAttribute("DBManager");
            con = dbPool.getConnection();
            LOGGER.info("Database connection obtained for typeahead user Search");

            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                jsonArray.put(rs.getString("user_name"));
            }
            out.println(jsonArray);
            LOGGER.info("The output in TypeAhead Servlet", jsonArray);

        } catch (Exception e) {
            LOGGER.error("Error in username typeahed loading..");
        } finally {
            try {
                LOGGER.trace("Closing typeaheadUsrname connection..");
                assert con != null;
                con.close();
            } catch (SQLException e) {
                LOGGER.fatal("Error while closing typeaheadUsrname connection..");
                e.printStackTrace();
            }
        }
    }
}
