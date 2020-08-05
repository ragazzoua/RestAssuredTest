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

}
