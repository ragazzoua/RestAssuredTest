import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setup() {
        //RestAssured.proxy("localhost", 8888); // it is  setting for fiddler you should install fiddler on your pc
        RestAssured.baseURI = "http://video-game-db.eu-west-2.elasticbeanstalk.com";
        RestAssured.basePath = "/app/";

        requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/xml")
                .addHeader("Accept", "application/xml")
                //.addFilter(new RequestLoggingFilter())
                //.addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.requestSpecification = requestSpecification;

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.responseSpecification = responseSpecification;

    }
}
