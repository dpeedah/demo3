package com.demo3.demo3.selenium;

import com.demo3.demo3.pages.ActorPage;
import com.demo3.demo3.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class SeleniumTest {

    static WebDriver driver;


    @Before
    public void initDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://e2eprojectui.s3-website.eu-west-2.amazonaws.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void correctPage(){
        String title = driver.getTitle();
        assertEquals(title,"Testing Project");
    }

    @Test
    public void openActors(){
        HomePage homePageObj = new HomePage(driver);
        homePageObj.clickActors();
        String url = driver.getCurrentUrl();
        assertEquals(url,"http://e2eprojectui.s3-website.eu-west-2.amazonaws.com/actors");
    }

    @Test
    public void openActorsEdit() throws InterruptedException {
        HomePage homePageObj = new HomePage(driver);
        homePageObj.clickActors();
        Thread.sleep(1000);
        ActorPage actorsPageObj = new ActorPage(driver);
        actorsPageObj.openEditPage(1);
        String url = driver.getCurrentUrl();
        assertEquals(url,"http://e2eprojectui.s3-website.eu-west-2.amazonaws.com/actors/edit/1");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
