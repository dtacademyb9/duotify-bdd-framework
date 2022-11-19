package com.duotify.stepDefinitions;

import com.duotify.pages.BrowsePage;
import com.duotify.utilities.DBUtils;
import com.duotify.utilities.SeleniumUtils;
import io.cucumber.java.en.Then;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.sql.SQLException;
import java.time.LocalDate;
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

    @Then("I retrieve the following information from the database and verify the data mapping")
    public void i_retrieve_the_information_about_the_same_user_from_the_database_and_verify_the_data_mapping(List<Map<String, String>> data) throws SQLException {

        Map<String, String> map = data.get(0);

        String username = map.get("username");
        String first = map.get("first");
        String last = map.get("last");
        String email = map.get("email");
        String password = map.get("password");


        String query = "select * from users where username='"+username+"'";
        List<Map<String, Object>> dbresult = DBUtils.getQueryResultAsListOfMaps(query);

        Map<String, Object> userInfoRow = dbresult.get(0);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(userInfoRow.get("username")).isEqualTo(username);
        softAssertions.assertThat(userInfoRow.get("firstName")).isEqualTo(first);
        softAssertions.assertThat(userInfoRow.get("lastName")).isEqualTo(last);
        softAssertions.assertThat(userInfoRow.get("email")).isEqualTo(email);
        String passwordMD5 = DigestUtils.md5Hex(password);
        softAssertions.assertThat(userInfoRow.get("password")).isEqualTo(passwordMD5);
//        softAssertions.assertThat(((String)(userInfoRow.get("signUpDate"))).split(" ")[0]).isEqualTo(LocalDate.now().toString());




        DBUtils.executeUpdate("DELETE from users where username='"+username+"'");

        softAssertions.assertAll();





    }

}
