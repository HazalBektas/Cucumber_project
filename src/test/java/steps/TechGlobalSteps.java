package steps;

import cucumber.api.java.Before;
import cucumber.api.java.bs.A;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.TechGlobalBasePage;
import pages.TechGlobalFrontEndPage;
import pages.TechGlobalPaginationPage;
import utilities.Driver;
import utilities.Waiter;

public class TechGlobalSteps {

    WebDriver driver;
    Actions actions;
    TechGlobalBasePage techGlobalBasePage;
    TechGlobalFrontEndPage techGlobalFrontEndPage;
    TechGlobalPaginationPage techGlobalPaginationPage;


    @Before
    public void setup(){
        driver = Driver.getDriver();
        actions = new Actions(driver);
        techGlobalBasePage = new TechGlobalBasePage();
        techGlobalFrontEndPage = new TechGlobalFrontEndPage();
        techGlobalPaginationPage = new TechGlobalPaginationPage();

    }
    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user moves to {string} header dropdown")
    public void userMovesToHeaderDropdown(String headerDropdown) {
        actions.moveToElement(techGlobalBasePage.headerDropdown).perform();
    }

    @And("user clicks on {string} header dropdown option")
    public void userClicksOnHeaderDropdownOption(String option) {

        techGlobalBasePage.selectByVisibleText(option);
    }

    @Then("user should be navigated to {string}")
    public void userShouldBeNavigatedTo(String pageUrl) {
        Assert.assertEquals( pageUrl, driver.getCurrentUrl());
    }

    @And("user clicks on {string} card")
    public void userClicksOnCard(String cardName) {
        techGlobalFrontEndPage.clickOnCard(cardName);
    }

    @And("user should see {string} heading")
    public void userShouldSeeHeading(String headingText) {
        switch (headingText){
            case "Pagination":
                Assert.assertEquals(headingText,techGlobalPaginationPage.mainHeading.getText());
                break;
            case "World City Populations 2022":
                Assert.assertEquals(headingText,techGlobalPaginationPage.subHeading.getText());
                break;
            default:
                throw  new NotFoundException("The heading text was not found !");


        }
    }

    @And("user should see {string} paragraph")
    public void userShouldSeeParagraph(String paragraphText) {
        Assert.assertEquals(paragraphText,techGlobalPaginationPage.pageContent.getText());
    }

    @And("user should see “Previous” button is disabled")
    public void userShouldSeeButtonIsDisabled() {
        Assert.assertFalse(techGlobalPaginationPage.btnPrevious.isEnabled());
    }

    @And("user should see “Next” button is enabled")
    public void userShouldSeeButtonIsEnabled() {
        Assert.assertTrue(techGlobalPaginationPage.btnNext.isEnabled());
    }

    @When("user clicks on “Next” button")
    public void userClicksOnButton() {
        techGlobalPaginationPage.btnNext.click();
    }

    @When("user clicks on “Next” button till it becomes disabled")
    public void userClicksOnButtonTillItBecomesDisabled() {
        Assert.assertFalse(techGlobalPaginationPage.btnNext.isEnabled());
    }

    @And("user should see city with info below and an image")
    public void userShouldSeeCityWithInfoBelowAndAnImage(DataTable data) {
        for (int i = 0; i <data.asList().size(); i++) {
            Assert.assertEquals(data.asList().get(i), techGlobalPaginationPage.cityDetailList.get(i).getText());
        }
        Assert.assertTrue(techGlobalPaginationPage.cityImage.isDisplayed());
        techGlobalPaginationPage.btnNext.click();
    }
}
