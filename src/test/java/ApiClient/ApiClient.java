package ApiClient;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiClient {
    public String string;
    public static Response getObj(String url) {
        Response getObject = given()
                .header("Content-type", "application/json")
                .header("charset", "UTF-8")
                .baseUri(url)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
        return getObject;
    }

    public static JSONObject getCharByName(String name) {
        JSONArray array = new JSONObject(getObj(Utils.Configuration.getConfigurationValue("urlApi") + "/character")
                .getBody().asString()).getJSONArray("results");
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonobject = array.getJSONObject(i);
            if (jsonobject.getString("name").equals(name)) {
                return jsonobject;
            }
        }
        return null;
    }

}
