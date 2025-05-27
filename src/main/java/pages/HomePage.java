package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// HomePage class extending BasePage to reuse generic WebDriver actions
public class HomePage extends BasePage {

    // Locators for various elements on the home page
    private By searchIcon = By.xpath("//ul//li[@class='nav-item displayed-desktop utility-nav-item']//a[@href='#subnav-search-desktop-top']");
    private By searchfield = By.xpath("(//input[@id='search-field'])[2]");
    private By searchButton = By.xpath("(//input[@id='search-field']//..//button)[2]");
    private By textBox = By.xpath("//input[@id='addressInput']");

    // Constructor: ensures we are on the Home Page by checking the presence of a key element
    public HomePage(WebDriver driver) {
        super(driver);
        if (!existsElement(searchIcon)) {
            throw new IllegalStateException("Not on Home Page");
        }
    }

    // Clicks on the search icon and checks if the search field appears
    public boolean click_on_SearchIcon() {
        click(searchIcon); // Click the search icon
        return existsElement(searchfield); // Confirm that the search field is visible
    }

    // Performs a search operation using the provided search keyword
    public boolean perFormSearch(String searchKey) {
        type(searchfield, searchKey); // Type the search keyword
        click(searchButton); // Click the search button

        // Verify that the result header with the search term appears
        return existsElement(By.xpath("//h3[@class='title' and text() ='" + searchKey + "']"));
    }

    // Accepts the privacy policy cookie pop-up if visible
    public void acceptpplicy() {
        click(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
    }

    // Types an address in the "Find a Center" text box and selects from auto-suggestions
    public void type_FindCenter(String newYork) throws InterruptedException {
        typeAndSelectSuggestion(textBox, newYork); // Call method from BasePage to handle auto-suggestion input
    }
}
