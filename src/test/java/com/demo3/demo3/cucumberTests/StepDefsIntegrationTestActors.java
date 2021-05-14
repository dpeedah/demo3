
package com.demo3.demo3.cucumberTests;

import com.demo3.demo3.actor.Actor;
import com.demo3.demo3.actor.ActorController;
import com.demo3.demo3.actor.ActorRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class StepDefsIntegrationTestActors extends SpringIntegrationTest {

    @Autowired
    private ActorController actorController;

    @Given("user provides an actor with a valid and unique {string} and {string}")
    public void userDataValid(String firstName, String lastName) throws Throwable{
        Actor actor = new Actor(firstName,lastName);
        //make executePut work
    }

    @When("user makes call to GET all actors api")
    public void userGetAllFilmsValid() throws Throwable{
        executeGet("/api/actors/all");
    }

    @When("user makes call to GET actor by {int} api")
    public void userGetAllFilmsValidById(int id) throws Throwable{
        executeGet("/api/actors/byid/"+id);
    }


    @Then("user receives response code {int}")
    public void userReceives_Code(int statusCode) throws Throwable{
        final HttpStatus code = latestResponse.getTheResponse().getStatusCode();
        assertThat("code incorrect" + latestResponse.getBody(),code.value(),is(statusCode));
    }

}



