package steps.api;

import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class EarlyAccessHealthAPISteps {
    @Step
    public void verifyThatAppIsRunning() {
        given()
                .get("http://k8s-master.cluboautomation.test.ostk.com:32005/health").then().statusCode(200);
    }
}