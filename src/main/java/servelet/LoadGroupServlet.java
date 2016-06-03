package servelet;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

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


/**
 * Created by hsenid on 5/30/16.
 */
public class LoadGroupServlet extends HttpServlet{


    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PagemoveServlet.class);

    /**
     * @param request servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


        LOGGER.trace("Invoking to LoadGroup servlet..");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String sql = "select name from functional_group";


        JSONArray jsonArray = new  JSONArray();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try{


            /** connect to the database pool**/
            DatabaseUtility dbPool = (DatabaseUtility) getServletContext().getAttribute("DBManager");
            con = dbPool.getConnection();

            st = con.prepareStatement(sql);
            rs = st.executeQuery();


            while (rs.next()) {
                /**create a JSON objecty */
                JSONObject jsonObj = new JSONObject();

                jsonObj.append("groupNm", rs.getString("name"));
                jsonArray.put(jsonObj);
            }
            LOGGER.info("The group list is: {}",jsonArray);
            out.println(jsonArray);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    LOGGER.error("Exception in closing the city loading connection");
                }

            if(rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.error("Exception in closing the city loading resultset");
                }
            if(st!= null)
                try {
                    st.close();
                } catch (SQLException e) {
                    LOGGER.error("Exception in closing the city loading statement");
                }

        }


    }



}
