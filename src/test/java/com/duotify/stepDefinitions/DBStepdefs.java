package com.duotify.stepDefinitions;

import com.duotify.pages.BrowsePage;
import com.duotify.utilities.DBUtils;
import com.duotify.utilities.SeleniumUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DBStepdefs {


    @Then("The albums on the welcome page should match the albums on the database table albums")
    public void the_albums_on_the_welcome_page_should_match_the_albums_on_the_database_table_albums() {

        BrowsePage browsePage = new BrowsePage();
        List<String> expectedList = SeleniumUtils.getElementsText(browsePage.albums);

        List<Map<String, Object>> queryResultListOfMaps =
                DBUtils.getQueryResultAsListOfMaps("select title  from albums");

        List<String> actualList = new ArrayList<>();

        for (Map<String, Object> eachRow : queryResultListOfMaps) {
            actualList.add((String)(eachRow.get("title")));
        }

        Collections.sort(expectedList);
        Collections.sort(actualList);
        Assert.assertEquals(expectedList, actualList);










    }

}
