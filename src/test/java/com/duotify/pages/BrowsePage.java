package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrowsePage {

    public BrowsePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//span[@class='progressTime remaining']")
    public WebElement minsAndSeconds;

    public void clickOnAlbum(String albumName) {
        String xpath = "//div[.='"+albumName+"']";
        Driver.getDriver().findElement(By.xpath(xpath)).click();
    }
}
