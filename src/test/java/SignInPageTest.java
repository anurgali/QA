import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SignInPageTest extends TestBase {



    @BeforeSuite
    public void setUpInternal(){
        super.url="https://cloudrein.com/newapp#/sign-in";
    }

    //test1
    @Test(priority = 0)
    public void headerTest() throws Exception {
        logger.info("headerTest");
        WebElement tag = findByTagName("h1");
        Assert.assertEquals(tag.getText(),"Sign In to your Account");
    }

    private WebElement findByTagName(String h1) {
        return driver.findElement(By.tagName(h1));
    }


    @Test(priority = 1)
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
        logger.info("starting to test sign in is enabled.");
        WebElement emailField = findByXPath("//input[@placeholder=\"Email\"]");
        emailField.click();
        emailField.clear();

                MyCredential.email2 = MyAccountingData.incorrectemail;
        emailField.sendKeys(MyCredential.email2);



        WebElement passField = findByXPath("//input[@placeholder=\"Password\"]");
        passField.click();
        MyCredential.password2 = MyAccountingData.newpassword;
        passField.sendKeys(MyCredential.password2);
        sleep(1000);
        Actions a=new Actions(driver);
        a.moveToElement(passField).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();

        WebElement signInButton = findByXPath("//button[@type=\"submit\"]");

        Assert.assertFalse(signInButton.isEnabled());
        logger.info("finished signInIsEnabledTest.");
    }

    @Test(dataProvider = "newUser", dataProviderClass = MyDataProviders.class)
    public void correctCredentialsTest(String email, String pass) {
        signIn(email, pass);
        sleep(1000);
        WebElement headEmail = findByXPath("//span[@class='StyledHeader__StyledUserEmail-sc-17b3aa3-7 Jmbq']");
        Assert.assertEquals(headEmail.getText(), email.toLowerCase());
    }

    @Test(dataProvider = "newUserWithCsv", dataProviderClass = MyDataProviders.class)
    public void correctCredentialsTestWithCsv(MyCredential user) {
        signIn(user.getEmail(), user.getPassword());
        sleep(1000);
        WebElement headEmail = findByXPath("//span[@class='StyledHeader__StyledUserEmail-sc-17b3aa3-7 Jmbq']");
        Assert.assertEquals(headEmail.getText(), user.getEmail().toLowerCase());
    }

    @Test(dataProvider = "NegativescenarioSignInCSV", dataProviderClass = DataProvidersTel31.class)
    public void wrongCredentialsTestWithCsv(MyCredential user) {
        signIn(user.getEmail(), user.getPassword());
        sleep(1000);
        WebElement invalidEmailOrPass = findByXPath("//div[@class='StyledSignIn__Error-sc-t0jmvd-4 eYlGJp']");
        Assert.assertEquals(invalidEmailOrPass.getText(), "Invalid Email or password.");



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
