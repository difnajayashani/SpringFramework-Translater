package listener;


import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;


/**
 * listener class to initiate the  database connection at the start of the web application
 * */
public class AppContextListener implements ServletContextListener {

    /** define the logger for this class**/
    private static final Logger LOGGER = LogManager.getLogger(AppContextListener.class);
    DatabaseUtility db = null;


    public void contextInitialized(ServletContextEvent servletContextEvent) {


        LOGGER.info("Servelet Context Initialized");
        ServletContext ctx = servletContextEvent.getServletContext();

            db=new DatabaseUtility();
            ctx.setAttribute("DBManager",db);
            LOGGER.trace("Database Connection pool created");



    }

    /** Destroy the connection pool at the end**/
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            db.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
