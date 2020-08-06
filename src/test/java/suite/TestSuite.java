package suite;

import base.TestUtilities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
public class TestSuite extends TestUtilities {

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By radius = By.className("radius");
    private final By DeleteBtn = By.xpath("//button[contains(.,'Delete')]");
    private final By StartBtn = By.xpath("//button[contains(.,'Start')]");
    private final By Loading = By.id("loading");
    private final By Message = By.id("message");
    private final By paragraph = By.cssSelector("p");
    private final By h3Part = By.cssSelector("h3");
    private final By modalPopUp = By.cssSelector(".modal-title");
    private final By restartBtn = By.id("restart-ad");


    @Test(priority = 1)
    public void aBTesting() {
        clickByLinkTextAndValidateUrl("A/B Testing", "abtest");
        assertTrue(driver.findElements(paragraph).size() > 0);
        assertTrue(driver.findElements(h3Part).size() > 0);
    }

    @Test
    public void addRemoveElements() {
        clickByLinkTextAndValidateUrl("Add/Remove Elements", "add_remove_elements");
        clickByXpathContainsText("Add Element");

        wait.until(ExpectedConditions.presenceOfElementLocated(DeleteBtn));
        assertTrue(driver.findElements(DeleteBtn).size() > 0);

        driver.findElement(DeleteBtn).click();
        assertEquals(driver.findElements(DeleteBtn).size(), 0);
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

        String actualText = driver.findElement(By.xpath("//div[@class='example']/p")).getText();
        assertEquals(actualText, "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void brokenImages() {
        clickByLinkTextAndValidateUrl("Broken Images", "broken_images");

        for (WebElement image : driver.findElements(By.cssSelector("img"))) {
            if (image.getAttribute("naturalWidth").equals("0")) {
                assertNotEquals(image.getAttribute("naturalWidth"), 0);
                System.out.println("image name => " + image.getAttribute("src") + " <= is broken");
            }
        }
    }

    @Test
    public void challengingDOM() {
        clickByLinkTextAndValidateUrl("Challenging DOM", "challenging_dom");
        String myStr = "Consequuntur1";

        for (WebElement td : driver.findElements(By.tagName("td"))) {
            if (td.getText().equals(myStr)) {
                System.out.println("search " + myStr + " found => " + td.getText());
                return;
            }
        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'Consequuntur7')]")));
//        assertFalse(driver.findElements(By.xpath("//td[contains(.,'Consequuntur7')]")).size() > 0);
    }

    @Test
    public void checkBoxes() {
        clickByLinkTextAndValidateUrl("Checkboxes", "checkboxes");
        // validate that checkbox1 not checked and checkbox2 checked
        assertFalse(driver.findElement(By.xpath("//form[@id='checkboxes']/input")).isSelected());
        assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected());

        WebElement element;

        element = driver.findElement(By.xpath("//form[@id='checkboxes']/input"));
        if (!element.isSelected()) element.click();

        element = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));
        if (element.isSelected()) element.click();
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

    @Test()
    public void userLogin() {
        clickByLinkTextAndValidateUrl("Form Authentication", "login");

        driver.findElement(username).sendKeys("tomsmith");
        driver.findElement(password).sendKeys("SuperSecretPassword!");
        driver.findElement(radius).click();
    }

    @Test
    public void disappearingElements() {
        clickByLinkTextAndValidateUrl("Disappearing Elements", "disappearing_elements");

        List<WebElement> elements;

        elements = driver.findElements(By.linkText("About"));
        assertTrue(elements.size() > 0);

        clickByLinkText("About");

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

    @Test
    public void Dropdown() {
        clickByLinkTextAndValidateUrl("Dropdown", "dropdown");
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h3"), "Dropdown List"));

        WebElement dropdown = driver.findElement(By.id("dropdown"));
        dropdown.findElement(By.xpath("//option[. = 'Option 1']")).click();
        assertEquals(dropdown.getAttribute("value"), "1");

        dropdown.findElement(By.xpath("//option[. = 'Option 2']")).click();
        assertEquals(dropdown.getAttribute("value"), "2");
    }

    @Test
    public void dynamicContent() {
        clickByLinkTextAndValidateUrl("Dynamic Content", "dynamic_content");
        clickByLinkText("click here");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@id='content'])")));
        assertTrue(driver.findElements(By.xpath("(//div[@id='content'])")).size() > 0);
    }

    @Test
    public void dynamicControls_1() {
        clickByLinkTextAndValidateUrl("Dynamic Controls", "dynamic_controls");
        clickByXpathContainsText("Remove");

        wait.until(ExpectedConditions.presenceOfElementLocated(Loading));
        wait.until(ExpectedConditions.textToBe(Message, "It's gone!"));

        clickByXpathContainsText("Add");

        wait.until(ExpectedConditions.presenceOfElementLocated(Loading));
        wait.until(ExpectedConditions.textToBe(Message, "It's back!"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkbox")));
    }

    @Test
    public void dynamicControls_2() {
        clickByLinkTextAndValidateUrl("Dynamic Controls", "dynamic_controls");

        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.cssSelector("#input-example > input"))));

        clickByXpathContainsText("Enable");

        wait.until(ExpectedConditions.visibilityOfElementLocated(Loading));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']")));
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("12345");
        String value = driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value");
        assertEquals(value, "12345");
    }

    @Test
    public void dynamicLoading() {
        clickByLinkTextAndValidateUrl("Dynamic Loading", "dynamic_loading");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Example 1: Element on page that is hidden")));
        driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(StartBtn));
        driver.findElement(StartBtn).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Loading));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(.,'Hello World!')]")));

        driver.navigate().back();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Example 2: Element rendered after the fact")));
        driver.findElement(By.linkText("Example 2: Element rendered after the fact")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(StartBtn));
        driver.findElement(StartBtn).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Loading));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(.,'Hello World!')]")));
    }

    @Test
    public void entryAd() {
        clickByLinkTextAndValidateUrl("Entry Ad", "entry_ad");
        wait.until(ExpectedConditions.presenceOfElementLocated(modalPopUp));

        do {
            driver.findElement(restartBtn).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(modalPopUp));
        } while (!wait.until(ExpectedConditions.textToBe(modalPopUp, "THIS IS A MODAL WINDOW")));

    }

}


















