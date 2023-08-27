/**
 * The PlayerInterface represents the interface for a soccer player in a team.
 * It provides methods to access and modify the player's information such as
 * first name, last name, date of birth, preferred position, skill level, and actual position.
 */
public interface PlayerInterface {
  /**
   * Get the first name of the player.
   *
   * @return The first name of the player.
   */
  String getFirstName();

  /**
   * Set the first name of the player.
   *
   * @param firstName The first name to set for the player.
   */
  void setFirstName(String firstName);

  /**
   * Get the last name of the player.
   *
   * @return The last name of the player.
   */
  String getLastName();

  /**
   * Set the last name of the player.
   *
   * @param lastName The last name to set for the player.
   */
  void setLastName(String lastName);

  /**
   * Get the date of birth of the player in the format "YYYY-MM-DD".
   *
   * @return The date of birth of the player as a string.
   */
  String getDateOfBirth();

  /**
   * Set the date of birth of the player.
   *
   * @param year The year of birth.
   * @param month The month of birth.
   * @param day The day of birth.
   */
  void setDateOfBirth(int year, int month, int day);

  /**
   * Get the preferred position of the player in the team.
   *
   * @return The preferred position of the player..
   */
  Position getPreferredPosition();

  /**
   * Set the preferred position of the player in the team.
   *
   * @param preferredPosition The preferred position to set for the player.
   */
  void setPreferredPosition(Position preferredPosition);

  /**
   * Get the skill level of the player, ranging from 1 to 5.
   *
   * @return The skill level of the player.
   */
  int getSkilledLevel();

  /**
   * Set the skill level of the player.
   *
   * @param skilledLevel The skill level to set for the player, ranging from 1 to 5.
   */
  void setSkilledLevel(int skilledLevel);

  /**
   * Get the actual position of the player in the team.
   *
   * @return The actual position of the player.
   */
  Position getActualPosition();

  /**
   * Set the actual position of the player in the team.
   *
   * @param actualPosition The actual position to set for the player.
   *
   */
  void setActualPosition(Position actualPosition);


}
