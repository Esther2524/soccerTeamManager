/**
 * A class representing the main program to manage a soccer team.
 */
public class Driver {

  /**
   *  The main method to demonstrate the functionality of the soccer team management system.
   * @param args Command-line arguments
   */
  public static void main(String[] args) {

    TeamModel team = new Team();
    team.addPlayer("James", "Garcia", 2015,
        5, 15, "FORWARD", 4);
    team.addPlayer("Kelvin", "Taylor", 2016,
        7, 1, "DEFENDER", 3);
    team.addPlayer("Maria", "Johnson", 2016,
        12, 10, "MIDFIELDER", 1);
    team.addPlayer("Sarah", "Williams", 2016,
        9, 5, "FORWARD", 4);
    team.addPlayer("Tyler", "Brown", 2016,
        4, 20, "DEFENDER", 2);
    team.addPlayer("Linda", "Miller", 2016,
        8, 12, "MIDFIELDER", 3);
    team.addPlayer("Nick", "Anderson", 2016,
        11, 28, "GOALIE", 4);
    team.addPlayer("Emily", "Martinez", 2016,
        3, 22, "DEFENDER", 3);
    team.addPlayer("Olivia", "Jones", 2016,
        1, 7, "FORWARD", 2);
    team.addPlayer("Henry", "Smith", 2015,
        5, 15, "FORWARD", 4);
    team.addPlayer("Elizabeth", "Martinez", 2015,
        5, 15, "GOALIE", 4);

    team.createValidTeam();

    System.out.println("Team Size:");
    System.out.println(team.getTeamSize());


    System.out.println("All Players in the Team:");
    System.out.println(team.getAllPlayersInTheTeam());

    String startingLineupList = team.getStartingLineupList();
    System.out.println("\nStarting Lineup List:");
    System.out.println(startingLineupList);

    System.out.println("\nBench Players:");
    String benchPlayers = team.getBenchPlayers();
    System.out.println(benchPlayers);

    System.out.println("\nAfter Remove:");

    // since the jersey number is random, so maybe there is not the player with 13
    System.out.println(team.removePlayer(13));
    System.out.println("\nThe Remaining Players in the Team:");
    System.out.println(team.getAllPlayersInTheTeam());
  }

}
