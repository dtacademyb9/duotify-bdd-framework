package com.duotify.apiDemo;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemoBDD {

    RequestSpecification requestSpecification;
    Response response;

    @Test
    public void givenStepDefinition(){

        baseURI = "https://api.github.com";

       requestSpecification = given().
                                            header("Accept", "application/json");
//

    }

   @Test
    public void whenStepDefinition(){
       response = requestSpecification.
                when().log().all().
                get("/users/dtacademyb9");
    }




     @Test
    public void thenStepDefinition(){
        response.
                then().log().all().
                assertThat().
                statusCode(equalTo(200));
    }







}
