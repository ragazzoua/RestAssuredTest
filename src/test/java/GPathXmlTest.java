import config.VideoGamesEndpoints;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;

public class GPathXmlTest extends TestConfig {

    @Test
    public void getFirstGameInTheList(){
        Response response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES);

        String name  = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);
    }
}
