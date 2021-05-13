
package com.demo3.demo3.cucumberTests;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@CucumberOptions(features = "src/test/resources")
public class StepDefsIntegrationTest extends SpringIntegrationTest {

    @When("^user calls /apis$")
    public void userGetAllFilmsValid() throws Throwable{
        executeGet("http://localhost:8080/api/films");
    }

    @Then("^user receives error of code (\\d+)$")
    public void userReceives_Code(int statusCode) throws Throwable{
       HttpStatus code = latestResponse.getTheResponse().getStatusCode();
       assertThat("code incorrect" + latestResponse.getBody(),200,is(statusCode));
    }

}

