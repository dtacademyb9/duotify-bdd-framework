package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetailsPage {


    public UserDetailsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(name = "email")
    public WebElement emailField;

    @FindBy(xpath = "//button[.='SAVE']")
    public WebElement saveButton;

    @FindBy(xpath = "//span[.='Update successful']")
    public WebElement successMessage;


}
