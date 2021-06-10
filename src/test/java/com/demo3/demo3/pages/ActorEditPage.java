package com.demo3.demo3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ActorEditPage {
    WebDriver driver;
    By firstNameTextBox= By.id("firstName");
    By lastNameTextBox= By.id("lastName");
    //    By submitButton= By.cssSelector("button[class='btn btn-primary']");
    By submitButton= By.xpath("//button[text()='Save']");

    public ActorEditPage(WebDriver driver) {
        this.driver = driver;
    }


    public void firstNameText(String firstName){
        driver.findElement(firstNameTextBox).sendKeys(firstName);
    }

    public void lastNameText(String lastName){
        driver.findElement(lastNameTextBox).sendKeys(lastName);
    }

    public void clearFirstNameText() throws InterruptedException {
        driver.findElement(firstNameTextBox).click();
        Thread.sleep(1000);
        driver.findElement(firstNameTextBox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(firstNameTextBox).sendKeys(Keys.DELETE);
    }

    public void submit(){
        driver.findElement(submitButton).click();
    }

    public String getFirstNameTextBox() {
        return driver.findElement(firstNameTextBox).getText();
    }

    public String getLastNameTextBox() {
        return driver.findElement(lastNameTextBox).getText();
    }
}
