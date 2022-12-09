package com.duotify.apiDemo;

import com.duotify.apiDemo.pojos.VideoGame;
import com.duotify.apiDemo.pojos.VideoGameGenerated;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SerializationDemo {



    static{
        baseURI = "http://ec2-3-128-25-7.us-east-2.compute.amazonaws.com:8080/app";
    }


    @Test
    public void stringSerialization(){

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
    public void fileSerialization(){



        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body(new File("src/test/java/com/duotify/apiDemo/payloadPost.json")).  // relative path

                when().log().all().
                post("/videogames").

                then().log().all().
                statusCode(200).
                body("status", is("Record Added Successfully")).
                header("Content-Length", "39");

    }

    @Test
    public void mapSerialization2(){




        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                body(Map.of("id", new Random().nextInt(20000),
                         "name",  "Half Life",
                        "releaseDate", "2022-12-01T23:00:17.545Z",
                        "reviewScore", 100,
                        "category", "Adventure",
                        "rating", "General"
                         )).

                when().log().all().
                post("/videogames").

                then().log().all().
                statusCode(200).
                body("status", is("Record Added Successfully")).
                header("Content-Length", "39");

    }

    @Test
    public void POJOSerialization(){


         VideoGame videoGame = new VideoGame("Half Life", "General", 99, new Random().nextInt(300000), "FPS", "2020-03-04");

        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
//                body(videoGame, ObjectMapperType.GSON).  // explicitly specify the Serializer library
                body(videoGame).

                when().log().all().
                post("/videogames").

                then().log().all().
                statusCode(200).
                body("status", is("Record Added Successfully")).
                header("Content-Length", "39");

    }

    @Test
    public void POJOSerializationGeneratedPOJO(){


        VideoGameGenerated videoGameGenerated = new VideoGameGenerated(1000+ new Random().nextInt(100000),
                "Half - Life Alyx",
                "2022-08-05",
                 23,
                "FPS",
                 "General"
                 );
        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
//                body(videoGame, ObjectMapperType.GSON).  // explicitly specify the Serializer library
        body(videoGameGenerated).

                when().log().all().
                post("/videogames").

                then().log().all().
                statusCode(200).
                body("status", is("Record Added Successfully")).
                header("Content-Length", "39");

    }
}
