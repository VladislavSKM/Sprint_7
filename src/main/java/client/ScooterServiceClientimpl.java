package client;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static client.Constants.*;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;

public class ScooterServiceClientimpl {
    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;

    public ScooterServiceClientimpl(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        this.responseSpecification = responseSpecification;
        this.requestSpecification = requestSpecification;
    }

    @Step("Create courier")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(requestSpecification)
                .body(courier)
                .post(CREATE_USER_ENDPOINT)
                .then()
                .spec(responseSpecification);
    }

    @Step("Authorization courier")
    public ValidatableResponse login(Credetntials creditantials) {
        return given()
                .spec(requestSpecification)
                .body(creditantials)
                .post(LOGIN_ENDPOINT)
                .then()
                .spec(responseSpecification);
    }

    @Step("Delete courier")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(requestSpecification)
                .delete(DELETE_USER_ENDPOINT + id)
                .then()
                .spec(responseSpecification);
    }

}
