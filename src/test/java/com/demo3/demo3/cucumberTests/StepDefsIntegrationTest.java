
package com.demo3.demo3.cucumberTests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class StepDefsIntegrationTest extends SpringIntegrationTest {
    @When("^client makes call to GET api")
    public void userGetAllFilmsValid() throws Throwable{
        executeGet("http://localhost:8080/api/films/all");
    }

    @When("^user calls \\/apis$")
    public void userGetAllFilmsValid2() throws Throwable{
        executeGet("http://localhost:8080/api/films/all");
    }

    @Then("^user receives error of code (\\d+)$")
    public void userReceives_Code(int statusCode) throws Throwable{
       final HttpStatus code = latestResponse.getTheResponse().getStatusCode();
       assertThat("code incorrect" + latestResponse.getBody(),code.value(),is(statusCode));
    }

}

