package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    // Public factory method to initialize WebDriver based on browser name
    public  WebDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            case "edge":
                return createEdgeDriver();
            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }
    }

    // Chrome driver creation with default options (can be extended)
    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");  // Example argument
        // options.addArguments("--headless");      // Uncomment for headless mode
        return new ChromeDriver(options);
    }

    // Firefox driver creation with options
    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        // options.setHeadless(true);              // Enable if needed
        return new FirefoxDriver(options);
    }

    // Edge driver creation with options
    private static WebDriver createEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        // You can add Edge-specific options here
        return new EdgeDriver(options);
    }
}
