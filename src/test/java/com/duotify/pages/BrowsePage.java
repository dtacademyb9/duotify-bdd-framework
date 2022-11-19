package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;

import java.util.List;

public class BrowsePage {





    @FindBy(xpath = "//button[.='USER DETAILS']")
    public WebElement userDetailsButton;
    @FindBy(id = "nameFirstAndLast")
    public WebElement userInfoLink;

    public BrowsePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//span[@class='progressTime remaining']")
    public WebElement minsAndSeconds;


    @FindBy(xpath = "//div[@class='gridViewInfo']")
    public List<WebElement> albums;


    @FindBy(xpath = "//span[.='Your Music']")
    public WebElement yourMusicLink;

    @FindBy(xpath = "//span[.='Browse']")
    public WebElement browseLink;





    public void clickOnAlbum(String albumName) {
        String xpath = "//div[.='"+albumName+"']";
        Driver.getDriver().findElement(By.xpath(xpath)).click();
    }
}
