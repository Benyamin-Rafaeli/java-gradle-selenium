package base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertTrue;

public class TestUtilities extends BaseTest {

    public void clickByLinkTextAndValidateUrl(String clickOnText, String part) {
        System.out.println("wait until elementToBeClickable => " + clickOnText);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(clickOnText)));
        System.out.println("click on element wit text => " + clickOnText);
        driver.findElement(By.linkText(clickOnText)).click();

        System.out.println("wait until urlContains => " + part);
        wait.until(ExpectedConditions.urlContains(part));
        System.out.println("validate urlContains => " + driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().contains(part));
    }

    public void clickByXpathContainsText(String text) {
        final By buttonToClick = By.xpath("//button[contains(.,'" + text + "')]");
        System.out.println("wait until presenceOfElementLocated => " + buttonToClick);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonToClick));
        System.out.println("click on element => " + buttonToClick);
        driver.findElement(buttonToClick).click();
    }

    public void linkText(String text) {
        final By buttonToClick = By.xpath("//button[contains(.,'" + text + "')]");
        System.out.println("wait until presenceOfElementLocated => " + buttonToClick);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonToClick));
        System.out.println("click on element => " + buttonToClick);
        driver.findElement(buttonToClick).click();
    }

}
