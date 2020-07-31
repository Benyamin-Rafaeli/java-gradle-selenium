package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() {
        System.out.println("Create driver: " + browser);

        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless", "no-sandbox", "disable-gpu", "disable-dev-shm-usage");
                driver.set(new ChromeDriver(options));
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless", "no-sandbox", "disable-gpu", "disable-dev-shm-usage");
                driver.set(new FirefoxDriver(options));
            }
            default -> {
                System.out.println("Do not know how to start: " + browser + ", starting chrome.");
                driver.set(new ChromeDriver());
            }
        }

        return driver.get();
    }
}
