package com.duotify.stepDefinitions;

import com.duotify.pages.HomePage;
import com.duotify.utilities.ConfigReader;
import com.duotify.utilities.DBUtils;
import com.duotify.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.SQLException;

public class LoginStepDefs {

    @When("I enter valid login credentials")
    public void i_enter_valid_login_credentials() {
        HomePage homePage  = new HomePage();
        homePage.loginUsername.sendKeys(ConfigReader.getProperty("username"));
        homePage.loginPassword.sendKeys(ConfigReader.getProperty("password"));
        homePage.loginButton.click();
    }
    @Then("I should be able to login and land on Welcome page")
    public void i_should_be_able_to_login_and_land_on_welcome_page() throws SQLException {
        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
//        DBUtils.executeUpdate("DELETE from users where username='tom.cruise'");
    }

    @When("I enter invalid login credentials")
    public void i_enter_invalid_login_credentials() {
        HomePage homePage  = new HomePage();
        homePage.loginUsername.sendKeys("username");
        homePage.loginPassword.sendKeys("password");
        homePage.loginButton.click();
    }

    @Then("I should not be able to login")
    public void i_should_not_be_able_to_login() {
        Assert.assertNotEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
    }


    @When("^I enter no login credentials$")
    public void i_enter_no_login_credentials() {
        HomePage homePage  = new HomePage();
        homePage.loginUsername.sendKeys("");
        homePage.loginPassword.sendKeys("");
        homePage.loginButton.click();
    }


    @When("I enter login credentials as {string} and {string}")
    public void iEnterLoginCredentialsAsAnd(String username, String password) {
        HomePage homePage  = new HomePage();
        homePage.loginUsername.sendKeys(username);
        homePage.loginPassword.sendKeys(password);
        homePage.loginButton.click();
    }
}
