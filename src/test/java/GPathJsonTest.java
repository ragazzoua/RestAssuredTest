import config.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJsonTest extends FootballApiConfig {

    @Test
    public void extractMapOfElementsWithFind() {

        Response response = get("competitions/2021/teams");

        Map<String, ?> allTeamDataForSingleTeam = response
                .path("teams.find { it.name == 'Manchester United FC'}");

        for (Map.Entry<String, ?> data: allTeamDataForSingleTeam.entrySet()){
            System.out.println(data.getKey() + " - " + data.getValue());
        }

        System.out.println(allTeamDataForSingleTeam);
    }
}
