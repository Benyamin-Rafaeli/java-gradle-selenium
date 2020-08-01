package base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertTrue;

public class TestUtilities extends BaseTest {

    public void clickByLinkTextAndValidateUrl(String clickOnText, String part) {

        System.out.println("Before Click on => " + clickOnText);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(clickOnText)));
        driver.findElement(By.linkText(clickOnText)).click();
        System.out.println("After Click on => " + clickOnText);

        System.out.println("Before Validate urlContains => " + part);
        wait.until(ExpectedConditions.urlContains(part));
        assertTrue(driver.getCurrentUrl().contains(part));
        System.out.println("After Validate urlContains => " + part);
    }

}
