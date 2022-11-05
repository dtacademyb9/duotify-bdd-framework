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

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "email2")
    public WebElement email2;


    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "password2")
    public WebElement password2;








}
