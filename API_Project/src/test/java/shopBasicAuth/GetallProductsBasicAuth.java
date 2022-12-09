package shopBasicAuth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import libraries.BaseTest;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

public class GetallProductsBasicAuth extends BaseTest {
    @Test
    public void verifyValidGetAllProductsBasicAuth() {

        spec.basePath("/api/v1/products")
                .auth()
                .basic("david", "david123")
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].name", CoreMatchers.containsString("Thermos"))
                .header("Content-Type", "application/json");


    }
}
