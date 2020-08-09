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
    public void getListOfXmlNodes() {
        String response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES).asString();

        List<Node> nodes = XmlPath.from(response)
                .get("videoGames.videoGame.findAll { element -> return element}");

        System.out.println(nodes.get(5).get("name").toString());
    }

    @Test
    public void getListOfXmlNodesByFindAllOnAttributes() {
        String response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES).asString();

        List<Node> nodes = XmlPath.from(response)
                .get("videoGames.videoGame.findAll { videoGame -> def category = videoGame.@category; category == 'Driving'}");

        System.out.println(nodes.get(0).get("name").toString());
    }

    @Test
    public void getSingleNode() {
        String response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES).asString();

        Node videoGame = XmlPath.from(response)
                .get("videoGames.videoGame.find { videoGame -> def name  = videoGame.name; name == 'Tetris'}");

        String name = videoGame.get("name").toString();
        System.out.println(name);
    }

    @Test
    public void getSingleElementDepthFirst() {

        String response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES).asString();

        int reviewScore = XmlPath.from(response).getInt("**.find {it.name == 'Tetris'}.reviewScore");

        System.out.println(reviewScore);

    }

    @Test
    public void getAllNodesBasedOnACondition() {

        String response = get(VideoGamesEndpoints.ALL_VIDEO_GAMES).asString();

        int reviewScore = 90;

        List<Node> allVideoGamesOverCertainScore = XmlPath.from(response)
                .get("videoGames.videoGame.findAll {it.reviewScore.toFloat() >= " + reviewScore + "}");

        System.out.println(allVideoGamesOverCertainScore);
    }
}
