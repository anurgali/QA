import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    String url;

    //Before test
    @BeforeMethod
    public void setUp() {
        System.out.println("00000000");
        String path = System.getenv("qwe");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //After test
    @AfterMethod(enabled = true)
    public void cleanUp() {
        driver.quit();
    }
}
