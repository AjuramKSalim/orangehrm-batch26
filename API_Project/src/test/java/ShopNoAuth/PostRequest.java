package ShopNoAuth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import libraries.BaseTest;
import models.CreateProductClass;
import org.testng.annotations.Test;

import java.io.File;

public class PostRequest extends BaseTest {

    @Test
    public void createProduct() {

        spec.basePath("/api/v1/products")

                .body(new File("src/main/resources/jsondataNew/CreateProduct.json"))
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all();

    }

    @Test
    public void createProductUsingPOJO() {
        CreateProductClass product = new CreateProductClass();
        product.setName("Pen");
        product.setDescription("Ink Pen");
        product.setPrice(5f);

        spec.basePath("/api/v1/products")

                .body(product)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .all();

    }
}
