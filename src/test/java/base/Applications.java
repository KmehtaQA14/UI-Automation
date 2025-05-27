package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;

import java.util.List;

public class Applications {
    private WebDriver driver; // WebDriver instance to interact with the browser

    public HomePage homePage; // Public reference to the HomePage object (can be accessed in tests)

    // Constructor: launches the application and handles cookie consent
    public Applications(WebDriver driver, String url) throws InterruptedException {
        this.driver = driver;

        // Launch the application using the provided URL
        launchApplication(url);

        // Click the cookie consent "Accept" button (specific to the application)
        driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

        // Uncomment to initialize page objects after launching (currently commented)
        // initPages();
    }

    // Navigates to the specified URL and waits for 5 seconds
    private void launchApplication(String url) throws InterruptedException {
        driver.get(url); // Load the URL
        Thread.sleep(5000); // Wait to allow full page load (can be replaced with WebDriverWait for better practice)
    }

    // Initializes page objects and prints the number of iframes on the page
    private void initPages() {
        homePage = new HomePage(driver); // Initialize HomePage object

        // Get all iframe elements and print their count
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Number of iframes on the page: " + iframes.size());

        // Extend this method to initialize other page objects as needed
    }
}
