import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests for the Team class, which represents a soccer team.
 */
public class TeamTest {

  TeamModel team, team2;
  /**
   * Set up some objects of type Team.
   */
  @Before
  public void setUp() {
    team = new Team();
    team2 = new Team();
  }


  /**
   * Tests that an IllegalStateException is thrown when the team size is less than ten players.
   *
   * @throws IllegalStateException When an attempt is made to create a valid team with less than ten players.
   */
  @Test (expected = IllegalStateException.class)
  public void testTeamSizeLessThanTen() {
    team.addPlayer("James", "Smith", 2018, 5,
        15, "FORWARD", 4);
    team.addPlayer("Maria", "Garcia", 2015, 3,
        4, "GOALIE", 5);
    team.addPlayer("Kelvin", "Hernandez:", 2018, 6,
        11, "DEFENDER", 3);
    team.createValidTeam();
  }

  /**
   * Tests the addPlayer() method and getTeamSize() method.
   */
  @Test
  public void testAddPlayerAndGetTeamSize() {
    team.addPlayer("James", "Garcia", 2015,
        6, 21, "FORWARD", 4);
    Assert.assertEquals(1, team.getTeamSize());

    team.addPlayer("Kelvin", "Taylor", 2016,
        7, 1, "Defender", 3);
    team.addPlayer("Maria", "Johnson", 2016,
        12, 10, "MIDFIELDER", 1);
    team.addPlayer("Sarah", "Williams", 2017,
        9, 5, "FORWARD", 1);
    team.addPlayer("Tyler", "Brown", 2016,
        4, 20, "DEFENDER", 2);
    Assert.assertEquals(5, team.getTeamSize());

    team.addPlayer("Linda", "Miller", 2016,
        8, 12, "Midfielder", 3);
    team.addPlayer("Nick", "Anderson", 2016,
        11, 28, "GOALIE", 4);
    team.addPlayer("Emily", "Martinez", 2017,
        3, 22, "DEFENDER", 3);
    team.addPlayer("Olivia", "Jones", 2016,
        1, 7, "FORWARD", 2);
    team.addPlayer("Henry", "Smith", 2015,
        5, 15, "forward", 4);
    Assert.assertEquals(10, team.getTeamSize());

    team.addPlayer("Elizabeth", "Martinez", 2015,
        4, 2, "Goalie", 4);
    Assert.assertEquals(11, team.getTeamSize());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidInputPosition() {
    team.addPlayer("Elizabeth", "Martinez", 2015,
        4, 2, "none", 4);
  }

  /**
   * Adds twenty players to the team for testing purposes.
   */
  private void addTwentyPlayers() {
    team2.addPlayer("James", "Garcia", 2015,
        5, 15, "FORWARD", 4);
    team2.addPlayer("Kelvin", "Taylor", 2016,
        7, 1, "DEFENDER", 3);
    team2.addPlayer("Maria", "Johnson", 2016,
        12, 10, "MIDFIELDER", 1);
    team2.addPlayer("Sarah", "Williams", 2016,
        9, 5, "FORWARD", 4);
    team2.addPlayer("Tyler", "Brown", 2016,
        4, 20, "DEFENDER", 2);
    team2.addPlayer("Linda", "Miller", 2016,
        8, 12, "MIDFIELDER", 3);
    team2.addPlayer("Nick", "Anderson", 2016,
        11, 28, "GOALIE", 4);
    team2.addPlayer("Emily", "Martinez", 2016,
        3, 22, "DEFENDER", 3);
    team2.addPlayer("Olivia", "Jones", 2016,
        1, 7, "FORWARD", 2);
    team2.addPlayer("Henry", "Smith", 2015,
        5, 15, "FORWARD", 4);
    team2.addPlayer("Elizabeth", "Martinez", 2015,
        5, 15, "GOALIE", 4);
    team2.addPlayer("AS", "HJ", 2016,
        2, 18, "DEFENDER", 3);
    team2.addPlayer("D", "F", 2017,
        1, 29, "DEFENDER", 3);
    team2.addPlayer("HH", "IO", 2017,
        6, 7, "forward", 3);
    team2.addPlayer("QW", "ER", 2016,
        3, 19, "GOALIE", 3);
    team2.addPlayer("TY", "IO", 2017,
        9, 2, "forward", 3);
    team2.addPlayer("P", "AS", 2015,
        10, 30, "GOALIE", 3);
    team2.addPlayer("DF", "GH", 2015,
        5, 11, "GOALIE", 3);
    team2.addPlayer("JK", "KL", 2014,
        7, 22, "MIDFIELDER", 3);
    team2.addPlayer("CV", "VB", 2018,
        2, 18, "MIDFIELDER", 3);
  }

  /**
   * Tests the removePlayer() method.
   */
  @Test
  public void testRemovePlayer() {
    addTwentyPlayers();
    team2.removePlayer(5);
    Assert.assertEquals(19, team2.getTeamSize());
  }

  /**
   * Tests the uniqueness of jersey numbers assigned to players in the team.
   * It ensures that all jersey numbers are unique and correspond to existing players.
   */
  @Test
  public void testUniqueJerseyNumber() {
    addTwentyPlayers();

    Set<Integer> jerseyNumbers = new HashSet<>();
    for (int i = 1; i <= 20; i++) {
      PlayerInterface player = team2.getPlayerByJerseyNumber(i);
      Assert.assertNotNull(player); // check if the player with the jersey number exists

      jerseyNumbers.add(team2.getJerseyNumberByPlayer(player));
    }

    // check if all jersey numbers are unique
    Assert.assertEquals(20, jerseyNumbers.size());
  }

  /**
   * Tests the removal of the lowest skilled level player from a full team.
   * In this test case, the "A, A" should be removed based on its skilledLevel and the name.
   */
  @Test
  public void testRemoveLowestSkilledLevelPlayerInFullTeam() {
    addTwentyPlayers();
    team2.addPlayer("A", "A", 2018,
        2, 18, "MIDFIELDER", 1);
    String output = team2.getAllPlayersInTheTeam();

    // output string doesn't contain "A, A"
    Assert.assertFalse(output.contains("A, A"));
  }

  /**
   * Tests the removal of the lowest skilled level player from a full team.
   * In this test case, the "Maria, Johnson" will be removed.
   */
  @Test
  public void testRemoveLowestSkilledLevelPlayerInFullTeamWithoutMoving() {
    addTwentyPlayers();
    team2.addPlayer("Z", "Z", 2018,
        2, 18, "MIDFIELDER", 1);
    String output = team2.getAllPlayersInTheTeam();
    Assert.assertTrue(output.contains("Z, Z"));
    Assert.assertFalse(output.contains("Maria, Johnson"));
    System.out.println(team2.getAllPlayersInTheTeam());
  }

  /**
   * Test case to verify that attempting to remove a player from an empty team.
   * Expects an IllegalStateException to be thrown.
   */
  @Test(expected = IllegalStateException.class)
  public void testRemovePlayerInEmptyTeam() {
    team.removePlayer(2);
  }

  /**
   * Adds ten players to the team for testing purposes.
   */
  private void addTenPlayers() {
    team.addPlayer("A", "A", 2016,
        7, 21, "Goalie", 5);
    team.addPlayer("B", "B", 2016,
        8, 17, "Defender", 3);
    team.addPlayer("C", "C", 2015,
        7, 10, "Defender", 4);
    team.addPlayer("D", "D", 2018,
        9, 12, "Defender", 4);
    team.addPlayer("E", "E", 2017,
        7, 3, "Midfielder", 5);
    team.addPlayer("F", "F", 2016,
        3, 14, "Midfielder", 3);
    team.addPlayer("G", "G", 2016,
        7, 21, "Forward", 5);
    team.addPlayer("H", "H", 2016,
        6, 26, "Forward", 1);
    team.addPlayer("I", "I", 2015,
        5, 1, "Defender", 2);
    team.addPlayer("J", "J", 2015,
        1, 13, "Defender", 3);
  }

  /**
   * Test the getAllPlayersInTheTeam()` method.
   * Adds ten players to the team and checks if their information is correctly included
   * in the output.
   * Cannot check the random jersey numbers.
   */
  @Test
  public void testGetAllPlayersInTheTeam() {
    addTenPlayers();
    String output = team.getAllPlayersInTheTeam();
    Assert.assertTrue(output.contains("A, A, Jersey Number"));
    Assert.assertTrue(output.contains("B, B, Jersey Number"));
    Assert.assertTrue(output.contains("C, C, Jersey Number"));
    Assert.assertEquals(10, output.split("\n").length);
//    System.out.println(team.getAllPlayersInTheTeam());
  }


  /**
   * Tests the getStartingLineupList() method.
   */
  @Test
  public void testGetStartingLineupList() {
    addTenPlayers();
    String output = team.getStartingLineupList();
    // A is the only one Forward in the team
    Assert.assertTrue(output.contains("A, A"));
    Assert.assertEquals(7, output.split("\n").length);
    System.out.println(team.getStartingLineupList());
  }


  /**
   * Tests the getBenchPlayers() method.
   */
  @Test
  public void testGetBenchPlayers() {
    addTenPlayers();
    team.getStartingLineupList();
    String output = team.getBenchPlayers();
    Assert.assertEquals(3, output.split("\n").length);
//    System.out.println(team.getBenchPlayers());
  }

  /**
   * Adds twenty players with the same preferred Position to the team for testing purposes.
   */
  private void addTenSamePreferredPositionPlayers() {
    team.addPlayer("A", "A", 2016,
        7, 21, "Forward", 5);
    team.addPlayer("B", "B", 2016,
        8, 17, "Forward", 3);
    team.addPlayer("C", "C", 2015,
        7, 10, "Forward", 4);
    team.addPlayer("D", "D", 2018,
        9, 12, "Forward", 4);
    team.addPlayer("E", "E", 2017,
        7, 3, "Forward", 5);
    team.addPlayer("F", "F", 2016,
        3, 14, "Forward", 3);
    team.addPlayer("G", "G", 2016,
        7, 21, "Forward", 5);
    team.addPlayer("H", "H", 2016,
        6, 26, "Forward", 1);
    team.addPlayer("I", "I", 2015,
        5, 1, "Forward", 2);
    team.addPlayer("J", "J", 2015,
        1, 13, "Forward", 3);
  }
  /**
   * Tests the case where all the players' preferred position is forward.
   * At this point, the team still can be created.
   */
  @Test
  public void testCaseOfAllPlayersAreTheSamePreferredPosition() {
    addTenSamePreferredPositionPlayers();
//    System.out.println(team.getStartingLineupList());
    Assert.assertEquals(10, team.getAllPlayersInTheTeam().split("\n").length);
    Assert.assertEquals(7, team.getStartingLineupList().split("\n").length);
    Assert.assertEquals(3, team.getBenchPlayers().split("\n").length);

  }



}
