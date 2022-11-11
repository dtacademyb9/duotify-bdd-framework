package com.duotify.stepDefinitions;

import com.duotify.pages.AlbumInfoPage;
import com.duotify.pages.BrowsePage;
import com.duotify.pages.PlaylistInfoPage;
import com.duotify.pages.YourMusicPage;
import com.duotify.utilities.Driver;
import com.duotify.utilities.SeleniumUtils;
import io.cucumber.java.an.Y;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyMusicStepDefs {

    String nameShared;
    List<Map<String, String>> sharedDatatable;
    @Then("I click on your music and create a playlist named {string}")
    public void i_click_on_your_music_and_create_a_playlist_named(String name) throws InterruptedException {

        nameShared = name;
        BrowsePage browsePage = new BrowsePage();
        browsePage.yourMusicLink.click();
        YourMusicPage yourMusicPage = new YourMusicPage();
        yourMusicPage.newPlaylistButton.click();
        Driver.getDriver().switchTo().alert().sendKeys(name);
        Driver.getDriver().switchTo().alert().accept();



    }
    @Then("I add the following songs from the albums to my playlist")
    public void i_add_the_following_songs_from_the_albums_to_my_playlist(List<Map<String, String>> dataTable) throws InterruptedException {

        sharedDatatable = dataTable;

        BrowsePage browsePage = new BrowsePage();


        for (int i =0; i< dataTable.size(); i++) {
            browsePage.browseLink.click();

            browsePage.clickOnAlbum(dataTable.get(i).get("Album"));

            AlbumInfoPage albumInfoPage = new AlbumInfoPage();

            albumInfoPage.clickOnThreeDotsAndAddToPlaylist(dataTable.get(i).get("Song"), nameShared);

        }





    }
    @Then("My playlist should contain the same songs")
    public void my_playlist_should_contain_the_same_songs() {


        BrowsePage browsePage = new BrowsePage();
        browsePage.yourMusicLink.click();

        YourMusicPage yourMusicPage = new YourMusicPage();

        yourMusicPage.clickOnPlayList(nameShared);

        List<String> actualSongNames = SeleniumUtils.getElementsText(yourMusicPage.allSongs);

        List<String> expectedSongNames =  new ArrayList<>();

        for (int i = 0; i < sharedDatatable.size(); i++) {

            expectedSongNames.add(sharedDatatable.get(i).get("Song"));
        }

        Assert.assertEquals(expectedSongNames, actualSongNames);

    }

    @And("I delete the playlist")
    public void iDeleteThePlaylist() {
        BrowsePage browsePage = new BrowsePage();
        browsePage.yourMusicLink.click();

        YourMusicPage yourMusicPage = new YourMusicPage();

        yourMusicPage.clickOnPlayList(nameShared);

        PlaylistInfoPage playlistInfoPage = new PlaylistInfoPage();
        playlistInfoPage.deleteButton.click();
        Driver.getDriver().switchTo().alert().accept();

    }
}
