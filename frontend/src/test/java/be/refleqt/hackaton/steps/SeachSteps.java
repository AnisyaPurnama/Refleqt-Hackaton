package be.refleqt.hackaton.steps;

import be.refleqt.hackaton.pages.SearchPage;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class SeachSteps {


    @When("I select a departure city")
    public void iSelectADepartureCity() {
        new SearchPage().selectDepartureCity();
    }
}
