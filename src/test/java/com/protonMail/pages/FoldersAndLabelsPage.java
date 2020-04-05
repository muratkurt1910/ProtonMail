package com.protonMail.pages;

import com.protonMail.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FoldersAndLabelsPage extends DashboardPage {

    @FindBy(id = "accountName")
    public WebElement nameBox;

    @FindAll({ @FindBy( xpath = "//*[@role='alert']"), @FindBy(className = "ng-hide"), @FindBy(xpath = "//span[@ng-show='!$messageTemplate']")})
    public WebElement alert;

    @FindBy(css = "[data-name='mailbox']")
    public WebElement mailbox;

    public String getAlertMessage() throws InterruptedException {
        while (!alert.isDisplayed()){
            Thread.sleep(1000);
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(alert));
        }catch (TimeoutException e){
            e.printStackTrace();
        }finally {
            return alert.getText();
        }
    }

    public List<String> getLabelNames(){
        List<String> labelNamesStr = new ArrayList<>();
        List<WebElement> labelNames = Driver.get().findElements(By.cssSelector("[data-test-id='folders/labels:item-name']"));
        for (WebElement labelName : labelNames) {
            wait.until(ExpectedConditions.visibilityOf(labelName));
            labelNamesStr.add(labelName.getText());
        }

        return labelNamesStr;
    }

    public List<String> getFolderNames(String folder) {
        List<String> labelNamesStr = new ArrayList<>();
        WebElement labelNames = null;
        try {
            labelNames = Driver.get().findElement(By.xpath("//*[text()='" + folder + "']"));
            wait.until(ExpectedConditions.visibilityOf(labelNames));
            labelNamesStr.add(labelNames.getText());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }finally {
            return labelNamesStr;
        }
    }

    public void deleteAllLabelsFolders() {
        List<WebElement> deleteDropdown = Driver.get().findElements(By.cssSelector("[data-test-id='dropdown:open']"));
        System.out.println("deleteDropdown.size() = " + deleteDropdown.size());
        for (int i = deleteDropdown.size()-1; i >= 0; i--) {
            wait.until(ExpectedConditions.elementToBeClickable(deleteDropdown.get(i)));
            try {
                deleteDropdown.get(i).click();
            } catch (Exception e) {
                JavascriptExecutor executor = (JavascriptExecutor) Driver.get();
                executor.executeScript("arguments[0].click();", deleteDropdown.get(i));
            }
            WebElement delete = Driver.get().findElement(By.xpath("(//*[text()='Delete'])["+(i+1)+"]"));
            wait.until(ExpectedConditions.elementToBeClickable(delete));
            delete.click();
            clickButton("Confirm");
        }
    }

    public List<String> getAllEmailsInLabel() {
        List<WebElement> emails = Driver.get().findElements(By.xpath("//span[@class='subject-text inbl mw100 ellipsis']"));
        List<String> emailsStr = new ArrayList<>();

        for (int i = 0; i < emails.size(); i++) {
            emails.get(i).click();
            emailsStr.add(emails.get(i).getText());
        }

        return emailsStr;
    }
}
