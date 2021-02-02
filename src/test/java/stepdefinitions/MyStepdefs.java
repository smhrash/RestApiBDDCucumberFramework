package stepdefinitions;

import files.Payload;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import payload.PayLoad;
import resources.APIResources;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class MyStepdefs extends Utils {


    Response response;
    //JsonPath jsonPath = new JsonPath(response);
    //RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;




    @Given("User add Place by adding payload")
    public void userAddPlaceByAddingPayload() throws IOException {

        requestSpecification = given().spec(requestSpecification()).body(PayLoad.getBody());

        //requestSpecification = given().queryParams("key","qaclick123").body(PayLoad.getBody());
    }



    @When("user calls {string} with {string} Request")
    public void userCallsWithRequest(String resource, String method) {
        APIResources apiResources = APIResources.valueOf(resource);
        System.out.println(apiResources.getResource());


       responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
               .expectContentType(ContentType.JSON).build();

       if (method.equalsIgnoreCase("Post")) {
           response = requestSpecification.when().post(apiResources.getResource());
       }

       else if (method.equalsIgnoreCase("Get")){
           response = requestSpecification.when().get(apiResources.getResource());
       }

    }

    @Then("Api calls got success with status code {int}")
    public void apiCallsGotSuccessWithStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        Assert.assertEquals(jsonPath.get(key).toString(),value);
    }




}
