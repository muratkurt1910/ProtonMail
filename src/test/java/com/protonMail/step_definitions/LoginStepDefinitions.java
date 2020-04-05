package com.protonMail.step_definitions;

import com.protonMail.pages.InboxPage;
import com.protonMail.pages.LoginPage;
import com.protonMail.utilities.ConfigurationReader;
import com.protonMail.utilities.Driver;
import io.cucumber.java.en.Given;

public class LoginStepDefinitions {

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @Given("User logs in with valid credentials username and password")
    public void user_logs_in_with_valid_credentials_username_and_password() {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        new InboxPage().closeWelcome();
    }

    @Given("User logs in with valid credentials username {string} and password {string}")
    public void user_logs_in_with_valid_credentials_username_and_password(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        InboxPage inboxPage = new InboxPage();
        new InboxPage().closeWelcome();
    }


}
