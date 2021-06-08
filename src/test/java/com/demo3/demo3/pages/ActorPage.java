package com.demo3.demo3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActorPage {
    WebDriver driver;

    By addActorButton = By.linkText("Add Actor");

    public ActorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openEditPage(int id){
        By editActorButton = By.xpath("//a[@href='/actors/edit/"+id+"']");
        driver.findElement(editActorButton).click();
    }

    public void openAddPage(int id){
        driver.findElement(addActorButton).click();
    }
}
