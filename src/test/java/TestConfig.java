import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class TestConfig {

    @BeforeClass
    public static void setup() {
        //RestAssured.proxy("localhost", 8888); // it is  setting for fiddler you should install fiddler on your pc
        RestAssured.baseURI = "http://video-game-db.eu-west-2.elasticbeanstalk.com";
        RestAssured.basePath = "/app/";

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        RestAssured.requestSpecification = requestSpecification;

    }
}
