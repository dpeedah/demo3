package com.demo3.demo3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;
    By actorButton = By.linkText("Actors");
    By categoryButton = By.linkText("Categories");
    By filmButton = By.linkText("Films");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickActors(){
        driver.findElement(actorButton).click();
    }

    public void clickCategories(){
        driver.findElement(categoryButton).click();
    }

    public void clickFilms(){
        driver.findElement(filmButton).click();
    }
}
