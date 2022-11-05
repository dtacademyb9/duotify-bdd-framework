package com.duotify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@CucumberOptions(
        tags = "@test",
        features = "src/test/resources", // path to the feature files
        glue = "com/duotify/stepDefinitions" // path to the step definition classes
//        , dryRun = true  // to generate step definition snippet without running the test

)
@RunWith(Cucumber.class)
public class CucumberRunner {
}
