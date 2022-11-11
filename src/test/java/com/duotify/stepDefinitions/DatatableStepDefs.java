package com.duotify.stepDefinitions;

import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class DatatableStepDefs {

    @Then("I should see the following info")
    public void i_should_see_the_following_info(List<List<String>> dataTable) {

        System.out.println(dataTable);

        for (int i = 0; i < dataTable.size(); i++) {

            for (int j = 0; j < dataTable.get(i).size(); j++) {

                System.out.print(dataTable.get(i).get(j) + "\t");
            }

            System.out.println();

        }

        System.out.println(dataTable.get(1).get(1));





    }


    @Then("I should see the following info as List of Maps")
    public void i_should_see_the_following_info_as_list_of_maps(List<Map<String,String>> dataTable) {

        System.out.println(dataTable);

        System.out.println(dataTable.get(1).get("lastName"));

    }


    @Then("I should see the following info as Map")
    public void i_should_see_the_following_info_as_map(Map<String, String> dataTable) {

        System.out.println(dataTable);

        for ( Map.Entry entry : dataTable.entrySet()){
            System.out.println(entry);
        }

    }


    @Then("I should see the following info as Map of String as key and List as value")
    public void i_should_see_the_following_info_as_map_of_string_as_key_and_list_as_value(Map<String, List<Double>> dataTable) {

        System.out.println(dataTable);

        System.out.println(dataTable.get("KJFK").get(1));
    }

}
