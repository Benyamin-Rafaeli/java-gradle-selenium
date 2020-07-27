import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.*;

public class testSuite {
    private final String url = "http://the-internet.herokuapp.com/";
    //    private final String url = "http://localhost:7080/";
    private WebDriver driver;
    private FluentWait wait;
    public Logger log;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.navigate().to(url);
        driver.manage().window().maximize();
        wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30));
//        log.info("Navigate to => " + url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
//        log.info("Method name => " + result.getMethod().getMethodName());
//        System.out.println("method name:" + result.getMethod().getMethodName());
        driver.quit();
    }

    @Test
    public void aBTesting() {
        clickByLinkTextAndValidateUrl("A/B Testing", "abtest");

//        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "A/B Test Variation 1");
    }

    @Test
    public void addRemoveElements() {
        clickByLinkTextAndValidateUrl("Add/Remove Elements", "add_remove_elements");

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.cssSelector("button")).click();
            assertEquals(driver.findElement(By.id("elements")).findElements(By.tagName("button")).size(), i + 1);
        }

        int size = driver.findElement(By.id("elements")).findElements(By.tagName("button")).size();
        for (int i = 0; i < size; i++) {
            List<WebElement> myArray = driver.findElement(By.id("elements")).findElements(By.tagName("button"));
            myArray.get(myArray.size() - 1).click();
            assertEquals(size - i, myArray.size());
        }
    }

    @Test
    public void basicAuth() {
        // Some of the applications that are secured with Basic Authentication.
        // If you want to access those applications first you need to pass credentials.
        // Those applications will launch a system level pop-up which cant not be handled by selenium.
        // If you access below url it will ask for authentication.
        // UserName --admin // Password --admin // URL --- http://the-internet.herokuapp.com/basic_auth
        // Updated URl -- http://admin:admin@the-internet.herokuapp.com/basic_auth

        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
//        waitUrlContains("basic_auth");

        String actualText = driver.findElement(By.xpath("//div[@class='example']/p")).getText();
        assertEquals(actualText, "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void brokenImages() {
        clickByLinkTextAndValidateUrl("Broken Images", "broken_images");

        for (WebElement image : driver.findElements(By.cssSelector("img"))) {
            if (image.getAttribute("naturalWidth").equals("0")) {
                assertNotEquals(image.getAttribute("naturalWidth"), 0);
            }
        }
    }

    @Test
    public void challengingDOM() {
        clickByLinkTextAndValidateUrl("Challenging DOM", "challenging_dom");

        WebElement tbody = driver.findElement(By.tagName("tbody"));

        for (WebElement td : tbody.findElements(By.tagName("td"))) {
            if (td.getText().equals("Consequuntur1")) {
                System.out.println("Found: " + td.getText());
                return;
            }
        }
    }

    @Test
    public void checkboxes1() {
        clickByLinkTextAndValidateUrl("Checkboxes", "checkboxes");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type=\"checkbox\"]"));

        System.out.println("With .attribute('checked')");
        for (WebElement checkbox : checkboxes) {
            System.out.println(checkbox.getAttribute("checked"));
        }

        System.out.println("\nWith .selected?");
        for (WebElement checkbox : checkboxes) {
            System.out.println(checkbox.isSelected());
        }
    }

    @Test
    public void checkboxes2() {
        clickByLinkTextAndValidateUrl("Checkboxes", "checkboxes");

        WebElement checkbox = driver.findElement(By.cssSelector("form input:nth-of-type(2)"));
        assertTrue(Boolean.parseBoolean(checkbox.getAttribute("checked")));
        assertNotNull(checkbox.getAttribute("checked"));
    }

    @Test
    public void checkboxes3() {
        clickByLinkTextAndValidateUrl("Checkboxes", "checkboxes");

        WebElement checkbox = driver.findElement(By.cssSelector("form input:nth-of-type(2)"));
        assertTrue(checkbox.isSelected());
    }

    @Test
    public void rightClickContextMenu() {
        clickByLinkTextAndValidateUrl("Context Menu", "context_menu");

        WebElement menu = driver.findElement(By.id("hot-spot"));

        Actions action = new Actions(driver);
        action.contextClick(menu)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "You selected a context menu");
    }

    @Test(enabled = false)
    public void userLogin() {
        clickByLinkTextAndValidateUrl("Form Authentication", "login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius")).click();

        System.out.println("Current URL is:" + driver.getCurrentUrl());

        assertTrue(driver.getCurrentUrl().contains("secure"));
    }

    @Test
    public void disappearingElements() {
        clickByLinkTextAndValidateUrl("Disappearing Elements", "disappearing_elements");

        List<WebElement> elements;
        elements = driver.findElements(By.linkText("About"));
        assertTrue(elements.size() > 0);

        driver.findElement(By.linkText("About")).click();

        elements = driver.findElements(By.linkText("About"));
        assertEquals(elements.size(), 0);
    }

    @Test
    public void dragAndDrop() {
        clickByLinkTextAndValidateUrl("Drag and Drop", "drag_and_drop");

        assertEquals(driver.findElement(By.id("column-a")).getText(), "A");
        assertEquals(driver.findElement(By.id("column-b")).getText(), "B");

        WebElement dragged = driver.findElement(By.id("column-a"));
        WebElement dropped = driver.findElement(By.id("column-b"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(dragged, dropped).perform();
//        builder.dragAndDrop(dropped, dragged).perform();

        assertEquals(driver.findElement(By.id("column-a")).getText(), "A");
        assertEquals(driver.findElement(By.id("column-b")).getText(), "B");
    }

    private void clickByLinkTextAndValidateUrl(String clickOnText, String part) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(clickOnText)));
        driver.findElement(By.linkText(clickOnText)).click();
//        log.info("Click on => " + clickOnText);

        wait.until(ExpectedConditions.urlContains(part));
        assertTrue(driver.getCurrentUrl().contains(part));
//        log.info("Validate urlContains => " + part);
    }

//    private void start(String url) {
//        log.info("");
//        System.out.println("Current URL is: " + url);
//        driver.navigate().to(url);
//        driver.manage().window().maximize();
//    }
}
