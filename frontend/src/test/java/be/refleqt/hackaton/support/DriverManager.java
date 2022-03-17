package be.refleqt.hackaton.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriver localDriver;
            if (System.getProperty("browser", "chrome").equals("firefox")) {
                localDriver = new FirefoxDriver();
            } else {
                localDriver = new ChromeDriver();
            }
            driver = new EventFiringDecorator(new EventHandler()).decorate(localDriver);
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }
}
