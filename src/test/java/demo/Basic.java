package demo;

import files.Payload;
import files.ReUsableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Basic {

    Payload payload = new Payload();

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI ="https://rahulshettyacademy.com";
        String respose = given().queryParams("key","qaclick123").header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get("/Users/user/IdeaProjects/RestApiBDDCucumberFramework/src/test/java/files/Payload.java")))).log().all().
                when().post("/maps/api/place/add/json").
                then().assertThat().log().all().statusCode(200).header("Content-Type","application/json;charset=UTF-8")
                .extract().response().asString();


        System.out.println(respose);

        JsonPath jsonPath = new JsonPath(respose);
        String placeId = jsonPath.getString("place_id");
        System.out.println(placeId);

        String address = "54-01 37th Ave, Woodside, NY-11377";

        given().queryParams("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+address+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").log().all()
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200);

        String getPlaceResponse = given().queryParams("key","qaclick123").queryParams("place_id",placeId)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        System.out.println(getPlaceResponse);


        JsonPath jsonPath1 = ReUsableMethod.rawToJson(getPlaceResponse);
        String actualAddress =jsonPath1.getString("address");
        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress,"54-01 37th Ave, Woodside, NY-11377");
    }
}
