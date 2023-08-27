import java.awt.event.ActionListener;
import java.util.List;

/**
 * This interface defines the methods that a view in the U10 Soccer Team Manager should implement.
 */
public interface ViewInterface {

  /**
   * Switches to the start page.
   */
  void switchToStartPage();

  /**
   * Switches to the "Add Player" page.
   * @param currentPlayers List of current player information.
   * @param teamSize Current team size.
   */
  void switchToAddPage(String currentPlayers, int teamSize);

  /**
   * Retrieves player information from user input fields.
   * @return A list containing player information.
   */
  List<String> getPlayerInfo();

  /**
   * Clears player information input fields.
   */
  void clearPlayerInfoFields();

  /**
   * Displays a success message to the user.
   * @param message The success message to display.
   */
  void showSuccessMessage(String message);

  /**
   * Displays a failure message to the user.
   * @param message The failure message to display.
   */
  void showFailureMessage(String message);

  /**
   * Switches to the "Remove Player" page.
   * @param currentPlayers List of current player information.
   * @param teamSize Current team size.
   */
  void switchToRemovePage(String currentPlayers, int teamSize);

  /**
   * Clears jersey number input fields.
   */
  void clearJerseyNumberFields();

  /**
   * Retrieves jersey number from user input.
   * @return The jersey number entered by the user.
   */
  String getJerseyNumberFromUserInput();

  /**
   * Switches to the "Team Details" page.
   * @param currentPlayers List of current player information.
   * @param startingLineup The generated starting lineup.
   * @param playersOnBench Players on the bench.
   * @param teamSize Current team size.
   */
  void switchToTeamPage(String currentPlayers, String startingLineup,
      String playersOnBench, int teamSize);

  /**
   * Sets an action listener for handling user interactions.
   * @param listener The action listener to set.
   */
  void setActionListener(ActionListener listener);
}
