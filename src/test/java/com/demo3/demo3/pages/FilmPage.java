package com.demo3.demo3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FilmPage {
    WebDriver driver;

    By addActorButton = By.linkText("Add Film");

    public FilmPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openEditPage(int id){
        By editActorButton = By.xpath("//a[@href='/films/edit/"+id+"']");
        driver.findElement(editActorButton).click();
    }

    public void openFilmActorsPage(int id){
        WebElement a = driver.findElement(By.xpath("//a[@href='/films/actors/"+id+"']"));
        a.click();
    }

    public void openAddPage(int id){
        driver.findElement(addActorButton).click();
    }
}
