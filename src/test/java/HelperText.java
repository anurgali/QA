import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HelperText  extends TestBase{

    @BeforeSuite
    public void setUpInternal(){

        super.url="https://partner.cloudrein.com/reset-password";
    }

    @Test
    public void passResetTest(){
        WebElement emailField = findByXPath("//input[@placeholder=\"Email\"]");
        emailField.click();
        emailField.clear();
        emailField.sendKeys("Andre3gmail");
        WebElement i= driver.findElement(By.cssSelector("#email_help > div"));
        Assert.assertEquals(i.getText(), "Please enter a valid Email address");
    }


}

