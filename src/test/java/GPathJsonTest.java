import config.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJsonTest extends FootballApiConfig {

    @Test
    public void extractMapOfElementsWithFind() {

        Response response = get("competitions/2021/teams");

        Map<String, ?> allTeamDataForSingleTeam = response
                .path("teams.find { it.name == 'Manchester United FC'}");

        for (Map.Entry<String, ?> data : allTeamDataForSingleTeam.entrySet()) {
            System.out.println(data.getKey() + " - " + data.getValue());
        }

        System.out.println(allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind() {
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find {it.shirtNumber == 23}.name");

        System.out.println("Player " + certainPlayer);
    }

    @Test
    public void extractListValuesFindAll(){
        Response response = get("teams/57");
        List<String> certainPlayers = response.path("squad.findAll {it.shirtNumber >= 23}.name");

        System.out.println("Player " + certainPlayers.toString());
    }
}
