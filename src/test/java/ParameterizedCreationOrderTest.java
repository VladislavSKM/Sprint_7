import client.Order;
import io.qameta.allure.Description;
import io.restassured.RestAssured;;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static client.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class ParameterizedCreationOrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String colorForHeader;
    private final String[] color;

    public ParameterizedCreationOrderTest(String firstName,
                                          String lastName,
                                          String address,
                                          int metroStation,
                                          String phone,
                                          int rentTime,
                                          String deliveryDate,
                                          String comment,
                                          String colorForHeader,
                                          String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colorForHeader = colorForHeader;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Test {index} Цвет самоката: {8}")
    public static Object[][] getTestData() {

        return new Object[][]{
                {"Semen", "Semenov", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2024-04-06", "Saske, come back to Konoha", "Черный",         new String[]{"BLACK"}},
                {"Ivan",  "Ivanov",  "Konoha, 142 apt.", 4, "+7 800 555 35 35", 4, "2024-05-07", "Saske, come back to Konoha", "Серый и Черный", new String[]{"GREY", "BLACK"}},
                {"Oleg",  "Olegov",  "Konoha, 142 apt.", 4, "+7 800 666 35 35", 3, "2024-06-07", "Saske, come back to Konoha", "Цвет не выбран", new String[]{""}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @Description("Create order")
    public void creationOrders(){
        Order order = new Order(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color);

              given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(CREATE_ORDER_ENDPOINT)
                .then()
                .assertThat()
                .body("track", notNullValue())
                .statusCode(201);
    }
}