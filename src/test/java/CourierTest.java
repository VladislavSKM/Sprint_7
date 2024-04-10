import client.*;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static client.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierTest {

    private Courier  courier;

    private ScooterServiceClient client;
    private int id;

    @Before
    public void createTestData(){
        client = new ScooterServiceClientimpl(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    }

    @After
    @Test
    @Description("Delete courier - status 201, Body:\"ok\" = true")
    public void deleteCourier(){
        courier = Courier.createRandom();
        client.create(courier);
        ValidatableResponse loginResponse = client.login(Credetntials.fromCourier(courier));
        id = loginResponse.extract().path("id");
        ValidatableResponse responseDelete = client.delete(id);
        responseDelete.assertThat().statusCode(200).and().body("ok", equalTo(true));
    }

    @Test
    @Description("Create courier (successful) - status 201, Body:\"ok\" = true")
    public void createCourierSuccessTest() {
        courier = Courier.createRandom();
        ValidatableResponse response = client.create(courier);
        response.assertThat().statusCode(201).and().body("ok", equalTo(true));
    }

    @Test
    @Description("Create courier without password - status 400")
    public void createCourierWithoutPassTest() {
        courier = Courier.createRandomWithoutPass();
        ValidatableResponse response = client.create(courier);
        response.assertThat().statusCode(400);
        response.assertThat().body("message", Matchers.equalTo(INSUFFICIENT_DATA_MESSAGE));
    }

    @Test
    @Description("Create courier_is already in DB - status 409")
    public void createCourierAlreadyUse() {
        courier = new Courier("Vlad28","Qq12345678","Onshapes");
        ValidatableResponse response = client.create(courier);
        response.assertThat().statusCode(409);
        response.assertThat().body("message", Matchers.equalTo(LOGIN_ALREADY_USE_MESSAGE));
    }

}
