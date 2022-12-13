package com.duotify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;





@CucumberOptions(
        tags = "@smoke",
                              //"@smoke and @noCredentials" -> scenarios tagged with both @smoke and @noCredentials
                              //"@smoke or @noCredentials" -> scenarios tagged with either @smoke or @noCredentials
                            //"not @login" -> scenarios tagged with either @smoke or @noCredentials
                            // (@login or @noCredentials) and @appHealthCheck -> -> scenarios tagged with either @login and @appHealthCheck at the same time or @noCredentials and @appHealthCheck at the same time
        features = "src/test/resources", // path to the feature files
        glue = "com/duotify/stepDefinitions", // path to the step definition classes
        stepNotifications = true,
        plugin = {
                "pretty",  // provides more info about the test run on the console
                "html:target/cucumber-built-in-report/report.html",  // generates a built-in cucumber html report
                "json:target/jsonReport.json",
                 "rerun:target/failedScenarioList.txt"
        }
//        , dryRun = true  // to generate step definition snippets without running the test


)
@RunWith(Cucumber.class)
public class CucumberRunner {
}
