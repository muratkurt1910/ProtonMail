package com.protonMail.pages;

import com.protonMail.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class DashboardPage {
    public DashboardPage(){ PageFactory.initElements(Driver.get(), this);    }

    @FindBy(css = ".loadingAnimation")
    public WebElement loadingAnimation;

    @FindBy(css = "[data-folder-id='0']")
    public WebElement moveToInbox;

    WebDriverWait wait = new WebDriverWait(Driver.get(), 10);

    public void waitUntilLoadingAnimationDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(loadingAnimation));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToModule(String module) {
        WebElement element = Driver.get().findElement(By.cssSelector("[pt-tooltip='"+module+"']"));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            new Actions(Driver.get()).moveToElement(element).pause(100).click(element).build().perform();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
                ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element);
            }catch (WebDriverException e1){
                e1.printStackTrace();
            }
        }
    }

    public void clickDropDownItem(String item){
        WebElement element = Driver.get().findElement(By.xpath("(//*[text()='"+item+"'])[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void clickButton(String button){
        WebElement element = Driver.get().findElement(By.xpath("//*[text()='"+button+"']"));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            new Actions(Driver.get()).moveToElement(element).pause(100).click(element).build().perform();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
                ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element);
            }catch (WebDriverException e1){
                e1.printStackTrace();
            }
        }
    }
}
