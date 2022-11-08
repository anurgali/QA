import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{
Logger logger= LoggerFactory.getLogger(TestBase.class);
// logging to HPTest.2
    //1 logger.
    @BeforeSuite
    public void setUpInternal(){
        super.url="https://cloudrein.com/newapp#/backups";
    }

    @BeforeMethod
    public void signInCheck(){
        sleep(1000);
        try {
            WebElement logOut = findByXPath("//span[@xpath='1']");
        } catch (NoSuchElementException exception){
            //we are not logged in, and we need to sign in
            signIn();
        }

    }

    @Test
    public void homePageExistenceTest(){
        sleep(3000);
        WebElement homepage = findByXPath("//h3[@class='ant-typography StyledTypography__StyledTitle-sc-d1hytu-0 MxoCQ StyledTypography__SubTitle-sc-d1hytu-3 StyledPageTitle__PageTitle-sc-18q9nsb-0  eyseN']");
        Assert.assertEquals(homepage.getText(),"Homepage");
    }

}
