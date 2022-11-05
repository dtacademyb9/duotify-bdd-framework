package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {

    public WelcomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "nameFirstAndLast")
    public WebElement firstAndLast;

}
