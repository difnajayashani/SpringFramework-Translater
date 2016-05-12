
package c3p0.sample;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import property.PropertyReader;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *this class create the database connection using Servelet Context Listener
 * **/
public class DatabaseUtility {

    /** define the logger for this class**/
    private static final Logger LOGGER = LogManager.getLogger(DatabaseUtility.class);

    ComboPooledDataSource cpds;

    /** define the connection variable**/
    public static Connection conn;

    public DatabaseUtility(){

        LOGGER.info("load the properties like database url,password and username");
        PropertyReader properties=new PropertyReader();


        try {
            cpds=new ComboPooledDataSource();
            cpds.setDriverClass(properties.getproperty("database.driver","system.properties"));
            cpds.setJdbcUrl(properties.getproperty("database.url", "system.properties"));
            cpds.setUser(properties.getproperty("database.user", "system.properties"));
            cpds.setPassword(properties.getproperty("database.pw", "system.properties"));

            LOGGER.info("Database Connection created");

            //Setting pooling configurations
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);

        } catch (PropertyVetoException e) {
           LOGGER.error("Exception related to making database connection pool");
        }


    }

    /** to return the created connection**/
    public Connection getConnection() throws SQLException {

        LOGGER.info("return the database connection ");
        return cpds.getConnection();
    }




    /*public static void main(String[] args) throws SQLException {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            ComboPooledDataSource dataSource = DatabaseUtility.getDataSource();
            connection = dataSource.getConnection();
            String name="difna";
            String pw="difna";
            String query = "SELECT * FROM user_data where user_name =\"" + name + "\" ; ";
//            pstmt = connection.prepareStatement("SELECT * FROM user_data");
            pstmt = connection.prepareStatement(query);


            System.out.println("The Connection Object is of Class: " + connection.getClass());

            resultSet = pstmt.executeQuery();

            while (resultSet.next()){


                System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));


            }

        } catch (Exception e) {
            assert connection != null;
            connection.rollback();
            e.printStackTrace();
        }finally{
            if (resultSet != null)
                resultSet.close();
            if (pstmt != null)
                pstmt.close();
            if(connection != null)
                connection.close();

        }

    }*/
}

