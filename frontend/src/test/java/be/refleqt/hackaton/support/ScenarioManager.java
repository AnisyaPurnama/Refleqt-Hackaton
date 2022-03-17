package be.refleqt.hackaton.support;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ScenarioManager {

    protected static Scenario scenario;

    public static void setScenario(Scenario scenario) {
        ScenarioManager.scenario = scenario;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static void saveScreenshot() {
        byte[] screenshot = getScaledScreenshot(3);

        if (screenshot != null) {
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
    }

    private static byte[] getScaledScreenshot(int scaleFactor) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            ByteArrayInputStream in = new ByteArrayInputStream(screenshot);
            BufferedImage img = ImageIO.read(in);

            int height = img.getHeight() / scaleFactor;
            int width = img.getWidth() / scaleFactor;

            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);
            screenshot = buffer.toByteArray();

            return screenshot;
        } catch (Exception e) {
            return null;
        }
    }
}

