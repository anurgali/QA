import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "/Users/albertnurgaliev/Downloads/chromedriver");
        driver = new ChromeDriver();//FirefoxDriver()
        driver.get("https://cloudrein.com/newapp#/sign-in");
       // driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(500,500));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void myFirstTest(){
        System.out.println("Site opened");
    }

    @Test
    public void findElementByTag(){
        /*
        CSS Selectors
        [checked] - наличие атрибута
        [name=email] - совпадение значения
        [title*=Name] - содержит текст
        [src^=http] - начинается с текста
        [src$=.pdf] - заканчивается текстом

        label - по тегу
        .error - по классу
        label.error - по тегу и классу
        label.error.fatal - по тегу и  двум классам
        label.error[for=email] - по тегу, классу и аттрибуту
         */

        /*
        CSS Selectors vs. XPath
        div#main p  >>> //div[@id='main']//p
        div#main > p  >>> //div[@id='main']/p


        XPath structure

        Xpath=//tagname[@attribute='value']
        tagname can be * for wildcard

        //input[@id='search']/../input[@type='button']
        //*[@class='className']/../th[4]

        //span[text()='Sign In']
         */

        /*
        By.cssSelector
        tr:nth-child(8) td:nth-child(3)
        WebElement name3 = driver.findElement(By.cssSelector("tr:nth-child(8) td:nth-child(3)"));

        Assert.assertEquals(actual, expected)
        assertTrue assertFalse

        WebElement element.click() element.clear() element.sendKeys()
         */


        WebElement tag = driver.findElement(By.tagName("h1"));
        WebElement tag2 = driver.findElement(By.cssSelector("h1"));
        WebElement tag3 = driver.findElement(By.xpath("//h1"));

        System.out.println(tag.getText());

        List<WebElement> tags = driver.findElements(By.tagName("tr"));


        WebElement cl = driver.findElement(By.className("abc"));
        WebElement cl2 = driver.findElement(By.cssSelector(".t-records.t-records_animated.t830__allrecords_padd.t-records_visible"));

        WebElement element = driver.findElement(By.className("qwe"));
        WebElement element2 = driver.findElement(By.xpath("//a[@class='t-records t-records_animated t830__allrecords_padd t-records_visible']"));

        WebElement id = driver.findElement(By.id("hs-analytics"));
        WebElement id2 = driver.findElement(By.cssSelector("#hs-analytics"));


        WebElement name = driver.findElement(By.name("form436936038"));
        WebElement name2 = driver.findElement(By.cssSelector("[name=form436936038]"));
        WebElement name3 = driver.findElement(By.cssSelector("[name*=form43693]"));
        WebElement name4 = driver.findElement(By.xpath("//*[contains(@name,'form43693')]"));

        WebElement linkText = driver.findElement(By.linkText("sdf"));
        WebElement partial = driver.findElement(By.partialLinkText("sdf"));

        WebElement search = driver.findElement(By.xpath("//h2[contains(.,'Hello')]"));
        /*
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        //driver.switchTo().alert();
        //alert.accept();
        */
    }

    @AfterMethod(enabled = false)
    public void tearDown(){
        driver.quit();
    }
}
