import client.OrderSteps;
import client.ScooterServiceClientimpl;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static client.Constants.REQUEST_SPECIFICATION;
import static client.Constants.RESPONSE_SPECIFICATION;

public class OrderTest {
    OrderSteps orderSteps;

    @Before
    public void createTestData() {
        orderSteps = new OrderSteps(REQUEST_SPECIFICATION, RESPONSE_SPECIFICATION);
    }

    @Test
    @Description("Get list order  - status 200, Body:\"orders\", notNull")
    public void getOrderListNotNull() {
        ValidatableResponse response = orderSteps.getOrders();
        orderSteps.checkOrderListNotNull(response);
    }

}
