import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AutomationTest {
    WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional ("CHROME") String browser){
        if (browser.equals("CHROME")) {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver99.exe");
        driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("FIREFOX")){
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    @Parameters({"subjectHeading","email","orderNum","upload","message", "elementSelector","expectedMessage"})
    public void automationTest(String subjectHeading, String email, String orderNum, @Optional("No") String upload, String message, String elementSelector, String expectedMessage){
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.cssSelector("[title='Contact Us']")).click();

        Select selectSubjectHeading = new Select(driver.findElement(By.cssSelector("#id_contact")));
        selectSubjectHeading.selectByVisibleText(subjectHeading);

        driver.findElement(By.cssSelector("#email")).sendKeys(email);

        driver.findElement(By.cssSelector("#id_order")).sendKeys(orderNum);

        if (upload.equalsIgnoreCase("Yes")){
        driver.findElement(By.cssSelector("#fileUpload")).sendKeys("C:\\Users\\Brana\\Desktop\\Test.txt");
        }

        driver.findElement(By.cssSelector("textarea")).sendKeys(message);

        driver.findElement(By.cssSelector("#submitMessage")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(elementSelector)).getText(),expectedMessage);

    }

    @Test
    public void automationMethod(){

    }
}
