import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SignInPageTest extends TestBase {

    @BeforeSuite
    public void setUpInternal(){
        super.url="https://cloudrein.com/newapp#/sign-in";
    }

    //test1
    @Test
    public void headerTest(String name) throws Exception {
        WebElement tag = findByTagName(name);
        Assert.assertEquals(tag.getText(),"Sign In to your Account");
    }

    private WebElement findByTagName(String name) {
        return driver.findElement(By.tagName(name));
    }

    @Test
    public void mySecondTest(){
        WebElement tag = findByTagName("h1");
        Assert.assertTrue(tag.getText().contains("Sign In"));
    }

    @Test
    public void findClass(){
        WebElement or = findByXPath("//p[@class='StyledLineSeparator__TextSeparator-sc-tvmx61-2 gGaiwp']");
        Assert.assertEquals(or.getText(),"or");


    }

    @Test
    public void signInIsEnabledTest() {
        WebElement emailField = findByXPath("//input[@placeholder=\"Email\"]");
        emailField.click();
        emailField.clear();
        SecondCredentials.email2 = "ckbcskakqrfeumnlxg@kvhrw.com";
        emailField.sendKeys(SecondCredentials.email2);


        WebElement passField = findByXPath("//input[@placeholder=\"Password\"]");
        passField.click();
        SecondCredentials.password2 = "38fBFGbx3yQjJ7P";
        passField.sendKeys(SecondCredentials.password2);
        sleep(1000);
        Actions a=new Actions(driver);
        a.moveToElement(passField).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();

        WebElement signInButton = findByXPath("//button[@type=\"submit\"]");

        Assert.assertFalse(signInButton.isEnabled());

    }/**/

    @Test
    public void correctCredentialsTest() {
        signIn();
        sleep(1000);
        WebElement headEmail = findByXPath("//span[@class='StyledHeader__StyledUserEmail-sc-17b3aa3-7 Jmbq']");
        Assert.assertEquals(headEmail.getText(), MyCredential.email3);
    }

    @Test
    public void tenErrorTest() {
        WebElement emailField = findByXPath("//input[@placeholder=\"Email\"]");
        emailField.click();
        emailField.clear();
        emailField.sendKeys(MyCredential.email3);

        WebElement passField = findByXPath("//input[@placeholder=\"Password\"]");
        passField.click();
        passField.clear();
        passField.sendKeys(MyCredential.passEmail3);

        WebElement signInButton = findByXPath("//button[@type=\"submit\"]");
        for (int i=0; i<10; i++) {
            signInButton.click();
            sleep(100);
        }


        WebElement invalidEmailOrPass = findByXPath("//div[@class='StyledSignIn__Error-sc-t0jmvd-4 eYlGJp']");
        Assert.assertEquals(signInButton.getText(), "Too many login failures, this account will be locked for 10 minutes.");

    }

}
