**ProtonMail**
This project is a sample of Settings module of ProtonMail application that is used to testing DDT and BDD Cucumber Framework. On Settings module ProtonMail uses a flexible hybrid system of folders and labels to maximize productivity and organization within your inbox.

**Test Environment**
Behavior Data Driven(BDD) Cucumber Framework and Data Driven Test(DDT) Framework Java Language Maven Selenium Webdriver JUnit IntelliJ IDE Jenkins

**Framework Structure**
    
    >Proton_Mail
        >.idea
        >src
            >main
            >test
                >java
                    >com
                        >protonMail
                            >pages
                                /DashboardPage
                                /FoldersAndLabelsPAge
                                /InboxPage
                                /LoginPage                              
                            >runners
                                /CukesRunner
                                /FailedTestRunner                                
                            >step_definitions
                                /FoldersAndLabelsStepDefinitions
                                /Hooks
                                /InboxStepDefinitions
                                /LoginStepDefinitions
                            >utilities
                                /ConfigurationReader
                                /Driver
                >resources
                    >features
                        /foldersLabels.feature
                        /settrings.feature
        >target
            /.gitignore
            /Configuration.properties
            /pom.xml
            /Proton_Mail.iml
            /README.md
    >External Libraries
    Scratches and Consoles

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

Tests start and finish by the orginizing of Hooks class by @Before and @After annotations. Inside @Before there is commands to start browser and manage it. Inside @After there is closing browser and taking screenshot
 
To continues integration used Jenkins. By the integration of Jenkins and Github, Jenkins retrieved the project data and executed the project at any time which set in Jenkins as Cron expression(for example (H 6 * * *) run the codes every day at 6 am) and set CucumberReports is sent to related email addresses in any failure or success. To run codes from Jenkins used Maven Life Cycle codes. For example

    mvn verify -Dcucumber.options="--tags @settings"     This code will execute scenarios under the @settings tags.
    mvn verify -Dbrowser=firefox    This code will execute the test in the Firefoz browser

**Reporting**
Reporting the result Cucumber is providing result of executing at the output. For example

    I am reporting the results
    Closing to Browser
    
    Failed scenarios:
    file:///C:/Users/mkurt/.eclipse/Proton_Mail/src/test/resources/features/foldersLabels.feature:21# User who has free account can not add more than three labels
    file:///C:/Users/mkurt/.eclipse/Proton_Mail/src/test/resources/features/foldersLabels.feature:35# User who has free account can not add more than three folders
    
    9 Scenarios (2 failed, 7 passed)
    68 Steps (2 failed, 66 passed)
    3m27.945s

Here can see that how many scenarios run and how many of them failed and passed in how much time.

All existing reports can find under the target file. All reports created by the command from CukesRunner class inside cucumber.options

    plugin = {"json:target/cucumber.json", "html:target/default-html-reports", "rerun:target/rerun.txt"},
    
While executing the test Cucumber framework provides a HTML report as a default under the target file.  _`"html:target/default-html-reports"`_

Also by the running Maven Life Cycle (mvn test or mvn verify commands) cucumber creating a fancy HTML-JSON Cucumber reports `_"json:target/cucumber.json"_`
It is creating by a maven plugin which added before to pom.xml

        <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-surefire-plugin</artifactId>
                        <version>2.22.2</version>
                        <configuration>
                            <!--<parallel>methods</parallel>
                            <useUnlimitedThreads>true</useUnlimitedThreads>-->
                            <testFailureIgnore>true</testFailureIgnore>
                            <runOrder>Alphabetical</runOrder>
                            <includes>
                                <include>**/*CukesRunner.java</include>
                                <include>**/*FailedTestRunner.java</include>
                            </includes>
                        </configuration>
                    </plugin>
                    <plugin>
                        <groupId>net.masterthought</groupId>
                        <artifactId>maven-cucumber-reporting</artifactId>
                        <version>5.0.0</version>
                        <executions>
                            <execution>
                                <id>execution</id>
                                <phase>verify</phase>
                                <goals>
                                    <goal>generate</goal>
                                </goals>
                                <configuration>
                                    <projectName>Cucumber HTML Reports</projectName>
                                    <outputDirectory>${project.build.directory}</outputDirectory>
                                    <inputDirectory>${project.build.directory}</inputDirectory>
                                    <jsonFiles>
                                        <param>**/cucumber*.json</param>
                                    </jsonFiles>
                                </configuration>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
        </build>
    
**RerunFailedTest**
After executing if there is any failure by the help of FailedTestRunner class Cucumber Framework can execute failed tests again. Its command also inside the cucumber.options `_"rerun:target/rerun.txt"_`
    
                                <includes>
                                    <include>**/*CukesRunner.java</include>
                                    <include>**/*FailedTestRunner.java</include>
                                </includes>

This command give order to runner classes. Also under target file there is an existing `_rerun.txt_`  which stores failed tests after running tests and it is syncronized with the FailedTestsRunner class

All tests after executing finishing by the help of @After annotation which is in Hooks class and it close browser and taking screenshot.  

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        
By the help of this code system taking screenshot of any failure and putting to reports under Hooks class
                
    
