package servelet;


import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import translater.YandexIntegrater;
import validate.LoginValidate;
import validate.PermissionValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * sevelet class to take the login form inputs and validate the user
 * */
public class MyServlet extends HttpServlet {

    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(MyServlet.class);

    private static final long serialVersionUID = 1L;

    /**
     * @param request servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     *
     * **/

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        /** username and password entered to the form are captured*/
        String n = request.getParameter("username");
        String p = request.getParameter("password");

        /**set the logged in user's name */
        request.setAttribute("name", n);
        LOG.info("Attribute name set");


        /** connect to the database pool**/
        DatabaseUtility dbPool=(DatabaseUtility)getServletContext().getAttribute("DBManager");
        LOG.trace("connection pool instantiated");


        /**
         * call the method to validate the permissions of the user
         * takes an arraylist containing the permission of the going to login user
         * **/

        /**create a new array list */
        ArrayList<String> permissionList = new ArrayList<String>();

        try {
            LOG.trace("call the permission validate from the servlet");
            permissionList= PermissionValidate.permission(n, dbPool.getConnection());
            LOG.trace("return the permission list to the servlet");
            LOG.info("Permission list is : {}",permissionList);


            LOG.info("set the permission list attribute in servlet");
            HttpSession session = request.getSession();
           session.setAttribute(" permissionList",  permissionList);


        } catch (SQLException e) {
            LOG.error("SQL Exception in obtaining permission list in servlet");
        }finally {
            try {
                dbPool.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        /** arraylist  is checked for each permission**/
        boolean logPermission=false;


        LOG.trace("check if Login permission exists in the permission arraylist");
        String searchLog="Login";
        for (String val :  permissionList) {
            if( val.contains(searchLog) ){
                logPermission=true;
                LOG.info("LOG permission stay");
            }
        }
        LOG.info("logPermission is: {}",logPermission);
        /**
         * call the method to validate the login
         * to get authentication using username and password
         * */
        boolean valid = false;
        try {

            LOG.info("Calling the method to validate the user");
            valid = LoginValidate.validate(n, p, dbPool.getConnection());


        } catch (Exception e) {
            LOG.error("Exception occurred in validating the user");
        }finally {
            try {
                dbPool.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * the logic to validate whether user exists and
         * the LOGIN permission exists for the user to log in
         * **/

         if (valid) {
            LOG.info("The user is a registered user");

             if(logPermission) {

                 try {
                     LOG.info("The  registered user has login permission");

                     /** to load the Yandex language list to the form dropdowns using a YandexIntegrater object */
                     LOG.warn(" Calling YandexIntegrater can cause Exception");
                     YandexIntegrater langObj = new YandexIntegrater();
                     ArrayList<String> load = new ArrayList<String>();
                     load = langObj.getLangs();
                     request.setAttribute("language_list", load);


                     //navigate to the translater page in case login is valid
                     RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
                     rd.forward(request, response);

                 } catch (Exception e) {
                     throw new ServletException(e);
                 } finally {
                     if (out != null)
                         out.close();
                 }

             } else {

                 LOG.warn("Registered user does not have login permission ");
                 String msg="Sorry user is blocked from Login Permission";

                 RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                 rd.include(request, response);

                 JOptionPane.showMessageDialog(null, msg);
             }

        } else {

            LOG.error("User doesn't exit... Cannot proceed");
            request.setAttribute("error", "Sorry username or password error");

            //out.print("<p style=\"color:blue\">Sorry username or password error</p>");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }


    }
}