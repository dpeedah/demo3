
package com.demo3.demo3.cucumberTests;

import com.demo3.demo3.film.Film;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class StepDefsIntegrationTestFilms extends SpringIntegrationTest {



    @When("^client makes call to GET api")
    public void userGetAllFilmsValid() throws Throwable{
        executeGet("/api/films/all");
    }

    @When("^user calls \\/apis$")
    public void userGetAllFilmsValid2() throws Throwable{
        executeGet("/api/films/all");
    }

    @Then("^user receives error of code (\\d+)$")
    public void userReceives_Code(int statusCode) throws Throwable{
       final HttpStatus code = latestResponse.getTheResponse().getStatusCode();
       assertThat("code incorrect" + latestResponse.getBody(),code.value(),is(statusCode));
    }

    @Given("the user provides a valid film {string}, {string}, {int},  and {int} of release")
    public void userDataValid(String title, String desc, int lengthMinutes, int yearRelease) throws Throwable{
        Film film = new Film(title,desc,Long.valueOf(lengthMinutes),Long.valueOf(yearRelease));
    }

    @And("film with title {string} is unique")
    public void filmUnique(String title) throws Throwable{
        executeGet("/api/films/all");
        String body = latestResponse.getBody();
        JSONObject json = new JSONObject(body);
        Iterator x = json.keys();
        JSONArray jsonArray = new JSONArray();
        while (x.hasNext()){
            String key = (String) x.next();
            jsonArray.put(json.get(key));
        }
        boolean notUnique = jsonArray.toString().contains("\"title\":\""+title+"\"");
        assertThat("reason", notUnique,is(false));
    }

}


