package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YourMusicPage {


    public YourMusicPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//button[.='NEW PLAYLIST']")
    public WebElement newPlaylistButton;


    @FindBy(xpath = " //ul//span[@class='trackName']")
    public List<WebElement> allSongs;




    public  void clickOnPlayList(String name){
        Driver.getDriver().findElement(By.xpath("//div[@class='gridViewInfo'][.='"+name+"']")).click();
    }

}
