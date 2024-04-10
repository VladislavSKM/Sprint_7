import client.*;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static client.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;
public class LoginTest {

    private Courier  courier;
    private Credetntials credetntials;
    private ScooterServiceClient client;
    private int id;

    @Before
    public void createTestData(){
        client = new ScooterServiceClientimpl(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    }

    @After
    public void cleanUp(){
        client.delete(id);
    }

    @Test
    @Description("Login courier (successful) - status 200, id is correctly")
    public void loginCourierSuccessTest() {
        courier = Courier.createRandom();
        client.create(courier);
        ValidatableResponse loginResponse = client.login(Credetntials.fromCourier(courier));
        id = loginResponse.extract().path("id");
        loginResponse.assertThat().statusCode(200).and().body("id", equalTo(id));
    }

    @Test
    @Description("Login courier_Account not found - status 404")
    public void loginCourierNotFound() {
        credetntials = new Credetntials("Vlad111","NULL");
        ValidatableResponse response = client.login(credetntials);
        response.assertThat().statusCode(404);
        response.assertThat().body("message", Matchers.equalTo(CREDETANTIALS_NOT_FOUND_MESSAGE));
    }

}
