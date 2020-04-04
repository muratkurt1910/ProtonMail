package com.protonMail.pages;

import com.protonMail.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InboxPage extends DashboardPage {

    @FindBy(css = "[ng-click='ctrl.cancel()']")
    public WebElement welcomeClose;

    public void closeWelcome(){
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(welcomeClose));
        welcomeClose.click();
    }

    public void chooseEmail(String emailTitle){
        List<WebElement> emails = Driver.get().findElements(By.xpath("//span[@class='subject-text inbl mw100 ellipsis']"));

        for (int i = 0; i < emails.size(); i++) {
            if(emails.get(i).getText().equals(emailTitle)){
                wait.until(ExpectedConditions.elementToBeClickable(emails.get(i)));
                emails.get(i).click();
            }
        }
    }
}
