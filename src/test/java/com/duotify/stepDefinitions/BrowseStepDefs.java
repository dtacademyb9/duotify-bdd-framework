package com.duotify.stepDefinitions;

import com.duotify.pages.AlbumInfoPage;
import com.duotify.pages.BrowsePage;
import com.duotify.utilities.SeleniumUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BrowseStepDefs {
    @And("The song duration should be {int} minutes {int} seconds")
    public void theSongDurationShouldBeMinutesSeconds(int mins, int secs) throws InterruptedException {

        AlbumInfoPage albumInfoPage = new AlbumInfoPage();
        Thread.sleep(2000);
        String[] split = albumInfoPage.minsAndSeconds.getText().split(":");
        Assert.assertEquals(mins, Integer.parseInt(split[0]));
        Assert.assertEquals(secs, Integer.parseInt(split[1]));

    }

    @And("I click on {string} album")
    public void iClickOnAlbum(String albumName) {

        new BrowsePage().clickOnAlbum(albumName);
    }


    @Then("The price of the song should be {double}")
    public void the_price_of_the_song_should_be(Double price) {
        System.out.println("The price is " + price);
    }




    @Then("I should see the following albums")
    public void i_should_see_the_following_albums(List<String> expectedList) {


        BrowsePage browsePage = new BrowsePage();
        List<String> actualList = SeleniumUtils.getElementsText(browsePage.albums);

        Collections.sort(actualList);

//        Collections.sort(expectedList);
        // Cucumber creates an unmodifiable versions of Lists and Maps when it converts datable to java type.

        List<String> modifiableExpected = new ArrayList<>(expectedList);

        Collections.sort(modifiableExpected);

        Assert.assertEquals(modifiableExpected, actualList);
    }

}
