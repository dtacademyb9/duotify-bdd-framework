package com.duotify.pages;

import com.duotify.utilities.Driver;
import com.duotify.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AlbumInfoPage {


    public AlbumInfoPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//span[@class='duration']")
    public WebElement minsAndSeconds;


    @FindBy(tagName = "h2")
    public WebElement albumName;

    @FindBy(xpath = "//p[@role='link']")
    public WebElement artistName;

    @FindBy(xpath = "//p[contains( text(), 'songs' )]")
    public WebElement songCount;



    @FindBy(xpath = "//select[@class='item playlist']")
    public WebElement selectMenu;


    public void clickOnThreeDotsAndAddToPlaylist(String trackName, String playlist) throws InterruptedException {

        String xpath = "//span[@class='trackName'][.='"+trackName+"']//parent::div//following-sibling::div//img[@class='optionsButton']";
        WebElement threeDots = Driver.getDriver().findElement(By.xpath(xpath));
        SeleniumUtils.hover(threeDots);
        threeDots.click();
        selectMenu.click();
        Select select = new Select(selectMenu);
        select.selectByVisibleText(playlist);
        Driver.getDriver().findElement(By.xpath(xpath)).click();

    }



}
