import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest {

    @Test
    public void myFirstTest() {
        given().log().all()
                .when().get("http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames")
                .then()
                .log().all();
    }
}
