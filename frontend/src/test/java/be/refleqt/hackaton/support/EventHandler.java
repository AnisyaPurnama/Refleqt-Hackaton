package be.refleqt.hackaton.support;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class EventHandler implements WebDriverListener {

    @Override
    public void afterClick(WebElement element) {
//        WebDriverListener.super.afterClick(element);
        ScenarioManager.saveScreenshot();
    }
}