package com.duotify.apiDemo;

import com.duotify.utilities.ConfigReader;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.util.Base64;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationSchemes {




    @Test
    public void apiKey() {


        // API keys are unique strings that have to be obtained by the client after registering with the API provider
        // They are usually sent as a query parameter, or request header parameter


        baseURI = "https://maps.googleapis.com";
        basePath = "/maps/api/place";

        given().
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
//                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8"). // api keys as query param
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").

                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200);


    }


    @Test
    public void basicAuth() {


           baseURI = "https://postman-echo.com";


           // Basic auth format -> Basic <username:password in base 64 format>

        String base64Encoded = Base64.getEncoder().encodeToString("postman:password".getBytes());

        System.out.println(base64Encoded);

        given().
//                   auth().
//                   basic("postman", "password").
                  header("Authorization", "Basic " + base64Encoded).

          when().log().all().
                   get("/basic-auth").
          then().log().all().
                   statusCode(200);


        //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



    }


    @Test
    public void bearerTokenAuth() {

        baseURI = "https://api.github.com";

        given().
                header("Authorization" , "Bearer " + ConfigReader.getProperty("bearer_token")).
                header("Accept", "application/vnd.github+json").
                header("X-GitHub-Api-Version", "2022-11-28").
                body("{\n" +
                        "    \"location\" : \"Virginia, VA\",\n" +
                        "    \"company\" : \"GitHub Inc\",\n" +
                        "    \"bio\" : \"In the beginning there was a test...\"\n" +
                        "}").
        when().log().all().
                patch("/user").
        then().  log().all().
                statusCode(200).
                body("company", equalTo("GitHub Inc"));


    }


    @Test
    public void bearerTokenJWT() {

        // post login

        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";


        JsonPath jsonPath = given().
                body("{\n" +
                        "    \"email\" : \"revifon371@pamaweb.com\",\n" +
                        "    \"password\" : \"Automation1234!\"\n" +
                        "}").
                when().log().all().
                post("/login.php").
                then().log().all().
                statusCode(200).extract().jsonPath();

        String JWToken = jsonPath.getString("token");

        // get mortgage

        given().
                header("Authorization", JWToken).
                when().log().all().
                get("/getmortagage.php").
                then().log().all().
                statusCode(200);





    }

}
