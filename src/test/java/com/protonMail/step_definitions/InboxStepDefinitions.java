package com.protonMail.step_definitions;

import com.protonMail.pages.FoldersAndLabelsPage;
import com.protonMail.pages.InboxPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class InboxStepDefinitions {

    InboxPage inboxPage = new InboxPage();

    @When("User navigates to {string} folder")
    public void user_navigates_to_folder(String folderName) {
        inboxPage.clickButton(folderName);
    }

    @When("User choose the email {string}")
    public void user_choose_the_email(String emailTitle) {
        inboxPage.chooseEmail(emailTitle);
    }

    @When("User navigates to {string} dropdown")
    public void user_navigates_to_dropdown(String dropdown) {
        inboxPage.navigateToModule(dropdown);
    }

    @When("User choose {string} label")
    public void user_choose_label(String label) {
        inboxPage.clickDropDownItem(label);
    }

    @Then("User should see that the email {string} is deleted from {string} folder")
    public void user_should_see_that_the_email_is_deleted_from_Inbox_folder(String emailTitle, String folder) {
        Assert.assertFalse("User should see that the email "+emailTitle+" is deleted from "+folder+" folder", new FoldersAndLabelsPage().getAllEmailsInLabel().contains(emailTitle));
    }

    @When("User choose {string} folder")
    public void user_choose_folder(String folder) {
        inboxPage.moveToInbox.click();
    }
}
