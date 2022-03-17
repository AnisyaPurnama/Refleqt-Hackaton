package be.refleqt.hackaton.pages;

import be.refleqt.hackaton.support.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FlightsPage {

    public WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));

    String flightButtonsXpath = "//input[@type='submit']";


    public void seeFlights(){
        Assert.assertNotNull(DriverManager.getDriver().findElements(By.xpath(flightButtonsXpath)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
