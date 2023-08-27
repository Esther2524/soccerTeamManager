
/**
 * This interface defines the operations that a soccer team can perform.
 */
public interface TeamModel {
  /**
   * Adds a new player to the team.
   *
   * @param firstName          The first name of the player.
   * @param lastName           The last name of the player.
   * @param year               The birth year of the player.
   * @param month              The birth month of the player.
   * @param day                The birthday of the player.
   * @param preferredPosition  The preferred position of the player.
   * @param skilledLevel       The skill level of the player.
   */
  void addPlayer(String firstName, String lastName, int year, int month, int day,
      String preferredPosition, int skilledLevel);

  /**
   * Checks if it is a valid team with a minimum of 10 players.
   *
   * @throws IllegalStateException If a valid team cannot be created.
   */
  void createValidTeam();

  /**
   * Removes a player from the team based on the jersey number.
   *
   * @param jerseyNumber The jersey number of the player to be removed.
   */
  String removePlayer(int jerseyNumber);

  /**
   * Retrieves a player from the team based on the jersey number.
   *
   * @param jerseyNumber The jersey number of the player to retrieve.
   * @return The player with the specified jersey number, or null if not found.
   */
  PlayerInterface getPlayerByJerseyNumber(int jerseyNumber);

  /**
   * Note: this method is only used for testing, not interacting with the controller.
   * Retrieves the jersey number associated with a specific player in the team.
   *
   * @param player The player for which to retrieve the jersey number.
   * @return The jersey number of the specified player,
   * or -1 if the player is not found in the team.
   */
  int getJerseyNumberByPlayer(PlayerInterface player);

  /**
   * Gets the current size of the team.
   *
   * @return The number of players in the team.
   */
  int getTeamSize();


  /**
   * Retrieves a formatted list of all players in the team.
   *
   * @return A formatted string containing details of all players in the team.
   */
  String getAllPlayersInTheTeam();


  /**
   * Retrieves a formatted list of players in the starting lineup.
   *
   * @return A formatted string containing details of players in the starting lineup.
   */
  String getStartingLineupList();

  /**
   * Retrieves a formatted list of players on the bench.
   *
   * @return A formatted string containing details of players on the bench.
   */
  String getBenchPlayers();
}
