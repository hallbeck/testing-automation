package steps.api;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Random;

import static net.serenitybdd.rest.SerenityRest.given;

public class BatchControllerAPISteps {
    //NEW

    private Response response;


    @Step
    public void ea_post_product_batch_status() {

        String URL = "https://swapi.co/api/";

        Random rand = new Random();
        int productId = rand.nextInt(10000);

        int[] productIds = {rand.nextInt(10000), rand.nextInt(10000), rand.nextInt(10000),
                rand.nextInt(10000), rand.nextInt(10000), rand.nextInt(10000),
                rand.nextInt(10000), rand.nextInt(10000), rand.nextInt(10000),
                rand.nextInt(10000), rand.nextInt(10000), rand.nextInt(10000),
                rand.nextInt(10000), rand.nextInt(10000), rand.nextInt(10000),
                rand.nextInt(10000), rand.nextInt(10000), rand.nextInt(10000), rand.nextInt(10000)};

        JsonArrayBuilder productJsonBuilder = Json.createArrayBuilder();
        for (int productIdInArray : productIds) {
            productJsonBuilder.add(productIdInArray).build();
        }
        JsonObject ProductStatusBatchRequest = Json.createObjectBuilder()
                .add("productIds", productJsonBuilder.build())
                .build();

        System.out.println(ProductStatusBatchRequest);
//        given().contentType("application/json")
//                .body(ProductStatusBatchRequest.toString())
//                .when()
//                .post(URL + "/early/access/product/batch/status")
//                .then().statusCode(200);

        response = SerenityRest.when()
                .post(URL + "/early/access/product/batch/status");
        response.then().statusCode(200);
    }

}
