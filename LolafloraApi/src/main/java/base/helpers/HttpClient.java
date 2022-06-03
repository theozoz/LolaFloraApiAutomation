package base.helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static base.constant.BaseConstants.LOLAFLORA_TEST_API_URL;
import static io.restassured.RestAssured.given;

public class HttpClient {

    public static Response get(String path) {

        return given()
                .log().all()
                .baseUri(LOLAFLORA_TEST_API_URL)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public static Response get(String path, Map<String, ?> query) {

        return given()
                .log().all()
                .baseUri(LOLAFLORA_TEST_API_URL)
                .contentType(ContentType.JSON)
                .queryParams(query)
                .when()
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

}
