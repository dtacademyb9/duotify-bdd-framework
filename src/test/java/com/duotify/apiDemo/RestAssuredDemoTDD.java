package com.duotify.apiDemo;


import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemoTDD {



    @Test
    public void testOne(){

        // Set the BASE URL

        baseURI = "https://api.github.com";
//        basePath = "/users"; //common path after the base URL

        // given() -> request specifications
        // when() -> request type and endpoint should be given
        // then() -> assertions


        given().
                header("Accept", "application/json").
//                header("Authorization", "token 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").
        when().log().all().
               get("/users/dtacademyb9").
        then().log().all().
                assertThat().
                statusCode(equalTo(200));

    }





}
