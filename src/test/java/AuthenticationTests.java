import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTests {

    @BeforeClass
    public static void setup() {
        //RestAssured.proxy("localhost", 8888); // it is  setting for fiddler you should install and run fiddler on your pc
    }

    @Test
    public void basicPreemptiveAuthTests() {
        given().auth().preemptive().basic("username", "password")
                .when()
                .get("http://localhost:8080/someEndpoint");
    }

    @Test
    public void basicChallengeAuthTest() {
        given().auth().basic("username", "password")
                .when()
                .get("http://localhost:8080/someEndpoint");
    }

    @Test
    public void oauth1Test() {
        given().auth().oauth("key", "secret", "token", "token")
                .when()
                .get("http://localhost:8080/someEndpoint");
    }

    @Test
    public void oauth2Test() {
        given().auth().oauth2("token")
                .when()
                .get("http://localhost:8080/someEndpoint");
    }

    @Test
    public void relaxedHttpsTest() {
        given().relaxedHTTPSValidation()
                .when()
                .get("http://localhost:8080/someEndpoint");
    }
}
