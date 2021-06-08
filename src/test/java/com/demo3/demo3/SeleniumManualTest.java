package com.demo3.demo3;

import com.demo3.demo3.pages.ActorEditPage;
import com.demo3.demo3.pages.ActorPage;
import com.demo3.demo3.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumManualTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://e2eprojectui.s3-website.eu-west-2.amazonaws.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        //go to actors
        HomePage homePageObj = new HomePage(driver);
        homePageObj.clickActors();
        Thread.sleep(2000);

        //click edit on actor 2
        ActorPage actorsPageObj = new ActorPage(driver);
        actorsPageObj.openEditPage(2);
        Thread.sleep(2000);

        //fill in the boxes and submit
        ActorEditPage actorsEditPageObj = new ActorEditPage(driver);
        actorsEditPageObj.firstNameText("tryinganedit");
        Thread.sleep(2000);
        actorsEditPageObj.lastNameText("withobjecttype");
        Thread.sleep(2000);
        actorsEditPageObj.submit();
        Thread.sleep(4000);
        driver.quit();
    }
}
