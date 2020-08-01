package base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertTrue;

public class TestUtilities extends BaseTest {

    public void clickByLinkTextAndValidateUrl(String clickOnText, String part) {
        log.info("Before Click on => " + clickOnText);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(clickOnText)));
        driver.findElement(By.linkText(clickOnText)).click();
        log.info("After Click on => " + clickOnText);

        log.info("Before Validate urlContains => " + part);
        wait.until(ExpectedConditions.urlContains(part));
        assertTrue(driver.getCurrentUrl().contains(part));
        log.info("After Validate urlContains => " + part);
    }

}
