package base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertTrue;

public class TestUtilities extends BaseTest {

    public void clickByLinkTextAndValidateUrl(String clickOnText, String part) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(clickOnText)));
        driver.findElement(By.linkText(clickOnText)).click();
//        log.info("Click on => " + clickOnText);

        wait.until(ExpectedConditions.urlContains(part));
        assertTrue(driver.getCurrentUrl().contains(part));
//        log.info("Validate urlContains => " + part);
    }

//    public void start(String url) {
//        log.info("");
//        System.out.println("Current URL is: " + url);
//        driver.navigate().to(url);
//        driver.manage().window().maximize();
//    }

}
