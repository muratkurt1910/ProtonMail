package com.protonMail.step_definitions;

import com.protonMail.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
        System.out.println("Connecting to Browser");
    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("I am reporting the results");
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        System.out.println("Closing to Browser");
        Driver.closeDriver();
    }
}
