package ShopNoAuth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import libraries.BaseTest;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

public class GetAllProducts extends BaseTest {
    @Test
    public void verifyValidGetAllProducts() {

        spec.basePath("/api/v1/products")

                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].name", CoreMatchers.containsString("Thermos"))
                .header("Content-Type", "application/json");


    }


}
