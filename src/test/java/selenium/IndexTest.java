package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * This class will automatically does the login process testing
 */
public class IndexTest {

    public String baseUrl;
    public WebDriver driver;

    /** opens the browser application before any test runs**/
    @BeforeSuite
    public void openBrowser() {

        baseUrl="http://localhost:8080";
       // driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }

    /** verify whether in the Login Page before any test method runs**/
    @BeforeMethod
    public void verifyHomepageTitle(){
        String expected= "Login Page";
        String actual= driver.getTitle();
       org.testng.Assert.assertEquals(actual, expected, "You are in the Home Page");

    }

    /** provide data for the test method**/
    @DataProvider
    public Object[][] LoginCredentials(){
        /**
         * rows=> represents the no.of times test run
         * column=> represent the data parameters
         * **/
        return new Object[][]{
                {"difna","difna","Home Page"},
                {"jayamal","jayamal123","Home Page"},
                {"difna", "","Login Page"},
                {"","difna","Login Page"},
                {"","","Login Page"}
        };
    }

    /**test method will take data from the data provider **/
    @Test(dataProvider ="LoginCredentials")
    public void loginPageTest(String uname, String pass, String expected) throws IOException, InterruptedException {


        // Enter UserName
        driver.findElement(By.id("form-username")).sendKeys(uname);
        Thread.sleep(2000);

        // Enter Password
        driver.findElement(By.id("form-password")).sendKeys(pass);
        Thread.sleep(2000);

        // Click on 'Sign In' button
        driver.findElement(By.id("btnlogin")).click();

        String actual= driver.getTitle();
        Assert.assertEquals(actual,expected);
    }


    /**navigate to the login page after running the test method once **/
    @AfterMethod
    public void logOut() throws InterruptedException {

        Thread.sleep(2000);
        driver.navigate().to("http://localhost:8080");
        Thread.sleep(2000);

    }


    /** close the browser after running all the tests**/
  /*  @AfterSuite
    public void closeBrowser() throws IOException, InterruptedException {
        driver.quit();

    }*/

}
