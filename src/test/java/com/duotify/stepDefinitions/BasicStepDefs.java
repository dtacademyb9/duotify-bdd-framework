package com.duotify.stepDefinitions;

import com.duotify.pages.BrowsePage;
import com.duotify.pages.HomePage;
import com.duotify.pages.UserDetailsPage;
import com.duotify.utilities.ConfigReader;
import com.duotify.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BasicStepDefs {


    @When("I update the user email to {string}")
    public void i_update_the_user_email_to(String email) {

        new BrowsePage().userInfoLink.click();
        new BrowsePage().userDetailsButton.click();

        UserDetailsPage userDetailsPage = new UserDetailsPage();
        userDetailsPage.emailField.clear();
        userDetailsPage.emailField.sendKeys(email);
        userDetailsPage.saveButton.click();

        Assert.assertTrue(userDetailsPage.successMessage.isDisplayed());


    }

    @When("I navigate to the homepage")
    public void i_navigate_to_the_homepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }


    @Then("The homepage url should be correct")
    public void the_homepage_url_should_be_correct() {
        Assert.assertEquals(ConfigReader.getProperty("url"), Driver.getDriver().getCurrentUrl());
    }
    @Then("The title should be Welcome to Duotify!")
    public void the_title_should_be_welcome_to_duotify() {
        Assert.assertEquals("Welcome to Duotify!", Driver.getDriver().getTitle());

    }


    @When("I click on sign up link")
    public void dscdsh() {
        new HomePage().signUpLink.click();
    }
    @Then("I should see Create your free account text")
    public void i_should_see_create_your_free_account_text() {
            Assert.assertTrue(new HomePage().createAccountText.isDisplayed());

    }
    @Then("I should see sign up button")
    public void i_should_see_sign_up_button() {
        Assert.assertTrue(new HomePage().signUpButton.isDisplayed());

    }



}
