package base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

import java.time.Duration;

public class BaseTest {
    protected String url = "http://the-internet.herokuapp.com/";
    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;
//    protected Logger log;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

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
//        log.info("Method name => " + result.getMethod().getMethodName());
        System.out.println("method name:" + result.getMethod().getMethodName());
        driver.quit();
    }

}
