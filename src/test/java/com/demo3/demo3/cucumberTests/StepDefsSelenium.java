package com.demo3.demo3.cucumberTests;

import com.demo3.demo3.pages.ActorEditPage;
import com.demo3.demo3.pages.ActorPage;
import com.demo3.demo3.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
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
    public void goToSite(){
        driver.get("http://3.8.24.96:3000/");
        String title = driver.getTitle();
        assertEquals(title,"Testing Project");
    }

    @When("user clicks on {string} button")
    public void openList(String entity){
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

    @When("user clicks on actor button")
    public void openList(){
        HomePage homePageObj = new HomePage(driver);
        homePageObj.clickActors();
    }

    @And("user clicks edit on id {int}")
    public void openEdit(int id) throws InterruptedException {
        ActorPage actorPageObj = new ActorPage(driver);
        Thread.sleep(1000);
        actorPageObj.openEditPage(id);
    }

    @And("user enters {string} into firstname box and submits")
    public void openEdit(String firstname) throws InterruptedException {
        ActorEditPage actorEditPageObj = new ActorEditPage(driver);
        actorEditPageObj.clearFirstNameText();
        Thread.sleep(1000);
        actorEditPageObj.firstNameText(firstname);
        Thread.sleep(1000);
        actorEditPageObj.submit();
    }

    @Then("firstname for {int} is updated with {string}")
    public void firstIsNameUpdated(int id, String firstname) throws InterruptedException {
        By newfirstName = By.cssSelector("#root > div > div > table > tbody > tr:nth-child("+id+") > td:nth-child(2)");
        Thread.sleep(1000);
        String actual = driver.findElement(newfirstName).getText();
        assertEquals(actual,firstname);
    }

    @Then("{string} page opens")
    public void pageOpens(String entity){
        String url = driver.getCurrentUrl();
        assertEquals(url,"http://3.8.24.96:3000/" + entity);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
