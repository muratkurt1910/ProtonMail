package com.protonMail.step_definitions;

import com.protonMail.pages.FoldersAndLabelsPage;
import com.protonMail.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FoldersAndLabelsStepDefinitions {

    FoldersAndLabelsPage foldersAndLabelsPage = new FoldersAndLabelsPage();

    @When("User clicks {string} button")
    public void user_clicks_button(String button) {
        foldersAndLabelsPage.clickButton(button);
    }

    @When("User navigates back")
    public void user_navigates_back() {
        Driver.get().navigate().back();
    }

    @When("User enter a name {string} to name textbox")
    public void user_enter_a_name_to_name_textbox(String name) {
        foldersAndLabelsPage.nameBox.sendKeys(name);
    }

    @Then("User should see {string} message")
    public void user_should_see_message(String message) {
        String actualMessage = foldersAndLabelsPage.getAlertMessage();
        String expectedMessage = message;

        Assert.assertEquals("User should see " + message + " message", expectedMessage, actualMessage);
    }

    @Then("User should see created label {string} on the Folders And Labels page")
    public void user_should_see_created_label_on_the_Folders_And_Labels_page(String label) {
        Assert.assertTrue("User should see created label "+label+" on the Folders And Labels page",foldersAndLabelsPage.getLabelNames().contains(label));
        Assert.assertTrue("User who has free account can not add more than 3 labels", foldersAndLabelsPage.getLabelNames().size()<=3);

    }

    @Then("User should see created folder {string} on the Folders And Labels page")
    public void user_should_see_created_folder_on_the_Folders_And_Labels_page(String folder) {
        Assert.assertTrue("User who has free account can not add more than 3 folders", foldersAndLabelsPage.getFolderNames(folder).size()<=3);
        Assert.assertTrue("User should see created folder " + folder + " on the Folders And Labels page", foldersAndLabelsPage.getFolderNames(folder).contains(folder));

    }

    @Then("User should see the mail {string} in the {string} folder")
    public void user_should_see_the_mail_in_the_Label1_folder(String emailTitle, String folderName) {
        Assert.assertTrue("User should see the mail "+emailTitle+" in the " + folderName,foldersAndLabelsPage.getAllEmailsInLabel().contains(emailTitle));
    }

    @Then("User deletes all labels and folders")
    public void user_deletes_all_labels_and_folders() {
        foldersAndLabelsPage.deleteAllLabelsFolders();
    }
}
