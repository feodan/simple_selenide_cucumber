package ru.riskmarket.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class MyStepdefs {
    @Given("^open riskmarket\\.ru$")
    public void openRiskmarketRu() {
        open("http://riskmarket.ru");
    }

    @When("^press button with text \"([^\"]*)\"$")
    public void pressButtonWithText(String button) {
        $(byText(button)).waitUntil(appear, 15000).click();
    }

    @And("^type to input with name \"([^\"]*)\" text: \"([^\"]*)\"$")
    public void typeToInputWithNameText(String input, String text) {
        sleep(1000);
        $(byName(input)).sendKeys(text);
    }

    @And("^select countries: (.*)$")
    public void selectCountries(List<String> countries) {
        for (String str : countries) {
            $("#countryInput").sendKeys(str);
            $("#countryInput").pressEnter();
        }
    }
}
