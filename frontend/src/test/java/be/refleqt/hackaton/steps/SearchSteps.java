package be.refleqt.hackaton.steps;

import be.refleqt.hackaton.pages.FlightsPage;
import be.refleqt.hackaton.pages.SearchPage;
import be.refleqt.hackaton.support.ScenarioManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {


    @When("I select a departure city")
    public void iSelectADepartureCity() {
        new SearchPage().selectDepartureCity();
    }

    @And("I select a destination city")
    public void iSelectADestinationCity() {
        new SearchPage().selectDestinationCity();
        ScenarioManager.saveScreenshot();
    }

    @When("I click on find flights button")
    public void iClickOnFindFlightsButton() {
        new SearchPage().sumbit();
        ScenarioManager.saveScreenshot();
    }

    @Then("I see different flights for my search data")
    public void iSeeDifferentFlightsForMySearchData() {
        new FlightsPage().seeFlights();
        ScenarioManager.saveScreenshot();
    }
}
