package com.duotify.apiDemo;

import com.duotify.apiDemo.pojos.Place;
import com.duotify.apiDemo.pojos.VideoGame;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DeserializationDemo {


    static{
        baseURI = "http://ec2-3-128-25-7.us-east-2.compute.amazonaws.com:8080/app";
    }


    @Test
    public void deserializeString(){

      String prettyString =   given().
                header("Accept", "application/json").
                pathParam("videoGameId", 6).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                statusCode(200).extract().asPrettyString();

        System.out.println(prettyString);

        Assert.assertTrue(prettyString.contains("id"));





    }


    @Test
    public void deserializeAsMap(){

        Map map = given().
                header("Accept", "application/json").
                pathParam("videoGameId", 6).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                statusCode(200).extract().as(Map.class);// deserialize as a raw map


        System.out.println(map);

        Integer id =  (Integer)(map.get("id"));

        Assert.assertEquals(Integer.valueOf(6), id);



    }


    @Test
    public void deserializeAsMapTypeRef(){

        Map<String, Object> map = given().
                header("Accept", "application/json").
                pathParam("videoGameId", 6).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                statusCode(200).extract().as(new TypeRef<Map<String, Object>>() {
                });// deserialize as a specific type of map


        System.out.println(map);

        List<String> expected = List.of("id", "name", "releaseDate", "reviewScore", "category", "rating");


        List<String> actual = new ArrayList<>(map.keySet());


        System.out.println(expected);
        System.out.println(actual);

        Assert.assertEquals(expected, actual);


    }


    @Test
    public void deserializeAsList() {

//       List rawList = given().
//                header("Accept", "application/json").
//
//                when().log().all().
//                get("/videogames").
//                then().log().all().
//                statusCode(200).extract().as(List.class);
//
//
//        System.out.println(rawList);
//        
//        Object first = rawList.get(0);


        List<Map<String, Object>> listOfMaps = given().
                header("Accept", "application/json").

                when().log().all().
                get("/videogames").
                then().log().all().
                statusCode(200).extract().as(new TypeRef<List<Map<String, Object>>>() {
                });




        System.out.println(listOfMaps);

        Map<String, Object > first = listOfMaps.get(0);

        System.out.println(first.get("name"));

        List<String> names =  new ArrayList<>();
        for (Map<String, Object> each : listOfMaps) {

            names.add( (String)(each.get("name")) );
        }

        System.out.println(names);

        Assert.assertFalse("The list contains null value", names.contains(null));








    }


    @Test
    public void deserializeAsPOJO(){

      VideoGame videoGame =   given().
                header("Accept", "application/json").
                pathParam("videoGameId", 6).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                statusCode(200).extract().as(VideoGame.class);// deserialize as a VideoGame object

        System.out.println(videoGame);

        Assert.assertEquals("Doom", videoGame.getName());
        Assert.assertEquals("Mature", videoGame.getRating());

    }


    @Test
    public void deserializeAsPOJO2(){

        baseURI = "https://maps.googleapis.com";
        basePath = "/maps/api/place";

        Place place = given().
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                queryParam("fields", "formatted_address,name,rating").

                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).extract().as(Place.class);


          Assert.assertEquals("2735 Hartland Rd Suite 302, Falls Church, VA 22043, United States",
                              place.getCandidates().get(0).getFormatted_address());

        Assert.assertEquals("OK",
                place.getStatus());



    }



    @Test
    public void deserializeAsListOfPOJOs() {

//


     List<VideoGame> videoGameList =     given().
                header("Accept", "application/json").

                when().log().all().
                get("/videogames").
                then().log().all().
                statusCode(200).extract().as(new TypeRef<List<VideoGame>>() {
                });


        for (VideoGame videoGame : videoGameList) {
            System.out.println(videoGame.getName());
        }








    }

    @Test
    public void deserializeAsFile() throws IOException {

//


         InputStream response =    given().
                header("Accept", "application/json").

                when().log().all().
                get("/videogames").
                then().log().all().
                statusCode(200).extract().asInputStream(); // extracts the response into inputstream obj


         // Copy the inputstream content to a json file
        FileUtils.copyInputStreamToFile(response, new File("src/test/java/com/duotify/apiDemo/jsonFiles/response.json"));











    }






}
