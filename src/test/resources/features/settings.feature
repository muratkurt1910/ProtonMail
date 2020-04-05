
Feature: Settings Folders and Labels

  Background: User should be able to login with valid credentials
    Given User is on the login page
    And User logs in with valid credentials username and password
  @settings
  Scenario: User should be able to create a label
    When User navigates to "Folders / Labels" folder
    And User clicks "Add label" button
    And User enter a name "Label1" to name textbox
    And User clicks "Submit" button
    Then User should see "Label1 created" message
    And User should see created label "Label1" on the Folders And Labels page

  Scenario: User should be able to carry a mail to a label
    When User choose the email "How to secure your ProtonMail account"
    And User navigates to "Label as" dropdown
    And User choose "Label1" label
    And User clicks "Apply" button
    Then User should see "Labels Saved" message
    And User navigates to "Label1" folder
    And User should see the mail "How to secure your ProtonMail account" in the "Label1" folder

  Scenario: User should be able to archieve a mail while carrying to a label
    When User choose the email "Encrypt your Internet with ProtonVPN"
    And User navigates to "Label as" dropdown
    And User choose "Label1" label
    And User clicks "Also Archive" button
    And User clicks "Apply" button
    Then User should see that the email "Encrypt your Internet with ProtonVPN" is deleted from "Inbox" folder
    And User navigates to "Archive" folder
    And User should see the mail "Encrypt your Internet with ProtonVPN" in the "Archive" folder

  Scenario: Multiple labels should be apply to a mail
    When User navigates to "Folders / Labels" folder
    And User clicks "Add label" button
    And User enter a name "Label2" to name textbox
    And User clicks "Submit" button
    And User navigates back
    And User choose the email "How to secure your ProtonMail account"
    And User navigates to "Label as" dropdown
    And User choose "Label2" label
    And User clicks "Apply" button
    Then User should see "Labels Saved" message
    And User navigates to "Label2" folder
    And User should see the mail "How to secure your ProtonMail account" in the "Label2" folder

  Scenario: Move to archived email to inbox folder
    When User navigates to "Archive" folder
    And User choose the email "Encrypt your Internet with ProtonVPN"
    And User navigates to "Move to" dropdown
    And User choose "Inbox" folder
    Then User should see "1 conversation moved to Inbox. Undo" message
    And User navigates to "Inbox" folder
    And User should see the mail "Encrypt your Internet with ProtonVPN" in the "Inbox" folder

  Scenario: After deleting email, it should be deleted the label as well
    When User choose the email "Encrypt your Internet with ProtonVPN"
    And User navigates to "Move to trash" dropdown
    Then User should see "1 conversation moved to Trash. Undo" message
    And User navigates to "Label1" folder
    And User should see that the email "Encrypt your Internet with ProtonVPN" is deleted from "Label1" folder
