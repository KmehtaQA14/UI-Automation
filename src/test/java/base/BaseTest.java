package base;

import lombok.extern.slf4j.Slf4j;
import selenium.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j // Lombok annotation for logging (provides `log.info`, `log.error`, etc.)
public class BaseTest {
    protected WebDriver driver;         // WebDriver instance for test classes
    protected Applications app;         // Custom application layer wrapper (usually holds page objects)

    public DriverFactory driverFactory = new DriverFactory();
    // Accepts "browser" parameter (default: chrome) before each test method
    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) throws InterruptedException {
        // Initialize WebDriver based on browser type from DriverFactory
        driver = driverFactory.getDriver(browser);

        // Initialize Applications class with WebDriver and application URL from config
        app = new Applications(driver, ConfigReader.getProperty("url"));

        // Maximize browser window
        driver.manage().window().maximize();

        // Set an implicit wait of 60 seconds (used when elements aren't immediately available)
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    // Executes after each test method, checks if test failed, and captures screenshot if it did
    @AfterMethod
    public void captureFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(); // Capture screenshot on failure
        }
    }

    // Annotated to attach screenshot to Allure report if test fails
    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] takeScreenshot() {
        // Capture screenshot as byte array
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Clean up and close the browser after each test method
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser and end session
        }
    }
}
