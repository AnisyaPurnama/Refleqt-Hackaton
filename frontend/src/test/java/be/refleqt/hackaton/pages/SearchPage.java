package be.refleqt.hackaton.pages;

import be.refleqt.hackaton.support.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    //todo:tests aanvullen + CI/CD + parrallelisatie
    //todo: momenteel werkt de findby niet, chrome bolt en de website wordt getoond
    //todo: screenshots zitten er normaal al in, moet misschien nog iets meer uitgewerkt worden, geen abstractpage class momenteel met aparte webdriverwaits
    public WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));

    String departureCityxpath = "//select[@name='fromPort']";

    String destinationCityXpath = "//select[@name='toPort']";

    String submitXpath = "//input[@type='submit']";

    public void selectDepartureCity() {
        Select departure = new Select(DriverManager.getDriver().findElement(By.xpath(departureCityxpath)));
        departure.selectByIndex(2);
    }

    public void selectDestinationCity() {
        Select departure = new Select(DriverManager.getDriver().findElement(By.xpath(destinationCityXpath)));
        departure.selectByIndex(2);
    }

    public void sumbit() {
        DriverManager.getDriver().findElement(By.xpath(submitXpath)).click();
    }
}
