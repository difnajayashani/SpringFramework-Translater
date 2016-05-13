package translater;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import property.PropertyReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class YandexIntegrater {

    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(YandexIntegrater.class);

    /** create a hashmap object and call the getProperties method from App Class*/
    PropertyReader properties=new PropertyReader();

    /** URL to send the request to the API to obtain the language list*/
     final String POSTURL = properties.getproperty("languagelist.url","system.properties");



    public static void main(String... args)  {

        YandexIntegrater y=new YandexIntegrater();

        //** for testing purpose of this class*//*
        String  ex2 = null;
        ArrayList<String> ex1;
        try {
            LOG.info("Calling text translate function");
            ex2 = y.translateText("english", "arabic", "Hello");
            LOG.debug("Successfully executed the text translate method");
        } catch (IOException e) {
            LOG.error("IOException occurred ", e);
        } catch (ParserConfigurationException e) {
            LOG.error("ParserConfigurationException occurred ", e);
        } catch (SAXException e) {
            LOG.error("SAXException occurred ", e);
        } catch (URISyntaxException e) {
            LOG.error("URISyntaxException occurred ", e);
        }

        LOG.info("translated text is: {}.", ex2);

        try {
            ex1=y.getLangs();
            LOG.info("Language list is: {}.", ex1);
        } catch (Exception e) {
            LOG.error("Exception occurred in getting language list", e);
        }
    }


    /** function to get the language list */
    public  ArrayList<String> getLangs() throws Exception {

        org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();

        /** send the request to the Yandex API */
        LOG.info("Sending API request to Yandex to get language list");
        HttpGet request = new HttpGet(POSTURL);

        /** get the response*/
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity1= response.getEntity();
        if(entity1 == null)  {
            LOG.error("Response of getting language list is NUll");
        }

        /** Get the response */
        InputStream input = response.getEntity().getContent();


        /**creating DOM object to parse the XML returned in response*/
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        Document doc = builder.parse(input);

        /** get the elements in the Item TAG*/
        NodeList nameNodesList = doc.getElementsByTagName("Item");

        /**create a new array list */
        ArrayList<String> listValues = new ArrayList<String>();

        /** get the value of the attribute "value" in the Item TAG and put it in to the above created arraylist*/
        for (int i = 0; i < nameNodesList.getLength(); i++) {

            listValues.add(nameNodesList.item(i).getAttributes().getNamedItem("value").getNodeValue());

        }
        LOG.info("Language list successfully returned");
        return listValues;

    }

    /** function to translate a input string to the given language
     * @o_lan => language of the original string to be translated
     * @t_lan => language for the string to be translated
     * @text_input => input string
     * */

    public  String translateText(String o_lan, String t_lan, String text_input) throws IOException, ParserConfigurationException, SAXException, URISyntaxException {


        String output;
        String url=properties.getproperty("translate.url","system.properties");

        /** URL sent to the API to get the string translated*/
       final String TRANSURL=url+o_lan+"-"+t_lan+"&text="+text_input;

        /**send the request to the server thorough YandexIntegrater*/
        org.apache.http.client.HttpClient httpClientTranslate = new DefaultHttpClient();

        LOG.info("Sending API request to Yandex to get the language translated");
        HttpGet request = new HttpGet(TRANSURL);

        HttpResponse response2 ;
        try {
            LOG.info("Inside the try of the text translate");
            response2 = httpClientTranslate.execute(request);
            HttpEntity entity2= response2.getEntity();
            LOG.warn("Null Response returns an Exception");
            if(entity2 == null){
                LOG.error("Response of translating a text is NUll");


            }
            else{
                LOG.info("Valid Response in text Translate response");
            }
        } catch (IOException e1) {
            throw e1;
        }


        /**Get the response*/
        InputStream input2 = response2.getEntity().getContent();

        /**creating DOM object*/
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder2 = null;
        try {
            builder2 = dbf2.newDocumentBuilder();
        } catch (ParserConfigurationException e2) {
            throw e2;
        }
        Document doc = null;
        try {
            doc = builder2.parse(input2);
        } catch (SAXException e3) {
            throw e3;
        }

        NodeList textTag = doc.getElementsByTagName("text");

        /** get the string value of the content in the text TAG*/
        output = String.valueOf(textTag.item(0).getTextContent());


        LOG.info("Return the translated text");
        return output;
    }

}