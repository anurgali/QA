import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
        String path = System.getenv("qwe");
        System.setProperty("webdriver.chrome.driver", path);
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
    public void signInIsEnabledTest() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("ckbcskakqrfeumnlxg@kvhrw.com");


        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.sendKeys("38fBFGbx3yQjJ7P");
        Thread.sleep(1000);
        Actions a=new Actions(driver);
        a.moveToElement(passField).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();

        WebElement signInButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));

        Assert.assertFalse(signInButton.isEnabled());

    }/**/

    @Test
    public void correctCredentialsTest() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("testQA36a@gmail.com");

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys("Qwer1234");

        WebElement signInButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        signInButton.click();

        Thread.sleep(5000);

        WebElement headEmail = driver.findElement(By.xpath("//span[@class='StyledHeader__StyledUserEmail-sc-17b3aa3-7 Jmbq']"));
        Assert.assertEquals(headEmail.getText(),"testqa36a@gmail.com");




    }
    @Test
    public void tenErrorTest() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys("testQA36a@gmail.com");

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys("Qwer123");

        WebElement signInButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        for (int i=0; i<10; i++) {
            signInButton.click();
            Thread.sleep(100);
        }


        WebElement invalidEmailOrPass = driver.findElement(By.xpath("//div[@class='StyledSignIn__Error-sc-t0jmvd-4 eYlGJp']"));
        Assert.assertEquals(signInButton.getText(), "Too many login failures, this account will be locked for 10 minutes.");

    }

    //After test
    @AfterMethod(enabled = false)
    public void cleanUp(){
        driver.quit();
    }
}
