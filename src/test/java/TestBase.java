import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.Browser;

public class TestBase {
    WebDriver driver;
    String url, browser;
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);


    @Deprecated
    public static String getTagName(String tagName) {
        return tagName;
    }
    //tel-27

    //Before test
    @BeforeMethod
    public void setUp() {
        String path;

        browser = System.getProperty("browser");
        if (browser.equals(Browser.CHROME.browserName())) {
            path = System.getenv("chromeDriver");
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            path = System.getenv("firefoxDriver");
            System.setProperty("webdriver.gecko.driver", path);
            driver = new FirefoxDriver();
            else if (browser.equals(Browser.EDGE.browserName())) {
                path = System.getenv("edgeDriver");
                System.setProperty("webdriver.edge.driver", path);
                driver = new edgeDriver();
        } else {
            logger.error("No supported browser specified. Supported browsers: chrome, firefox,edge");
        }

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void logMethod(Method m, Object[] p){
        logger.info("Starting method: " + m.getName()+" with data: "+ Arrays.asList(p));
    }

    //After test
    @AfterMethod(enabled = true)
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
    public void signIn(String email, String pass) {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys(pass);

        WebElement signInButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        signInButton.click();
    }

    protected WebElement findByXPath(String xpathExpression) {
        return driver.findElement(By.xpath(xpathExpression));
    }
}
