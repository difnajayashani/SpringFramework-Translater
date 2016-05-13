package servelet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import translater.YandexIntegrater;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * servelet which recieves the data entered to the rtanslater form
 */
public class MyServletTranslate extends HttpServlet {

    /** create the logger for the class**/
    private static final Logger LOG = LogManager.getLogger(YandexIntegrater.class);

    /**create a object from the translater class **/
    YandexIntegrater getReply = new YandexIntegrater();


    /**
     * @param request servlet instance we create to transport data to the servlet
     * @param response servlet instance we use to obtain data from the servlet
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     *
     * **/

     @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

            // get the parameters entered in translater.jsp form and send it to the language translate function
            String ol = request.getParameter("original-lang");
            String tl = request.getParameter("translate-lang");
            String textInput = request.getParameter("original-text");
            request.setAttribute("original", textInput);

         LOG.info("The text input: {}", textInput);


         String textOutput=null;

            //call the language translate function and catch the translated text
            try {

                LOG.trace("call the translate method");
                textOutput =getReply.translateText(ol, tl, textInput);
                LOG.info("translated successfully returned", textOutput);

            } catch (Exception e) {
                LOG.fatal("Exception occurred in translateText method");
            }


            ArrayList<String> list = new ArrayList<String>();
            try {
                LOG.trace("call the language get method");
                list = getReply.getLangs();
            } catch (Exception e) {
                LOG.error("Exception occurred in obtaining language list method");
            }

         /** set the attribute values**/
            request.setAttribute("language_list", list);
            request.setAttribute("selected_ol", ol);
            request.setAttribute("selected_tl", tl);
            request.setAttribute("original", textInput);
            request.setAttribute("translated", textOutput);

         /** reloadd the same page **/
            request.getRequestDispatcher("homepage.jsp").forward(request, response);;






    }
}


