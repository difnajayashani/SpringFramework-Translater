package selenium;

import c3p0.sample.DatabaseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class will test the add user UI
 */
public class AdduserTest {

    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(AdduserTest.class);

    private  String baseUrl;
    private WebDriver driver;




    /** opens the browser application before any test runs**/
/*    @BeforeSuite
    public void openBrowser() {

        baseUrl="http://localhost:8080";
        // driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }*/

    @BeforeTest
    public void adminLogin() throws InterruptedException {

        String expected= "Login Page";
        String actual= driver.getTitle();
        org.testng.Assert.assertEquals(actual, expected, "You are in the Home Page");

        Thread.sleep(2000);
        driver.findElement(By.id("form-username")).sendKeys("difna");
        driver.findElement(By.id("form-password")).sendKeys("difna");
        driver.findElement(By.id("btnlogin")).click();

    }

    /** provide data for the test method**/
    @DataProvider
    public Object[][] UserCredentials(){
        /**
         * rows=> represents the no.of times test run
         * column=> represent the data parameters
         * **/
        return new Object[][]{
               {"Right First","Right Second","05/05/2016","Sri Lanka","Gampaha","right@gmail.com","+94779900071","right","right12345","right12345",new String[]{"Administrator","Translater"},"true" },
                {"","Right Second","05/05/2016","Sri Lanka","Gampaha","right@gmail.com","+94779900071","right","right12345","right12345",new String[]{"Administrator","Translater"},"true" },
                {"","Right Second","","Sri Lanka","Gampaha","right@gmail.com","+94779900071","right","right12345","right12345",new String[]{"Administrator","Translater"},"true" }
                //{"Right First","Right Second","05/05/2016","Sri Lanka","Gampaha","rightgmail.com","+94779900071","right","right12345","right12345",new String[]{"Administrator","Translater"},"true" }



        };
    }

    @Test(dataProvider ="UserCredentials")
    public  void addUser(String fname,String lname, String date,String country,String city,String email,String mobile,
                         String uname,String pw, String cpw, String[] group, String expected) throws InterruptedException {





        driver.findElement(By.id("form-first-name")).sendKeys(fname);
        Thread.sleep(2000);

        driver.findElement(By.id("form-last-name")).sendKeys(lname);
        Thread.sleep(2000);

        driver.findElement(By.id("date")).sendKeys(date);
        Thread.sleep(2000);

        //select country
        Select dropdown1= new Select(driver.findElement(By.id("country")));
        dropdown1.selectByVisibleText(country);
        Thread.sleep(2000);

        //choose corresponding city
        Select dropdown2= new Select(driver.findElement(By.id("form-city")));
        dropdown2.selectByVisibleText(city);
        Thread.sleep(2000);

        driver.findElement(By.id("form-email")).sendKeys(email);
        Thread.sleep(2000);

        driver.findElement(By.id("form-mobile")).sendKeys(mobile);
        Thread.sleep(2000);

        driver.findElement(By.id("form-username")).sendKeys(uname);
        Thread.sleep(2000);

        driver.findElement(By.id("form-password")).sendKeys(pw);
        Thread.sleep(2000);

        driver.findElement(By.id("form-password-confirm")).sendKeys(cpw);
        Thread.sleep(2000);


        //select the group
        Select dropdown3= new Select(driver.findElement(By.id("group")));
        for(int i=0;i<group.length;i++) {
            dropdown3.selectByVisibleText(group[i]);
            Thread.sleep(2000);
        }

        // Click on 'Sign In' button
        driver.findElement(By.id("addbtn")).click();

        // looks for an element that has been marked as required on a submit attempt
        WebElement elem1 = driver.findElement(By.cssSelector("input:required"));


        String actual = null;
        //check whether such a required field is empty

            if (elem1.getAttribute("value").isEmpty()) {
                actual = "true";


            } else if (driver.getTitle() == "Home Page") {
                actual = "true";

            }


        org.testng.Assert.assertEquals(actual,expected);
        Thread.sleep(2000);
    }



    @AfterMethod
    protected void deleteUser() {


        LOG.info("Reset the form fields");
        driver.findElement(By.id("resetbtn")).click();

        PreparedStatement statement2 = null;
        DatabaseUtility dbPool = null;
        Connection conn = null;


        LOG.info("Query for the user deletion");
        String deleteQuery ="DELETE  FROM user" + " WHERE  username = 'right'";

        /** connect to the database pool**/

        LOG.info("To obtain the database connection obtained for user deletion");
        dbPool=new DatabaseUtility();



        try {
            conn = dbPool.getConnection();
            LOG.info("Database connection obtained for user deletion");

            statement2=conn.prepareStatement(deleteQuery);

            // insert the data
            statement2.executeUpdate();

            LOG.info("in afterClass");
            //conn.close();

        } catch (Exception e)
        {
            LOG.error("Got an exception! : {}", e.getMessage());
        }finally{
            if(statement2 != null){
                try {
                    statement2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @AfterTest
    public  void logOut() throws InterruptedException {

        Thread.sleep(2000);
        driver.navigate().to("http://localhost:8080");
    }

}
