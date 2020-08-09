import config.VideoGamesEndpoints;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class GPathXmlTest extends TestConfig {

    @Test
    public void getFirstGameInTheList() {
        Response response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES);

        String name = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);
    }

    @Test
    public void getAttributeName() {
        Response response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES);
        String category = response.path("videoGames.videoGame[1].@category");

        System.out.println(category);
    }

    @Test
    public void getListOfXmlNodes(){
        String response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES).asString();

        List<Node> nodes = XmlPath.from(response)
                .get("videoGames.videoGame.findAll { element -> return element}");

        System.out.println(nodes.get(5).get("name").toString());
    }
}
