package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BookData")
    public void addBook(String isbn,String aisle){

        RestAssured.baseURI="http://216.10.245.166";
        String response =given().header("Content-Type","application/json").body(Payload.addBook(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath jsonPath = ReUsableMethod.rawToJson(response);

        String id =jsonPath.get("ID");
        System.out.println(id);




    }
   @DataProvider(name ="BookData")
    public Object[][] getData(){

        return new Object [][] {{"jhggff","244322"},{"jhggfgff","24488322"},{"jhghggff","24432542"}};
   }




}
