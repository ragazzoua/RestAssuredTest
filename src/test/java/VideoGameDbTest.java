import config.VideoGameConfig;
import config.VideoGamesEndpoints;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class VideoGameDbTest extends VideoGameConfig {

    @Test
    public void getAllGames() {
        given().when().get(VideoGamesEndpoints.ALL_VIDEO_GAMES).then().log().all();
    }

    @Test
    public void createNewGameByJson() {
        String gameBodyJson = "{\n" +
                "  \"id\": 113,\n" +
                "  \"name\": \"MyNewGame\",\n" +
                "  \"releaseDate\": \"2020-08-05T18:27:11.397Z\",\n" +
                "  \"reviewScore\": 88,\n" +
                "  \"category\": \"Shooter\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given()
                .body(gameBodyJson)
                .when()
                .post(VideoGamesEndpoints.ALL_VIDEO_GAMES)
                .then()
                .log()
                .all();
    }

    @Test
    public void createTestByXml() {
        String gameByXml = "<videoGame category=\"Driving\" rating=\"Universal\">\n" +
                "    <id>556</id>\n" +
                "    <name>Gran Turismo 5</name>\n" +
                "<releaseDate>2001-03-10T00:00:00Z</releaseDate>\n" +
                "    <reviewScore>99</reviewScore>\n" +
                "  </videoGame>";

        given()
                .body(gameByXml)
                .header("Accept", "application/xml")
                .header("Content-Type", "application/xml")
                .when()
                .post(VideoGamesEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void updateGame() {
        String gameBodyJson = "{\n" +
                "  \"id\": 556,\n" +
                "  \"name\": \"MyNewame\",\n" +
                "  \"releaseDate\": \"2020-08-05T18:27:11.400Z\",\n" +
                "  \"reviewScore\": 77,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Universal\"\n" +
                "}";

        given()
                .body(gameBodyJson)
                .when()
                .put("videogames/556")
                .then();
    }

    @Test
    public void deleteGame() {
        given()
                .when()
                .delete("videogames/2")
                .then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(VideoGamesEndpoints.SINGLE_VIDEO_GAMES)
                .then();
    }

    @Test
    public void testVideoGameSerializationByJson() {
        VideoGame videoGame = new VideoGame("22", "1984-06-25", "Tetris", "Universal", "2", "Puzzle");

        given()
                .body(videoGame)
                .when()
                .post(VideoGamesEndpoints.ALL_VIDEO_GAMES)
                .then();
    }


    @Test
    public void testVideoGameSchemaXml() {
        given()
                .pathParam("videoGameId", 5)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .when()
                .get(VideoGamesEndpoints.SINGLE_VIDEO_GAMES)
                .then()
                .body(matchesXsdInClasspath("VideoGameXsd.xsd"));
    }

    @Test
    public void testVideoGameSchemaJson() {
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(VideoGamesEndpoints.SINGLE_VIDEO_GAMES)
                .then()
                .body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    @Test
    public void convertJsonToPojo(){
        Response response = given().pathParam("videoGameId", 5)
                .when()
                .get(VideoGamesEndpoints.SINGLE_VIDEO_GAMES);

        VideoGame videoGame = response.getBody().as(VideoGame.class);

        System.out.println(videoGame.toString());
    }

    @Test
    public void captureResponseTime(){
        long responseTime = get(VideoGamesEndpoints.ALL_VIDEO_GAMES).time();
        System.out.println(responseTime);
    }

    @Test
    public void assertOnResponseTime(){
        when()
                .get(VideoGamesEndpoints.ALL_VIDEO_GAMES)
                .then()
                .time(lessThan(2000L));

    }
}
