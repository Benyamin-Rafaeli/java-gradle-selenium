package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.log4testng.Logger;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;
    private final Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver() {
        System.out.println("\ncreate driver => " + browser + "\n");

        switch (browser) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                // --headless
                options.addArguments("no-sandbox", "disable-gpu", "disable-dev-shm-usage", "--headless");
                // headed
                // options.addArguments("no-sandbox", "disable-gpu", "disable-dev-shm-usage");
                driver.set(new ChromeDriver(options));
                break;
            }
            case "firefox": {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless", "no-sandbox", "disable-gpu", "disable-dev-shm-usage");
                driver.set(new FirefoxDriver(options));
                break;
            }
            default: {
                log.info("Do not know how to start: " + browser + ", starting chrome.");
                driver.set(new ChromeDriver());
            }
        }

        return driver.get();
    }
}
