package com.demo3.demo3.cucumberTests;

import com.demo3.demo3.pages.FilmActorPage;
import com.demo3.demo3.pages.FilmPage;
import com.demo3.demo3.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
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
    public void somethingelseee(String entity) throws InterruptedException {
        String url = driver.getCurrentUrl();
        Thread.sleep(5000);
        assertEquals(url,"http://3.8.24.96:3000/" + entity);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @And("user clicks on show actors of film {int}")
    public void userClicksOnShowActorsOfFilmId(int id) throws InterruptedException {
        Thread.sleep(5000);
        FilmPage filmpage = new FilmPage(driver);
        filmpage.openFilmActorsPage(id);
    }

    @And("one or more actors displayed")
    public void oneOrMoreActorsDisplayed() {
        FilmActorPage filmActorPage = new FilmActorPage(driver);
        assertTrue(filmActorPage.countReturned() > 0);
    }

    @Then("{string} page opens for film {int}")
    public void pageOpensForFilmId(String arg0, int arg01) throws InterruptedException {
        String url = driver.getCurrentUrl();
        Thread.sleep(1000);
        assertEquals(url,"http://3.8.24.96:3000/" + arg0 + "/" + arg01 );
    }
}
