package steps.api;

import net.thucydides.core.annotations.Step;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Random;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

public class ProductControllerAPISteps {

    //GET
    @Step
    public void StatusAPIcall() {
        String URL = "https://swapi.co/api/";

        given().param("productId", 9809209)
                .when()
                .get(URL + "/early/access/product/" + 9809209)
                .then().statusCode(200)
                .body("productId", is(9809209))
                .body("isActive", is(false))
                .body("startDate", isEmptyOrNullString())
                .body("endDate", isEmptyOrNullString());

    }

    //GET
    @Step
    public void StatusAPIcall2() {
        String URL = "https://swapi.co/api/";

        given().param("productId", 9809209)
                .when()
                .get(URL + "/early/access/product/" + 9809209)
                .then()
                .statusCode(200)
                .body("productId", is(9809209));
    }

    //GET
    @Step
    public void StatusAPIcallNull() {
        String URL = "https://swapi.co/api/";

        given().when()
                .get(URL + "/early/access/product/")
                .then()
                .statusCode(404);
    }

    //GET
    @Step
    public void StatusAPIcallNotInt() {
        String URL = "https://swapi.co/api/";

        given().param("productId", "abc123")
                .when()
                .get(URL + "/early/access/product/abc123")
                .then()
                .statusCode(400);
    }

    //PUT (Update)
    @Step
    public void UpdateProductStatusHappy() {
        String URL = "https://swapi.co/api/";
        int productId = 0;
        JsonObject ProductInfo = Json.createObjectBuilder()
                .add("endDate", "2019-09-06T16:59:25.306Z")
                .add("isActive", true)
                .add("productId", productId)
                .add("startDate", "2019-09-06T16:59:25.306Z")
                .build();
        JsonObject productUpdateRequest = Json.createObjectBuilder()
                .add("Product", ProductInfo)
                .add("productId", productId)
                .build();


        System.out.println(productUpdateRequest);
        given().contentType("application/json")
                .body(productUpdateRequest.toString())
                .when()
                .put(URL + "/early/access/product/" + productId)
                .then().statusCode(200);
    }

    //PUT (Update)
    @Step
    public void UpdateProductStatusIsActiveNull() {
        String URL = "https://swapi.co/api/";
        int productId = 0;
        JsonObject ProductInfo = Json.createObjectBuilder()
                .add("endDate", "2019-09-06T16:59:25.306Z")
                .add("isActive", JsonValue.NULL)
                .add("productId", productId)
                .add("startDate", "2019-09-06T16:59:25.306Z")
                .build();
        JsonObject productUpdateRequest = Json.createObjectBuilder()
                .add("Product", ProductInfo)
                .add("productId", productId)
                .build();


        System.out.println(productUpdateRequest);
        given().contentType("application/json")
                .body(productUpdateRequest.toString())
                .when()
                .put(URL + "/early/access/product/" + productId)
                .then().statusCode(200);
    }

    //PUT (Update)
    @Step
    public void UpdateProductStatusStartDateNull() {
        String URL = "https://swapi.co/api/";
        int productId = 0;
        JsonObject ProductInfo = Json.createObjectBuilder()
                .add("endDate", "2019-09-06T16:59:25.306Z")
                .add("isActive", true)
                .add("productId", productId)
                .add("startDate", JsonValue.NULL)
                .build();
        JsonObject productUpdateRequest = Json.createObjectBuilder()
                .add("Product", ProductInfo)
                .add("productId", productId)
                .build();


        System.out.println(productUpdateRequest);
        given().contentType("application/json")
                .body(productUpdateRequest.toString())
                .when()
                .put(URL + "/early/access/product/" + productId)
                .then().statusCode(200);
    }

    //PUT (Update)
    @Step
    public void UpdateProductStatusEndDateNull() {
        String URL = "https://swapi.co/api/";
        int productId = 0;
        JsonObject ProductInfo = Json.createObjectBuilder()
                .add("startDate", "2019-09-06T16:59:25.306Z")
                .add("isActive", true)
                .add("productId", productId)
                .add("endDate", JsonValue.NULL)
                .build();
        JsonObject productUpdateRequest = Json.createObjectBuilder()
                .add("Product", ProductInfo)
                .add("productId", productId)
                .build();


        System.out.println(productUpdateRequest);
        given().contentType("application/json")
                .body(productUpdateRequest.toString())
                .when()
                .put(URL + "/early/access/product/" + productId)
                .then().statusCode(200);
    }

    //PUT (Update)
    @Step
    public void UpdateProductStatusInvalidProducId() {
        String URL = "https://swapi.co/api/";
        int productId = -0;
        JsonObject ProductInfo = Json.createObjectBuilder()
                .add("endDate", "2019-09-06T16:59:25.306Z")
                .add("isActive", false)
                .add("productId", productId)
                .add("startDate", "2019-09-06T16:59:25.306Z")
                .build();
        JsonObject productUpdateRequest = Json.createObjectBuilder()
                .add("Product", ProductInfo)
                .add("productId", productId)
                .build();


        System.out.println(productUpdateRequest);
        given().contentType("application/json")
                .body(productUpdateRequest.toString())
                .when()
                .put(URL + "/early/access/product/" + productId)
                .then().statusCode(200);
    }

    //PUT (Update)
    @Step
    public void UpdateProductStatusNullProducId() {
        String URL = "https://swapi.co/api/";
        JsonValue productId = JsonValue.NULL;
        JsonObject ProductInfo = Json.createObjectBuilder()
                .add("endDate", "2019-09-06T16:59:25.306Z")
                .add("isActive", false)
                .add("productId", productId)
                .add("startDate", "2019-09-06T16:59:25.306Z")
                .build();
        JsonObject productUpdateRequest = Json.createObjectBuilder()
                .add("Product", ProductInfo)
                .add("productId", productId)
                .build();


        System.out.println(productUpdateRequest);
        given().contentType("application/json")
                .body(productUpdateRequest.toString())
                .when()
                .put(URL + "/early/access/product/" + productId)
                .then().statusCode(400);
    }


    @Step
    public void RetrieveStatus() {
        String URL = "https://swapi.co/api/";

        Random rand = new Random();
        int productId = rand.nextInt(10000);

        JsonArray productJsons = Json.createArrayBuilder().add(productId).build();
        JsonObject ProductStatusBatchRequest = Json.createObjectBuilder()
                .add("productIds", productJsons)
                .build();

        System.out.println(ProductStatusBatchRequest);
        given().contentType("application/json")
                .body(ProductStatusBatchRequest.toString())
                .when()
                .post(URL + "/early/access/product/batch/status")
                .then().statusCode(200);
    }

}