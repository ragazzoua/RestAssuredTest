import config.VideoGameConfig;
import config.VideoGamesEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameDbTest extends VideoGameConfig {

    @Test
    public void getAllGames() {
        given().when().get(VideoGamesEndpoints.ALL_VIDEO_GAMES).then().log().all();
    }

    @Test
    public void createNewGameByJson() {
        String gameBodyJson = "{\n" +
                "  \"id\": 112,\n" +
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
    public void deleteGame(){
        given()
                .when()
                .delete("videogames/2")
                .then();
    }

    @Test
    public void getSingleGame(){
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(VideoGamesEndpoints.SINGLE_VIDEO_GAMES)
                .then().;
    }

}
