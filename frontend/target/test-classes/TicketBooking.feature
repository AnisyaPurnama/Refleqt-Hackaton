Feature: I book tickets for travel

  Background:
    Given I go to the homepage

    Scenario: I select departure/destination city and find flights
      When I select a departure city
      And I select a destination city
      When I click on find flights button
      Then I see different flights for my search data