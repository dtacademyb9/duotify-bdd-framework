package com.duotify.stepDefinitions;

import com.duotify.pages.AlbumInfoPage;
import com.duotify.pages.BrowsePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

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


}
