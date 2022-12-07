package com.duotify.apiDemo;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPathDemo {


    @Test
    public void testGoogleMapsApi() {

        baseURI = "https://maps.googleapis.com";
        basePath = "/maps/api/place";

        JsonPath responseAsJsonPath = given().
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").

                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("candidates[0].formatted_address", equalTo("2735 Hartland Rd Suite 302, Falls Church, VA 22043, United States")).
                extract().jsonPath();

        // JsonPath expressions can be used directly in the body() method

        // JsonPath expressions can also be used when the response is extracted as JsonPath object.


        String address = responseAsJsonPath.getString("candidates[0].formatted_address");
        System.out.println("The address is " + address);

        System.out.println(responseAsJsonPath.getString("candidates[0].geometry.location.lat"));
        System.out.println(responseAsJsonPath.getString("candidates[0].name"));
        System.out.println(responseAsJsonPath.getString("status"));


    }


    @Test
    public void testGoogleMapsApi2() {

        baseURI = "https://maps.googleapis.com";
        basePath = "/maps/api/place";

        JsonPath jsonPath = given().
                queryParam("input", "Taco Bamba").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").

                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                // JsonPath expressions can be used directly in the body() method
                        body("candidates[0].formatted_address", equalTo("2190 Pimmit Dr, Falls Church, VA 22043, United States")).
                body("candidates[0].name", equalTo("Taco Bamba")).
                body("candidates[0].rating", equalTo(4.7F)).extract().jsonPath();


        // JsonPath expressions can also be used when the response is extracted as JsonPath object.

        Double latitude = jsonPath.getDouble("candidates[0].geometry.viewport.northeast.lat");

        System.out.println(latitude);
        Assert.assertEquals(Double.valueOf(38.90359), latitude);

        System.out.println(jsonPath.getInt("candidates[0].photos[0].height"));
        System.out.println(jsonPath.getList("candidates[0].photos[0].html_attributions"));
        List<String> list = jsonPath.getList("candidates[0].photos[0].html_attributions");

        Map<String, Double> map = jsonPath.getMap("candidates[0].geometry.location");

        System.out.println(map);


    }


    @Test
    public void testgitHubApi() {


        baseURI = "https://api.github.com";
//

        JsonPath jsonPath = given().
                header("Accept", "application/json").
//                header("Authorization", "token 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").
        when().log().all().
                get("/users").
                then().log().all().
                assertThat().
                body("size()", is(30)).  // verify the size of the list is 30
                        statusCode(equalTo(200)).extract().jsonPath();


        System.out.println(jsonPath.getMap("[0]")); // if the root element is an array with no name, simply use the index itself to access its elements
        System.out.println(jsonPath.getString("[0].login"));
        System.out.println(jsonPath.getList("login"));
        List<String> login = jsonPath.getList("login");

        Assert.assertTrue(login.contains("kevinclark"));


        List list = jsonPath.getList("");  // returns the root list

        System.out.println(list.size());

        // find{it.} -> matches the first occurence
        Object o = jsonPath.get("find{it.login='mojombo'}");  // returns the object from the list whose login value is mojombo
////
        System.out.println(o);

        Object o1 = jsonPath.get("find{it.login == 'mojombo'}.id");  // returns the id of the object from the list whose login value is mojombo
////
        System.out.println(o1);

        Object o2 = jsonPath.get("findAll{it.type == 'Organization'}.login");  // returns the login names of the users whose type is Organization
////
        System.out.println(o2);


        Object o3 = jsonPath.get("findAll{it.login.startsWith('d')}.login");  // returns the login names of the users whose type is Organization
////
        System.out.println(o3);


    }


    @Test
    public void testgitHubHamcrestMatchers() {


        baseURI = "https://api.github.com";
//

        given().
                header("Accept", "application/json").
//                header("Authorization", "token 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").
        when().log().all().
                get("/users").
                then().log().all().
                assertThat().
                // list related Hamscrest matcher methods
                body("findAll{it.type == 'User'}.login", hasItem("defunkt")).
                body("findAll{it.type == 'User'}.login", not(hasItem("hello"))).
                body("findAll{it.type == 'User'}.login", hasItems("defunkt", "bmizerany")).
                body("findAll{it.type == 'User'}.login", not(empty())).
                body("findAll{it.type == 'User'}.login", hasSize(29)).
                // Map related Hamscrest matcher methods
                body("[0]", hasKey("login")).
                body("[0]", hasValue("MDQ6VXNlcjE=")).
                body("[0]", hasEntry("login", "mojombo"))

                ;




    }


}
