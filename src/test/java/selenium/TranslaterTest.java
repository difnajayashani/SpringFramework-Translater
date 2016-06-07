package selenium;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class TranslaterTest {


    /**create the logger object for logging */
    private static final Logger LOG = LogManager.getLogger(AdduserTest.class);

    private  String baseUrl;
    private String Url;
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
        driver.findElement(By.id("form-username")).sendKeys("nayana");
        driver.findElement(By.id("form-password")).sendKeys("nayana");
        driver.findElement(By.id("btnlogin")).click();

    }

    /** provide data for the test method**/
    @DataProvider
    public Object[][] translateData(){
        /**
         * rows=> represents the no.of times test run
         * column=> represent the data parameters
         * **/
        return new Object[][]{
                {"father","cha","English","Vietnamese"},
                {"father","pai","English","Portuguese"}



        };
    }

    @Test(dataProvider ="translateData")
    public  void translate(String originalText, String translatedExpect, String originalLang, String translatedLang)
            throws InterruptedException {

        driver.findElement(By.id("original-text")).clear();

        driver.findElement(By.id("original-text")).sendKeys(originalText);
        Thread.sleep(2000);

        //select the original language
        Select dropdown1= new Select(driver.findElement(By.id("original")));
        dropdown1.selectByVisibleText(originalLang);
        Thread.sleep(2000);

        //select the original language
        Select dropdown2= new Select(driver.findElement(By.id("translated")));
        dropdown2.selectByVisibleText(translatedLang);
        Thread.sleep(2000);

        // Click on Translate button
        driver.findElement(By.id("btnTranslate")).click();
        Thread.sleep(2000);

        String translatedActual= driver.findElement(By.id("text_trans")).getAttribute("value");
        LOG.info("translatedActual is : {}", translatedActual);

        org.testng.Assert.assertEquals(translatedActual,translatedExpect);


    }

    @AfterTest
    public void logout() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().to("http://localhost:8080");
        Thread.sleep(2000);

    }







}
