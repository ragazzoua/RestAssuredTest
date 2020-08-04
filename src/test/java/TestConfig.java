import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestConfig {

    @BeforeClass
    public static void setup(){
        RestAssured.proxy("localhost", 8888); // it setting for fiddler you should install fiddler on your pc
        RestAssured.baseURI = "http://video-game-db.eu-west-2.elasticbeanstalk.com";
        RestAssured.basePath = "/app/";

    }
}
