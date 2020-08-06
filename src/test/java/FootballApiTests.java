import config.FootballApiConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballApiTests extends FootballApiConfig {

    @Test
    public void getDetailOfOneArea() {
        given()
                .queryParam("areas", 2255)
                .when()
                .get("areas");
    }

    @Test
    public void getDateFounded() {
        given()
                .when()
                .get("teams/57")
                .then()
                .body("founded", equalTo(1886));
    }


}
