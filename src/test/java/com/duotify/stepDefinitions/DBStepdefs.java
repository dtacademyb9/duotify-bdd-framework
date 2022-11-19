package com.duotify.stepDefinitions;

import com.duotify.pages.BrowsePage;
import com.duotify.pages.YourMusicPage;
import com.duotify.utilities.DBUtils;
import com.duotify.utilities.Driver;
import com.duotify.utilities.SeleniumUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        softAssertions.assertThat(((String)(userInfoRow.get("email"))).toLowerCase()).isEqualTo(email);
        String passwordMD5 = DigestUtils.md5Hex(password);
        softAssertions.assertThat(userInfoRow.get("password")).isEqualTo(passwordMD5);


        LocalDateTime signUpDate = (LocalDateTime)(userInfoRow.get("signUpDate"));
        softAssertions.assertThat(signUpDate.toLocalDate()).isEqualTo(LocalDate.now());




        DBUtils.executeUpdate("DELETE from users where username='"+username+"'");

        softAssertions.assertAll();





    }

    List<String>  expectedPlaylist;
    @Then("I click on your music and create the following playlists")
    public void i_click_on_your_music_and_create_the_following_playlists(List<String> dataTable) throws InterruptedException {

        expectedPlaylist =  dataTable;
        BrowsePage browsePage = new BrowsePage();
        browsePage.yourMusicLink.click();
        YourMusicPage yourMusicPage = new YourMusicPage();

        for (String name : dataTable) {
            yourMusicPage.newPlaylistButton.click();
            Driver.getDriver().switchTo().alert().sendKeys(name);
            Driver.getDriver().switchTo().alert().accept();
            Thread.sleep(1000);
        }


    }
    @Then("The database table should contain the same playlist names for user {string}")
    public void the_database_table_should_contain_the_same_playlist_names(String username) throws SQLException {

        String query = "select * from playlists where owner='"+username+"'";
        List<Map<String, Object>> listDB = DBUtils.getQueryResultAsListOfMaps(query);

        List<String> actualPlaylist =  new ArrayList<>();

        for (Map<String, Object> map : listDB) {
            actualPlaylist.add((String)(map.get("name")));
        }


        Assert.assertEquals(expectedPlaylist, actualPlaylist);


        DBUtils.executeUpdate("DELETE from playlists where owner='"+username+"'");

    }


    @Given("I create a new user with the following information in the database")
    public void i_create_a_new_user_with_the_following_information_in_the_database(List<Map<String, String>> dataTable) throws SQLException {

        Map<String, String> map = dataTable.get(0);


        String query = "INSERT INTO users (username, firstName, lastName, email, password) values " +
                "('"+map.get("username")+"', '"+map.get("first")+"', '"+map.get("last")+"', '"+map.get("email")+"', '"+DigestUtils.md5Hex(map.get("password"))+"')";
        DBUtils.executeUpdate(query);


    }




    @Then("The user email for {string} in the database should also be updated to {string}")
    public void the_corresponding_user_email_in_the_database_should_also_be_updated_to(String username, String expectedEmail) {

        List<Map<String, Object>> list = DBUtils.getQueryResultAsListOfMaps("select email from users where username='" + username + "'");

        Assert.assertEquals(expectedEmail, list.get(0).get("email"));

    }

    List<String> actualColumnNames;
    @When("I send a query to retrieve column names for users table")
    public void i_send_a_query_to_retrieve_column_names_for_users_table() {

        actualColumnNames = DBUtils.getColumnNames("select * from users limit 1");

    }
    @Then("The column names should be the following")
    public void the_column_names_should_be_the_following(List<String> expected) {

        Assert.assertEquals(expected,actualColumnNames);
    }

    List<String> actualList;
    @When("I send a query to retrieve genres from genres table")
    public void i_send_a_query_to_retrieve_genres_from_genres_table() {

        List<List<Object>> listOfList = DBUtils.getQueryResultAsListOfLists("select name from genres");

        actualList = new ArrayList<>();

        for (List<Object> each : listOfList) {

           actualList.add( (String)(each.get(0)) );
        }



    }
    @Then("The expected list of genres should be the following")
    public void the_expected_list_of_genres_should_be_the_following(List<String > expectedList) {
        Assert.assertEquals(expectedList,actualList);
    }

    List<String> usernames;
    @When("I send a request to retrieve all usernames")
    public void i_send_a_request_to_retrieve_all_usernames() {
        List<List<Object>> listOfLists = DBUtils.getQueryResultAsListOfLists("select username from users");
       usernames = new ArrayList<>();
        for (List<Object> each : listOfLists) {
            usernames.add((String)(each.get(0)));
        }


    }
    @Then("The usernames column should not contain any duplicates")
    public void the_usernames_column_should_not_contain_any_duplicates() {

        Collections.sort(usernames);

        boolean noDuplicate = true;
        for (int i = 0; i < usernames.size()-1; i++) {

            if(usernames.get(i).equals(usernames.get(i+1))){
                noDuplicate = false;
                break;
            }
        }


        Assert.assertTrue(noDuplicate);
    }



}
