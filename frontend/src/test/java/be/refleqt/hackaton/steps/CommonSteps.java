package be.refleqt.hackaton.steps;

import be.refleqt.hackaton.support.DriverManager;
import be.refleqt.hackaton.support.ScenarioManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;


public class CommonSteps {

    @Before
    public void beforeScenario(Scenario scenario) {
        ScenarioManager.setScenario(scenario);
        DriverManager.getDriver();
    }

    @After
    public void afterScenario() {
        DriverManager.quitDriver();
    }

    @Given("I go to the homepage")
    public void iGoToTheHomepage() {
        DriverManager.getDriver().get("https://blazedemo.com/");
    }
}
