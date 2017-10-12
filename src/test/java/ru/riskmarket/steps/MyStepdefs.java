package ru.riskmarket.steps;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MyStepdefs {
    @Given("^open riskmarket\\.ru$")
    public void openRiskmarketRu() {
        System.setProperty("webdriver.chrome.driver", "D:/dev/WebDrivers/chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.timeout = 30000;
        Configuration.collectionsTimeout = 30000;
        open("http://riskmarket.ru");
    }

    @When("^press button with text \"([^\"]*)\"$")
    public void pressButtonWithText(String button) {
        $(byText(button)).click();
    }

    @And("^type to input with name \"([^\"]*)\" text: \"([^\"]*)\"$")
    public void typeToInputWithNameText(String input, String text) {
        sleep(100);
        $(byName(input)).val(text);
    }

    @And("^select countries: (.*)$")
    public void selectCountries(List<String> countries) {
        for (String str : countries) {
            $(".countries-widget__wrapper input").append(str).pressEnter();
            sleep(100);
        }
    }

    @And("^press element with value \"([^\"]*)\"$")
    public void pressElementWithValue(String name) {
        $(byValue(name)).click();
    }


    @And("^wait until login frame disappears$")
    public void waitUntilLoginFrameDisappears() {
        $(".modal-content").waitUntil(disappear, 7000);
    }

    @And("^specify dates of journey, any available dates$")
    public void specifyDatesOfJourneyAnyAvailableDates() {
        $("#preview").click();
        $(".period-control__popup-day", 50).click();
        $(".period-control__popup-day", 60).click();
    }

    @And("^specify birthday of tourists: (\\d+).(\\d+).(\\d+)$")
    public void specifyBirthdayOfTourists(String day, String month, String year) {
        $(byText("Кто едет")).click();
        $("input[placeholder='дд.мм.гггг']").append(day + month + year);
    }

    @And("^wait until spinner disappears$")
    public void waitUntilSpinnerDisappears() {
        $(".spinner-container").waitUntil(disappears, 30000);
    }


    @Then("^element with tag \"([^\"]*)\" should exist$")
    public void elementWithTagShouldExist(String tag) {
        $(tag).shouldBe(visible);
    }

    @And("^press button with value \"([^\"]*)\" and it should be enabled$")
    public void pressButtonWithTextAndItShouldBeEnabled(String name) {
        $(byValue(name)).waitUntil(enabled, 2000).click();
    }

    @Then("^verify that page with url \"([^\"]*)\" is opened$")
    public void verifyThatPageWithUrlIsOpened(String verifyUrl) {
        sleep(8000);
        String currentUrl = url();
        assertThat("ERROR: page's URL doesn't contain " + verifyUrl, currentUrl, containsString(verifyUrl));
    }

    @And("^check \"Принимаю\"$")
    public void pressButtonWithId() {
        $("[for='user-agreement']").scrollTo().click();
    }
}
