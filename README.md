**ProtonMail**
This project is a sample of Settings module of ProtonMail application that is used to testing DDT and BDD Cucumber Framework. On Settings module ProtonMail uses a flexible hybrid system of folders and labels to maximize productivity and organization within your inbox.

**Test Environment**
Behavior Data Driven(BDD) Cucumber Framework and Data Driven Test(DDT) Framework Java Language Maven Selenium Webdriver JUnit IntelliJ IDE Jenkins

**Framework**
Cucumber is a BDD testing framework that allows non-technical people can write specifications that can be translated to unit test requirements almost automatically.

The feature file that has all functionality requirements that the application must fulfill is located here.

In BDD Cucumber Framework by the help of Gherkin Language test cases can use internal in the feature files. So any non-technical people even can understand.

To create a labelshould navigate to settings and should click existing related Add label button. Then can confirm with a message that label created. Created labels should have seen on the settings page.

    @settings
    Feature: Settings Folders and Labels

      Background: User should be able to login with valid credentials
        Given User is on the login page
        And User logs in with valid credentials username "haik.78" and password "1234qwerasdf"

      Scenario: User should be able to create a label
        When User navigates to "Folders / Labels" folder
        And User clicks "Add label" button
        And User enter a name "Label1" to name textbox
        And User clicks "Submit" button
        Then User should see "Label1 created" message
        And User should see created label "Label1" on the Folders And Labels page

Here by the help of @setting annotations test can execute from one place which name is CukesRunner class. For each step can implement test scripts in related StepDEfinitions classes. Also for by the help of feature files same test steps can implement in same methods. Not need to create different implematation for similar steps. For example;

    And User clicks "Add label" button
    And User clicks "Submit" button
    
steps are similar steps. Only the difference is the button name. These two steps use same test script as in the example.

    @When("User clicks {string} button")
    public void user_clicks_button(String button) {
        foldersAndLabelsPage.clickButton(button);
    }
    
In this project Data Driven Test(DDT) Framework is embedded to BDD Cucumber Framework by the help of Scenaria Outline to use same steps and implementations for different datas. For example;

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
    
Here to create more than one label, instead of writing different scenarios to create different labels, DDT Framework can be used to get datas by the help of Scenario Outline.

The framework is build by the help of Maven. Third party librarires(dependencies) added to pom.xml. Creadentials, urls and browser type is keept in the Configuration.properties to fast execution and reusibilty by the help of preapared ConfigurationReader class. All browser set up in the Driver class and by the help of Configuration.properties file browser type changes from one place easily.

To continues integration used Jenkins. By the integration of Jenkins and Github, Jenkins retrieved the project data and executed the project at any time which set in Jenkins as Cron expression(for example (H 6 * * *) run the codes every day at 6 am) and set CucumberReports is sent to related email addresses in any failure or success. To run codes from Jenkins used Maven Life Cycle codes. For example

    Maven verify -Dcucumber.options="--tags @settings"     This code will execute scenarios under the @settings tags.
    mvn verify -Dbrowser=firefox    This code will execute the test in the Firefoz browser
**Closure**
