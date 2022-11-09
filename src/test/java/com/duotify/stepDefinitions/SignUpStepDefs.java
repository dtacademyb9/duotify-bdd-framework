package com.duotify.stepDefinitions;

import com.duotify.pages.HomePage;
import com.duotify.pages.WelcomePage;
import com.duotify.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SignUpStepDefs {

    String first;
    String last;
    @When("I enter valid random info to sign up")
    public void i_enter_valid_random_info_to_sign_up() {

         Faker faker = new Faker();

        HomePage homePage = new HomePage();

        homePage.username.sendKeys(faker.name().username());
        first = faker.name().firstName();
        homePage.firstName.sendKeys(first);
        last =  faker.name().lastName();
        homePage.lastName.sendKeys(last);
        String email  = faker.internet().emailAddress();
        homePage.email.sendKeys(email);
        homePage.email2.sendKeys(email);
        String pass = faker.internet().password();
        homePage.password.sendKeys(pass);
        homePage.password2.sendKeys(pass);
        homePage.signUpButton.click();



    }
    @Then("I should be able to see the same full name that I signed up with")
    public void i_should_be_able_to_see_the_same_full_name_that_i_signed_up_with() {

        Assert.assertEquals(first + " " +last, new WelcomePage().firstAndLast.getText());

    }


    @When("I enter invalid random info to sign up")
    public void i_enter_invalid_random_info_to_sign_up() {
        HomePage homePage = new HomePage();

        homePage.username.sendKeys("8743265234786437865784375678423678567843268765348257862348657823465678234758623465862437582346578346587243578634878527");

        homePage.firstName.sendKeys("!@#$%^&*()_(*&^%$#@$%^&*()*&^%$##$%^&*(*&^%$#$%^&*(*^%$#@%^&*(&^%$#@$%^&*(*&^%$#$%^&*&^$%^&*#$%^&*($%^&*&^%$#%^&*");

        homePage.lastName.sendKeys("!@#$%^&*()_(*&^%$#@$%^&*()*&^%$##$%^&*(*&^%$#$%^&*(*^%$#@%^&*(&^%$#@$%^&*(*&^%$#$%^&*&^$%^&*#$%^&*($%^&*&^%$#%^&*");

        homePage.email.sendKeys("cbvasgfcsaghsvghcahvs");
        homePage.email2.sendKeys("cbvasgfcsaghsvghcahvs");

        homePage.password.sendKeys("fdsgghgfjdsgjfgafgdsjgfjsdajgfgsdhgfdsjhgcvjhsdjhdvshsdvgsdgjhvsdgjhsdgvjhdgsjhvgsdhjvsgdhjgsdvjhsdgjhvhsdg");
        homePage.password2.sendKeys("fdsgghgfjdsgjfgafgdsjgfjsdajgfgsdhgfdsjhgcvjhsdjhdvshsdvgsdgjhvsdgjhsdgvjhdgsjhvgsdhjvsgdhjgsdvjhsdgjhvhsdg");
        homePage.signUpButton.click();
    }


    @When("I enter no info to sign up")
    public void i_enter_no_info_to_sign_up() {
        HomePage homePage = new HomePage();

        homePage.username.sendKeys("");

        homePage.firstName.sendKeys("");

        homePage.lastName.sendKeys("");

        homePage.email.sendKeys("");
        homePage.email2.sendKeys("");

        homePage.password.sendKeys("");
        homePage.password2.sendKeys("");
        homePage.signUpButton.click();
    }



}
