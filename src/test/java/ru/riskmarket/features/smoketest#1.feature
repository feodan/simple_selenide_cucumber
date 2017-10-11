Feature: smoke test #1, go through the service to Yandex-pay-page https://habrahabr.ru/post/283422/

  Scenario: go through the service to button "Купить"

    #actions at first page
    Given open riskmarket.ru
    When press button with text "Вход в кабинет"
    And type to input with name "userName" text: "riskmarket.testoviy2016@yandex.ru"
    And type to input with name "password" text: "l0dcfJMB"
    And press element with value "Войти"
    And wait until login frame disappears
    And select countries: Шенген, Финляндия, Китай
    And specify dates of journey, any available dates
    And specify birthday of tourists: 08.12.1945
    And press button with text "Рассчитать полис"
    #actions at second page
    And wait until spinner disappears
    Then element with tag "search-result-item" should exist

  Scenario: go through service to yandex pay-page

    Given press button with text "КУПИТЬ"
    #actions at third page
    When type to input with name "lastName" text: "TESTOVIY"
    And type to input with name "firstName" text: "TEST"
    And press element with text "Принимаю"
    And press element with value "Оформить" and it should be enabled
    And press button with text "Оплатить"
    Then verify that page with url "https://money.yandex.ru/cashdesk" is opened
