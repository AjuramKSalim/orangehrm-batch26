package libraries;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public RequestSpecification spec;

    @BeforeTest
    public void restAssured() {
        spec = RestAssured
                .given()
                .baseUri("http://localhost:8090")
                .contentType(ContentType.JSON);
    }
}
