package base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import java.time.Duration;

public class BaseTest {

    protected String url = "http://the-internet.herokuapp.com/";
    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;
    protected Logger log;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)

    public void setUp(@Optional("chrome") String browser, ITestContext ctx) { // firefox chrome

//        Logger strLogger = Logger.getLogger(String.class);

        log = Logger.getLogger(String.class);
        System.out.print("===========>>> " + log);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.navigate().to(url);
        driver.manage().window().maximize();

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

//        log.info("Navigate to => " + url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        System.out.print("Close driver ===========>>> " + result.getMethod().getMethodName());
        log.info("Close driver =========>>> ");
        log.info("Method Name:" + result.getMethod().getMethodName());
        driver.quit();
    }

}
