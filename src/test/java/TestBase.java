import com.google.common.io.Files;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.Browser;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class TestBase {
    WebDriver driver;
    String url, browser;
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    private ScreenRecorder sr;


    @Deprecated
    public static String getTagName(String tagName) {
        return tagName;
    }
    //tel-27

    //Before test
    @BeforeMethod
    public void setUp() {
        String path;

        browser = System.getProperty("browser");
        if (browser.equals(Browser.CHROME.browserName())) {
            path = System.getenv("chromeDriver");
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            path = System.getenv("firefoxDriver");
            System.setProperty("webdriver.gecko.driver", path);
            driver = new FirefoxDriver();
        }
            else if (browser.equals(Browser.EDGE.browserName())) {
                path = System.getenv("edgeDriver");
                System.setProperty("webdriver.edge.driver", path);
                driver = new EdgeDriver();
        } else {
            logger.error("No supported browser specified. Supported browsers: chrome, firefox,edge");
        }

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void logMethod(Method m, Object[] p){
        logger.info("Starting method: " + m.getName()+" with data: "+ Arrays.asList(p));
    }

    @AfterMethod
    public void stopTest(ITestResult result){
        if (!result.isSuccess()) {
            logger.error("Failed test:" +result.getMethod().getMethodName()+" screenshot:"+takeScreenshot());
        }
        driver.quit();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //helper method
    public void signIn(String email, String pass) {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder=\"Email\"]"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder=\"Password\"]"));
        passField.click();
        passField.clear();
        passField.sendKeys(pass);

        WebElement signInButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        signInButton.click();
    }

    protected WebElement findByXPath(String xpathExpression) {
        return driver.findElement(By.xpath(xpathExpression));
    }

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test/resources/screenshots/screen"+System.currentTimeMillis()+".png");
        try {
            screenshot.createNewFile();
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return "";
        }
        return screenshot.getAbsolutePath();
    }

    public void startRecording() {
        File file = new File("src/test/resources/records");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle captureSize = new Rectangle(0,0, dimension.width, dimension.height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        try {
            sr = new Recorder(gc, captureSize,
                    new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG, CompressorNameKey, ENCODING_AVI_MJPG,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, file, "MyVideo");
            sr.start();
        } catch (IOException e1){
            logger.error(e1.getMessage());
        } catch (AWTException e2) {
            logger.error(e2.getMessage());
        }
    }

    public void stopRecoding() {
        try {
            sr.stop();
        } catch (IOException e1){
            logger.error(e1.getMessage());
        }
    }

    @BeforeSuite
    public void deleteRecording(){
        File dir = new File("src/test/resources/records");
        for (File f: dir.listFiles()){
            f.delete();
        }
    }
}
