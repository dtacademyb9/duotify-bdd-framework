package com.duotify.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class apiDemoStepDefs {
    RequestSpecification requestSpecification;
    Response response;

    @Given("the base URI is initialized and header is {string} {string}")
    public void the_base_uri_is_initialized_and_header_is(String key, String value) {

        baseURI = "https://api.github.com";

        requestSpecification = given().
                header(key, value);

    }
    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {

        response = requestSpecification.
                when().log().all().
                get(endpoint);

    }
    @Then("the status code should be {int} and {string} value in the body should be  {string}")
    public void the_status_code_should_be_and_value_should_be(Integer statusCode, String key, String value) {
        response.
                then().log().all().
                assertThat().
                statusCode(equalTo(statusCode)).
                body(key, equalTo(value));

    }
}
