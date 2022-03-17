package be.refleqt.hackaton.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            if (System.getProperty("browser","chrome").equals("firefox")) {
                WebDriver localDriver = new FirefoxDriver();
            } else {
                WebDriver localDriver = new ChromeDriver();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }
}
