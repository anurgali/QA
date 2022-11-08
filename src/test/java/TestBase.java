import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    String url;
    Logger logger = LoggerFactory.getLogger(TestBase.class);


    @Deprecated
    public static String getTagName(String tagName) {
        return tagName;
    }
    //tel-27

    //Before test
    @BeforeTest
    public void setUp() {
        //System.out.println("00000000");
        String path = System.getenv("qwe");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //After test
    @AfterSuite(enabled = true)
    public void cleanUp() {
        driver.quit();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //helper method
    public void signIn() {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(MyCredential.email3);

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys("Qwer1234");

        WebElement signInButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        signInButton.click();
    }

    protected WebElement findByXPath(String xpathExpression) {
        return driver.findElement(By.xpath(xpathExpression));
    }
}
