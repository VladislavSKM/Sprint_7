package client;
import io.restassured.response.Response;
import static client.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import io.qameta.allure.Step;

public class OrderSteps {

    @Step("Get orders")
    public Response getOrders() {
        return given().relaxedHTTPSValidation()
                .log().all()
                .header("Content-type", "application/json")
                .when()
                .get(CREATE_ORDER)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    @Step("getOrders body contains list - status 200, list notNullValue")
    public void checkOrderListNotNull(Response response) {
        response.then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("orders", notNullValue());
    }

}
