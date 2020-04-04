package com.protonMail.pages;

import com.protonMail.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "login_btn")
    public WebElement loginButton;

    public void login(String usernameStr, String passwordStr){
        username.sendKeys(usernameStr);
        password.sendKeys(passwordStr);
        loginButton.click();
    }

}
