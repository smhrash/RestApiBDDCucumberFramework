package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethod {

    public static JsonPath rawToJson(String getResponse){

        JsonPath jsonPath1 = new JsonPath(getResponse);

        return jsonPath1;
    }
}
