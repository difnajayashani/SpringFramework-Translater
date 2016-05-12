package usermanage;/*
package usermanage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

*/
/**
 * Created by hsenid on 4/26/16.
 *//*

public class UserValidate {

    */
/**create the logger object for logging *//*

    private static final Logger LOG = LogManager.getLogger(UserInteract.class);

    //validate the date validity
    public static String isValidDate(String date)

    {
        // set date format, this can be changed to whatever format

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // declare and initialize testDate variable, this is what will hold converted date

        Date testDate = null;

        // we will now try to parse the string into date form

        try

        {
            testDate = sdf.parse(date);

        }

        // if the format of the string provided doesn't match the format we

        // declared in SimpleDateFormat() we will get an exception

        catch (ParseException e)

        {
          */
/*  errorMessage = "the date you provided is in an invalid date" +

            " format.";*//*

         LOG.error("error in parsing the date");
        }


        return sdf.format(testDate);




    }



}
*/
