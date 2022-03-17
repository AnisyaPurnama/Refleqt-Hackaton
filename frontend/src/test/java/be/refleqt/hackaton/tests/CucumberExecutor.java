package be.refleqt.hackaton.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "be.refleqt.hackaton.steps",
        plugin = {"progress", "json:target/cucumber/json/results.json", "html:target/cucumber/html/results.html",},
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class CucumberExecutor {

}
