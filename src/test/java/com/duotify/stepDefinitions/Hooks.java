package com.duotify.stepDefinitions;

import com.duotify.utilities.DBUtils;
import com.duotify.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

//    @BeforeAll
//    public static void setupSuite(){
//        System.out.println("Setup  actions that need to be done once before all scenarios are run");
//    }
//
//    @AfterAll
//    public static void tearDownSuite(){
//        System.out.println("Tear down  actions that need to be done after all scenarios have run");
//    }

//    @BeforeStep
//    public void beforeStepHook(){
//        System.out.println("Before each step");
//    }
//
//    @AfterStep
//    public void afterStepHook(){
//        System.out.println("After each step");
//    }

    @Before ("not @db_only")
    public void setupScenario(){

        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getDriver().manage().window().maximize();
        DBUtils.createConnection();

    }

    @Before ("@db_only")
    public void setupDb(){
        DBUtils.createConnection();
    }
//
    @After ("@db_only")
    public void tearDb(){
        DBUtils.close();
    }


    @After ("not @db_only")
    public void tearDownScenario(Scenario scenario){

        if(scenario.isFailed()) {
            byte[] screenshotFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotFile, "image/png", "screenshotOfFailure");


        }

        Driver.quitDriver();
        DBUtils.close();
    }


}
