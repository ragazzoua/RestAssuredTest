import config.FootballApiConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

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
    public void extractListValuesFindAll() {
        Response response = get("teams/57");
        List<String> certainPlayers = response.path("squad.findAll {it.shirtNumber >= 23}.name");
        System.out.println("Players " + certainPlayers.toString());
    }

    @Test
    public void extractSingleValueWithHighestNumber() {
        Response response = get("teams/57");
        String playerName = response.path("squad.max {it.shirtNumber}.name");
        System.out.println(playerName);
    }

    @Test
    public void extractMultipleValuesAndSumThem() {
        Response response = get("teams/57");
        int sumOfIds = response.path("squad.collect { it.id }.sum()");
        System.out.println(sumOfIds);
    }

    @Test
    public void extractObjectWithFindAndFindAll() {
        Response response = get("teams/57");
        Map<String, ?> playerOfCertainPosition = response
                .path("squad.findAll { it.position == 'Defender'}.find { it.nationality = 'Greece' }");

        for (Map.Entry<String, ?> data : playerOfCertainPosition.entrySet()) {
            System.out.println(data.getKey() + " - " + data.getValue());
        }
    }

    @Test
    public void extractObjectWithFindAndFindAllParameters() {
        String position = "Defender";
        String nationality = "Greece";

        Response response = get("teams/57");
        Map<String, ?> playerOfCertainPosition = response
                .path("squad.findAll { it.position == '%s'}.find { it.nationality = '%s' }", position, nationality);

        for (Map.Entry<String, ?> data : playerOfCertainPosition.entrySet()) {
            System.out.println(data.getKey() + " - " + data.getValue());
        }
    }

    @Test
    public void extractMultiplePlayers() {
        String position = "Midfielder";
        String nationality = "England";

        Response response = get("teams/57");

        List<Map<String, ?>> allPlayersCertainNationality = response
                .path("squad.findAll { it.position == '%s'}.findAll { it.nationality = '%s' }", position, nationality);

        for (Map<String, ?> listItem : allPlayersCertainNationality) {
            for (Map.Entry<String, ?> stringEntry : listItem.entrySet()) {
                System.out.println(((Map.Entry) stringEntry).getKey() + " = " + ((Map.Entry) stringEntry).getValue());
            }
        }
    }
}
