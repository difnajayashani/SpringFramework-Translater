package validate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PermissionValidate {

    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(LoginValidate.class);


    public static ArrayList<String> permission(String username,Connection connection){


        /**create a new array list */
        ArrayList<String> permissionList = new ArrayList<String>();

        LOG.info("Inside the permission validation");
        Statement stmt = null;
        ResultSet rs = null;





        return permissionList;

    }

}
