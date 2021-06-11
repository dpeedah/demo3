package com.demo3.demo3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilmActorPage {
    WebDriver driver;

    By actorRows = By.xpath("//table/tbody/tr");


    public FilmActorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openEditPage(int id){
        By editActorButton = By.xpath("//a[@href='/actors/edit/"+id+"']");
        driver.findElement(editActorButton).click();
    }

    public int countReturned(){
        List rows = driver.findElements(actorRows);
        return rows.size();
    }
}
