package tests;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import java.util.HashMap;

@Epic("Home Page")
@Feature("Search Functionality")
@Slf4j
public class HomePageTest extends BaseTest {

    @Test(dataProvider = "SearchTerm", dataProviderClass = Dataproviderclass.class)
    public void validate_Search(HashMap<String, String> inmap) throws InterruptedException {
        // Instantiate HomePage with current driver instance
        HomePage homePage = new HomePage(driver);

        // Validate that the search icon can be clicked
        Assert.assertEquals(homePage.click_on_SearchIcon(), true, "Validating Click Functionality Of SearchIcon");

        // Validate that the search term returns expected result in first position
        Assert.assertEquals(
                homePage.perFormSearch(inmap.get("searchTerm")),
                true,
                "Validating Search terms & Exist on first place"
        );
    }

    @Test(dataProvider = "SearchCenter", dataProviderClass = Dataproviderclass.class)
    public void verify_Comment(HashMap<String,String> inmap) throws InterruptedException {
        // Instantiate HomePage with current driver instance
        HomePage homePage = new HomePage(driver);

        // Click on "Find a Center" and verify it succeeded
        Assert.assertEquals(true, homePage.click_on_FindaCenter());

        // Assert current URL contains the expected substring
        Assert.assertTrue(
                homePage.getCurrentURL().contains(inmap.get("urlContains")),
                "URL should contain expected substring"
        );

        // Type center name in the textbox
        homePage.type_FindCenter(inmap.get("CenterName"));

        // Validate the number of result cards matches the displayed count
        Assert.assertEquals(
                homePage.getSizeofElement(By.xpath("//h3[@class='centerResult__name']")),
                homePage.getText(By.xpath("//span[@class='resultsNumber']"))
        );

        // Get the name and address of the first center in the list
        String centerName = homePage.getText(By.xpath("(//div[@class='description-wrapper'])[1]//h3"));
        String getAddress = homePage.getText(By.xpath("(//div[@class='description-wrapper'])[1]//span[@class='centerResult__address']"));

        // Click on the first result to open the map tooltip
        homePage.click(By.xpath("(//div[@class='description-wrapper'])[1]"));

        // Validate the tooltip headline matches the center name
        Assert.assertEquals(centerName, homePage.getText(By.xpath("//span[@class='mapTooltip__headline']")));

        // Validate the address in the tooltip matches the expected address, with line breaks removed
        Assert.assertEquals(
                getAddress,
                homePage.getText(By.xpath("//div[@class='mapTooltip__address']")).replace("\n", " ").trim());

    }
}
