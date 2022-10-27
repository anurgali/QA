import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MyTest {

    WebDriver driver;

    //Before test
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "/Users/albertnurgaliev/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://cloudrein.com/newapp#/sign-in");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //test1
    @Test
    public void headerTest() throws Exception {
        WebElement tag = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(tag.getText(),"Sign In to your Account");
    }

    @Test
    public void mySecondTest(){
        WebElement tag = driver.findElement(By.tagName("h1"));
        Assert.assertTrue(tag.getText().contains("Sign In"));
    }

    @Test
    public void findClass(){
        WebElement or = driver.findElement(By.xpath("//p[@class='StyledLineSeparator__TextSeparator-sc-tvmx61-2 gGaiwp']"));
        Assert.assertEquals(or.getText(),"or");
    }

    @Test
    public void wrongCredentialsTest(){
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("ckbcskakqrfeumnlxg@kvhrw.com");

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys("38fBFGbx3yQjJ7P");

        WebElement signInButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        signInButton.click();

    }

    //After test
    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
}
