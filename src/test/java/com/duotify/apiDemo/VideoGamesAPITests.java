package com.duotify.apiDemo;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class VideoGamesAPITests {




    static{
        baseURI = "http://ec2-3-128-25-7.us-east-2.compute.amazonaws.com:8080/app";
    }


    @Test
    public void getVideoGames(){

        given().
                header("Accept", "application/json").
                when().log().all().
                get("/videogames").
                then().log().all().
                statusCode(200);


    }

    @Test
    public void getVideoGameById(){

        given().
                header("Accept", "application/json").
                pathParam("videoGameId", 6).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                statusCode(200).
                body("id", equalTo(6) ).  //   body(JsonPath Expression, Matcher method)
                body("name", equalTo("Doom") ).
                body("releaseDate", equalTo("1993-02-18") ).
                body("reviewScore", equalTo(81) ).
                body("category", equalTo("Shooter") ).
                body("rating", equalTo("Mature") ).
//                header("Content-Type", "application/json").
//                header("Content-Length", "105").
                headers(Map.of("Content-Type", "application/json","Content-Length", "105" )).
                time(lessThan(1000L));





    }


    @Test
    public void postVideoGame(){

        int id =  20 + new Random().nextInt(1000);

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
                post("/videogames").

                then().log().all().
                statusCode(200).
                body("status", is("Record Added Successfully")).
                header("Content-Length", "39");

    }



    @Test
    public void putVideoGame(){


        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"id\": 11,\n" +
                        "  \"name\": \"Half Life\",\n" +
                        "  \"releaseDate\": \"2022-12-01T23:00:17.545Z\",\n" +
                        "  \"reviewScore\": 100,\n" +
                        "  \"category\": \"Adventure\",\n" +
                        "  \"rating\": \"General\"\n" +
                        "}").
                pathParam("videoGameId", 11).

                when().log().all().
                put("/videogames/{videoGameId}").

                then().log().all().
                statusCode(200).
                body("id", is(11));


    }


    @Test
    public void deleteVideoGame(){


        given().
                header("Accept", "application/json").


                pathParam("videoGameId", 11).

                when().log().all().
                delete("/videogames/{videoGameId}").

                then().log().all().
                statusCode(200).
                body("status", is("Record Deleted Successfully"));


    }


}
