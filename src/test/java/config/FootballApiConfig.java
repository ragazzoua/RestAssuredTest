package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class FootballApiConfig {

    private static RequestSpecification footballRequestSpecification;
    private static ResponseSpecification footballResponseSpecification;

    @BeforeClass
    public static void  setup(){
        footballRequestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org/")
                .setBasePath("/v2/")
                .addHeader("X-Auth-Token", "505f35e412dc44f291919a1b3ae24301")
                .addHeader("X-Response-Control", "minified")
                .build();

        footballResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.requestSpecification = footballRequestSpecification;
        RestAssured.responseSpecification = footballResponseSpecification;
    }
}
