import config.FootballApiConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FootballApiTests extends FootballApiConfig {

    @Test
    public void getDetailOfOneArea(){
        given()
                .queryParam("areas", 2255)
                .when()
                .get("areas")
        ;
    }
}
