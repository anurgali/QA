import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SignUpPageTest extends TestBase{

    @BeforeSuite
    public void setUpInternal(){

        super.url="https://partner.cloudrein.com//sign-up";
    }

    @Test (dataProvider = "newUserWithCsv")
    public void positiveScenarioTest(String fname, String lname,String email,String pass){
        WebElement firstName = findByXPath("//input[@placeholder=\"First Name\"] ");
        Assert.assertEquals(firstName.getText(),"First Name");
        WebElement lastName = findByXPath("//input[@placeholder=\"Last Name\"] ");
        Assert.assertEquals(lastName.getText(),"Last Name");
        WebElement eMail = findByXPath("//input[@placeholder=\"Email\"] ");
        Assert.assertEquals(eMail.getText(),"Email");
        WebElement passWord = findByXPath("//input[@placeholder=\"Password\"] ");
        Assert.assertEquals(passWord.getText(),"Password");
        WebElement confirmPassword = findByXPath("//input[@placeholder=\"Confirm Password\"] ");
        Assert.assertEquals(confirmPassword.getText(),"Confirm Password");
    }

    @DataProvider
    public Iterator<Object[]> newUserWithCsv() {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/TEL-33.csv"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                list.add(new Object[]{data[0],data[1],data[2],data[3]});
                line = reader.readLine();
            }
        } catch (IOException e){
            TestBase.logger.error(e.getMessage(), e);
        }
        return list.iterator();
    }




}
