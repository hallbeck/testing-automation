package steps.api;

import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class HealthAPISteps {
    @Step
    public void verifyThatAppIsRunning() {
        given()
                .get("https://swapi.co/api/").then().statusCode(200);
    }
}