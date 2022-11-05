package com.duotify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@CucumberOptions(
        tags = "(@login or @noCredentials) and @appHealthCheck",   //"@smoke and @noCredentials" -> scenarios tagged with both @smoke and @noCredentials
                              //"@smoke or @noCredentials" -> scenarios tagged with either @smoke or @noCredentials
                            //"not @login" -> scenarios tagged with either @smoke or @noCredentials
                            // (@login or @noCredentials) and @appHealthCheck -> -> scenarios tagged with either @login and @appHealthCheck at the same time or @noCredentials and @appHealthCheck at the same time
        features = "src/test/resources", // path to the feature files
        glue = "com/duotify/stepDefinitions" // path to the step definition classes
//        , dryRun = true  // to generate step definition snippets without running the test

)
@RunWith(Cucumber.class)
public class CucumberRunner {
}
