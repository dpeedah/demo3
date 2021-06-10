package com.demo3.demo3.cucumberTests;

import com.demo3.demo3.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class StepDefsSelenium {

    static WebDriver driver;


    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Given("user goes to the site")
    public void something(){
        driver.get("http://3.8.24.96:3000/");
        String title = driver.getTitle();
        assertEquals(title,"Testing Project");
    }

    @When("user clicks on {string} button")
    public void somethingelse(String entity){
        HomePage homePageObj = new HomePage(driver);
        if(entity.equals("actors")){
            homePageObj.clickActors();
            assertTrue(true);
        }else if(entity.equals("categories")){
            homePageObj.clickCategories();
            assertTrue(true);
        }else if(entity.equals("films")){
            homePageObj.clickFilms();
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    @Then("{string} page opens")
    public void somethingelseee(String entity){
        String url = driver.getCurrentUrl();
        assertEquals(url,"http://3.8.24.96:3000/" + entity);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
