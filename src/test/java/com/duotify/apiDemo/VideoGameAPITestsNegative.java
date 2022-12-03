package com.duotify.apiDemo;


import io.cucumber.java.BeforeAll;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VideoGameAPITestsNegative {


    @BeforeClass
    public static void beforeClass(){
        baseURI = "http://ec2-3-128-25-7.us-east-2.compute.amazonaws.com:8080/app";
    }



    @Test
    public void postVideoGameNegative(){

        int existingID = 1;

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"id\": "+existingID+",\n" +
                        "  \"name\": \"Half Life\",\n" +
                        "  \"releaseDate\": \"2022-12-01T23:00:17.545Z\",\n" +
                        "  \"reviewScore\": 100,\n" +
                        "  \"category\": \"Adventure\",\n" +
                        "  \"rating\": \"General\"\n" +
                        "}").

                when().log().all().
                post("/videogames").

                then().log().all().
                statusCode(not(200)); // creation of the videogame should not be successful

    }


    @Test
    public void postVideoGameNegativeByChangingMethodType(){

        int id = 100+ new Random().nextInt(10000);

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"id\": "+id+",\n" +
                        "  \"name\": \"Half Life\",\n" +
                        "  \"releaseDate\": \"2022-12-01T23:00:17.545Z\",\n" +
                        "  \"reviewScore\": 100,\n" +
                        "  \"category\": \"Adventure\",\n" +
                        "  \"rating\": \"General\"\n" +
                        "}").

                when().log().all().
                delete("/videogames").

                then().log().all().
                statusCode(405). // status code should be 405 method not allowed
                header("Allow" , is("POST,GET,OPTIONS"));
    }


    @Test
    public void postVideoGameNegativeInvalidHeaderValue(){

        int id = 100+ new Random().nextInt(10000);

        given().
                header("Accept", "application/vnd.api+json").  // invalid header value
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"id\": "+id+",\n" +
                        "  \"name\": \"Half Life\",\n" +
                        "  \"releaseDate\": \"2022-12-01T23:00:17.545Z\",\n" +
                        "  \"reviewScore\": 100,\n" +
                        "  \"category\": \"Adventure\",\n" +
                        "  \"rating\": \"General\"\n" +
                        "}").

                when().log().all().
                post("/videogames").

                then().log().all().
                statusCode(406); // status code should be 406 Not Acceptable (endpoint cannot return the payload in the client's expected format)

    }




}
