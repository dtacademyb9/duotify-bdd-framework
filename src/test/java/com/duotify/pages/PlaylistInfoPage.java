package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaylistInfoPage {



    public PlaylistInfoPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//button[.='DELETE PLAYLIST']")
    public WebElement deleteButton;

}
