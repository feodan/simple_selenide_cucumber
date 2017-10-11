package ru.riskmarket.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;

public class MyStepdefs {
    @Given("^open riskmarket\\.ru$")
    public void openRiskmarketRu() {
        System.setProperty("webdriver.chrome.driver", "D:/dev/WebDrivers/chromedriver.exe");
        Configuration.browser = "chrome";
        open("http://riskmarket.ru");
    }

    @When("^press button with text \"([^\"]*)\"$")
    public void pressButtonWithText(String button) {
        $(byText(button)).waitUntil(appear, 15000).click();
    }

    @And("^type to input with name \"([^\"]*)\" text: \"([^\"]*)\"$")
    public void typeToInputWithNameText(String input, String text) {
        sleep(500);
        $(byName(input)).sendKeys(text);
    }

    @And("^select countries: (.*)$")
    public void selectCountries(List<String> countries) {
        for (String str : countries) {
            $(".countries-widget__wrapper input").sendKeys(str);
            sleep(500);
            $(".countries-widget__wrapper input").sendKeys(Keys.RETURN);
//            Selenide.actions().sendKeys(Keys.RETURN);
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
        $$(".period-control__popup-day").get(50).click();
        $$(".period-control__popup-day").get(60).click();
    }

    @And("^specify birthday of tourists: (\\d+).(\\d+).(\\d+)$")
    public void specifyBirthdayOfTourists(String  day, String  month, String  year) {
        $(byText("Кто едет")).click();
        $("input[placeholder='дд.мм.гггг']").sendKeys( day + month + year);
    }

    @And("^wait until spinner disappears$")
    public void waitUntilSpinnerDisappears()  {
        $(".spinner-container").waitUntil(disappears, 30000);
    }


    @Then("^element with tag \"([^\"]*)\" should exist$")
    public void elementWithTagShouldExist(String tag) {
        $(tag).waitUntil(appear, 15000).shouldBe(visible);
    }

    @And("^press button with value \"([^\"]*)\" and it should be enabled$")
    public void pressButtonWithTextAndItShouldBeEnabled(String name) {
        $(byValue(name)).waitUntil(enabled, 2000).click();
    }

    @Then("^verify that page with url \"([^\"]*)\" is opened$")
    public void verifyThatPageWithUrlIsOpened(String verifyUrl)  {
        sleep(10000);
        String currentUrl = url();
        assertThat("ERROR: page's URL doesn't contain " + verifyUrl, currentUrl, containsString(verifyUrl));
    }

    @And("^check \"Принимаю\"$")
    public void pressButtonWithId() {
        $("[for='user-agreement']").scrollTo().click();
    }
}
