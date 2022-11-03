import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{


    @BeforeSuite
    public void setUpInternal(){
        super.url="https://cloudrein.com/newapp#/backups";


    }

    @BeforeMethod
    public void signInCheck(){
        WebElement logOut = driver.findElement(By.xpath("//span[@xpath='1']"));
        if (!(logOut !=null && logOut.getText().equals("Log Out"))){
            //we are not logged in and we need to sign in
            signIn();
        }
    }

    @Test
    public void homePageExistenceTest(){
        WebElement homepage = driver.findElement(By.xpath("//h3[@xpath='1']"));
        Assert.assertEquals(homepage.getText(),"Homepage");
    }

}
