package client;

import io.restassured.response.ValidatableResponse;

public interface ScooterServiceClient {
    ValidatableResponse create(Courier courier);

    ValidatableResponse login(Credetntials creditantials);

    ValidatableResponse  delete(int id);

}
