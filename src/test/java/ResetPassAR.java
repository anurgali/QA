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

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ResetPassAR extends TestBase {

    @BeforeSuite
    public void setUpInternal(){

        super.url="https://partner.cloudrein.com/reset-password";
    }

    @Test(dataProvider = "reset", dataProviderClass = DataProvidersAR.class)
    public void resetPassTest(String email)  {
        logger.info("Starting test: " + "reset");
        WebElement emailField = findByXPath("//input[@placeholder=\"Email\"]");
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);



        WebElement resetButton = findByXPath("//button[@type=\"submit\"]");
        resetButton.click();

       sleep(5000);
        WebElement text = driver.findElement(By.tagName("strong"));


        Assert.assertEquals(text.getText(),"Thank you. An email has been sent to you. " +
                "Please follow the email instructions in order to complete your password reset.");
        logger.info("finished resetPassTest."); }
    }

//tel_32 .Message: ..WARNING: Unable to find an exact match for CDP version 107,
// so returning the closest version found: 106..

