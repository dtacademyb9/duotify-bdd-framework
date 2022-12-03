package com.duotify.apiDemo;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPathDemo {





    @Test
    public void testGoogleMapsApi(){

        baseURI  = "https://maps.googleapis.com";
        basePath = "/maps/api/place";

      JsonPath responseAsJsonPath =   given().
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
}
