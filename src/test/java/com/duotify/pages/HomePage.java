package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


     @FindBy(id = "hideLogin")
     public WebElement signUpLink;

    @FindBy(xpath = "//form[@id='registerForm']//h2")
    public WebElement createAccountText;

    @FindBy(name = "registerButton")
    public WebElement signUpButton;

    @FindBy(id = "loginUsername")
    public WebElement loginUsername;

    @FindBy(id = "loginPassword")
    public WebElement loginPassword;

    @FindBy(name = "loginButton")
    public WebElement loginButton;

}
