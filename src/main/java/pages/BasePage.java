package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j // Lombok annotation to enable logging (log.info, log.error, etc.)
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Locator for "Find a Center" link (4th instance)
    private By elementfindaCenter = By.xpath("(//a[@data-tracking-cta='Find a Center,/child-care-locator,header'])[4]");
    private String currentURL;

    // Constructor initializes WebDriver and WebDriverWait
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Clicks on an element when it becomes clickable
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Types text into a visible input field
    public void type(By locator, String value) {
        log.info("Typing value in element " + locator + " " + value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value);
    }

    // Types slowly character-by-character and selects a suggestion (used for autocomplete fields)
    public void typeAndSelectSuggestion(By locator, String value) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(locator));
            input.click();
            input.clear();

            // Type each character with delay to simulate user typing
            for (char c : value.toCharArray()) {
                input.sendKeys(Character.toString(c));
                Thread.sleep(200); // Simulate delay for suggestion box
            }

            // Wait for suggestion list to appear and select the first suggestion
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pac-item")));
            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            log.error(e.toString()); // Log any error that occurs
        }
    }

    // Retrieves visible text from a web element
    public String getText(By locator) {
        log.info("GetText from Element " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Checks if an element is visible on the page
    public boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false; // Returns false if the element is not visible in time
        }
    }

    // Checks if an element exists in the DOM and is visible
    public boolean existsElement(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            driver.findElement(by); // Confirms presence in DOM
        } catch (NoSuchElementException e) {
            log.error("Unable to Locate elements " + e);
            return false;
        }
        return true;
    }

    // Clicks the "Find a Center" link and checks if the expected heading appears
    public boolean click_on_FindaCenter() {
        try {
            click(elementfindaCenter); // Perform the click action
        } catch (Exception e) {
            log.error(e.toString()); // Log any failure
        }

        // Confirm that the expected heading appears after the click
        return existsElement(By.xpath("//h4[contains(@class, 'centerLocator__sidebarHeading') and contains(normalize-space(text()), 'Find a Center')]"));
    }

    // Gets the current page URL
    public String getCurrentURL() {
        return currentURL = driver.getCurrentUrl();
    }

    // Returns the number of elements matching the locator as a string
    public String getSizeofElement(By xpath) {
        return String.valueOf(driver.findElements(xpath).size());
    }
}
