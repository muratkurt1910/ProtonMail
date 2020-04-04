@foldersLabels
Feature: User who has free account can not add more three labels and folders

  Background:
    Given User is on the login page
    And User logs in with valid credentials username "haik.78" and password "1234qwerasdf"
    When User navigates to "Folders / Labels" folder

  Scenario Outline: User who has free account can not add more than three labels
    And User clicks "Add label" button
    And User enter a name "<label name>" to name textbox
    And User clicks "Submit" button
    Then User should see "<alert message>" message
    And User should see created label "<label name on folders/labels page>" on the Folders And Labels page

    Examples:
      | label name | alert message  | label name on folders/labels page |
      | Label1     | Label1 created | Label1                            |
      | Label2     | Label2 created | Label2                            |
      | Label3     | Label3 created | Label3                            |
      | Label4     | Label4 created | Label4                            |

  Scenario Outline: User who has free account can not add more than three folders
    And User clicks "Add folder" button
    And User enter a name "<label name>" to name textbox
    And User clicks "Submit" button
    Then User should see "<alert message>" message
    And User should see created folder "<label name on folders/labels page>" on the Folders And Labels page

    Examples:
      | label name | alert message                                                           | label name on folders/labels page |
      | Folder1    | Folder1 created                                                         | Folder1                           |
      | Folder2    | Folder2 created                                                         | Folder2                           |
      | Folder3    | Folder3 created                                                         | Folder3                           |
      | Folder4    | Folder limit reached. Please upgrade to a paid plan to add more folders | Folder4                           |


  Scenario: Delete all labels and folders
    Then User deletes all labels and folders

