import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ResetPass extends TestBase {
    @BeforeSuite
    public void setUpInternal(){

        super.url="https://partner.cloudrein.com/reset-password";
    }
    @Test
    public void incorrectEmailResetpassTest()  {
        WebElement emailField = findByXPath("//input[@placeholder=\"Email\"]");
        emailField.click();
        emailField.clear();
        emailField.sendKeys("smikoann@gmail.co");


        WebElement signInButton = findByXPath("//button[@type=\"submit\"]");
        signInButton.click();

       // Thread.sleep(5000);
        WebElement text = driver.findElement(By.tagName("strong"));


        Assert.assertEquals(text.getText(),"An email has been sent to you.");
    }
    }


