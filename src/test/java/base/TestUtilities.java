package base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertTrue;

public class TestUtilities extends BaseTest {

    public void clickByLinkTextAndValidateUrl(String clickOnText, String part) {
        clickByLinkText(clickOnText);

        System.out.println("wait until urlContains => " + part);
        wait.until(ExpectedConditions.urlContains(part));
        System.out.println("validate urlContains => " + driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().contains(part));
    }

    public void clickByXpathContainsText(String text) {
        final By buttonToClick = By.xpath("//button[contains(.,'" + text + "')]");

        clickExecute(buttonToClick);
    }

    public void clickByLinkText(String text) {
        final By buttonToClick = By.linkText(text);

        clickExecute(buttonToClick);
    }

    public void clickById(String text) {
        final By buttonToClick = By.id(text);

        clickExecute(buttonToClick);
    }

    private void clickExecute(By buttonToClick) {
        System.out.println("wait until elementToBeClickable => " + buttonToClick);
        wait.until(ExpectedConditions.elementToBeClickable(buttonToClick));
        System.out.println("click on element => " + buttonToClick);
        driver.findElement(buttonToClick).click();
    }


}
