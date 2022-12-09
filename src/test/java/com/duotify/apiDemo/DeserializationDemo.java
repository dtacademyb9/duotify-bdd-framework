package com.duotify.apiDemo;

import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
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


}
