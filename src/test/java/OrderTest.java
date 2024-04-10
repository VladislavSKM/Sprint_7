import client.OrderSteps;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    OrderSteps orderSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        orderSteps = new OrderSteps();
    }

    @Test
    @Description("Get list order  - status 200, Body:\"orders\", notNull")
    public void getOrderListNotNull() {
        Response response = orderSteps.getOrders();
        orderSteps.checkOrderListNotNull(response);
    }

}
