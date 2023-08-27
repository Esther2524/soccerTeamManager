import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.util.List;

/**
 * The SwingTeamController class implements the ControllerInterface and ActionListener interface,
 * providing control logic for the team management application's GUI.
 */
public class SwingTeamController implements ControllerInterface, ActionListener {
  private final TeamModel model;
  private final ViewInterface view;

  /**
   * Constructs a SwingTeamController object.
   * @param model The TeamModel instance representing the application's data model.
   * @param view The ViewInterface instance representing the graphical user interface.
   */
  public SwingTeamController(TeamModel model, ViewInterface view) {
    this.model = model;
    this.view = view;
  }


  /**
   * Starts the controller, setting the controller as the action listener for GUI components.
   */
  @Override
  public void start() {
    // the controller is set as the action listener for the buttons
    view.setActionListener(this);
  }

  /**
   * Handles user actions performed on GUI components.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    String currentPlayers = model.getAllPlayersInTheTeam();
    int teamSize = model.getTeamSize();
    String startingLineUp, playersOnTheBench;

    switch (command) {
      case "Add New Player": // switch to add player page, not adding player!
        view.switchToAddPage(currentPlayers, teamSize);
        break;

      case "Submit":
        List<String> infoList = view.getPlayerInfo();
        passValidInfoToModel(infoList);
        break;

      case "Refresh Add Page":
        view.switchToStartPage();
        view.switchToAddPage(currentPlayers, teamSize);
        break;

      case "Remove Player": // switch to remove player page, not removing player
        view.switchToRemovePage(currentPlayers, teamSize);
        break;

      case "Remove": // remove a player
        removeValidPlayer();
        break;

      case "Refresh Remove Page": // update the displayed players after removing
        view.switchToStartPage();
        view.switchToRemovePage(currentPlayers, teamSize);
        break;

      case "View Team Details": // switch to the Team Page
        view.switchToTeamPage(currentPlayers, "", "", teamSize);
        break;

      case "Generate Starting Line Up":
        startingLineUp = validateStartingLineUp();
        playersOnTheBench = model.getBenchPlayers();
        view.switchToStartPage();
        view.switchToTeamPage(currentPlayers, startingLineUp, playersOnTheBench, teamSize);
        break;

      case "Go Back":
        view.switchToStartPage();
        break;

      case "Exit Application":
        System.exit(0);
        break;

    }
  }

  /**
   * Passes valid player information from the GUI to the model to add a new player.
   *
   * @param infoList The List of String containing the player's information.
   */
  private void passValidInfoToModel(List<String> infoList) {

    try {
      String firstName = infoList.get(0);
      String lastName = infoList.get(1);
      int year = Integer.parseInt(infoList.get(2));
      int month = Integer.parseInt(infoList.get(3));
      int day = Integer.parseInt(infoList.get(4));
      String position = infoList.get(5);
      int skilledLevel = Integer.parseInt(infoList.get(6));

      model.addPlayer(firstName, lastName, year, month, day, position, skilledLevel);
      view.showSuccessMessage("Player added successfully!");
      clearPlayerInfoFields();

    } catch (IllegalArgumentException | DateTimeException e) {
      view.showFailureMessage("Error adding player: " + e.getMessage());
      clearPlayerInfoFields();
    }
  }



  /**
   * Removes a player based on the provided jersey number.
   */
  private void removeValidPlayer() {
    try {
      int jerseyNumber = Integer.parseInt(view.getJerseyNumberFromUserInput());
      String removalMessage = model.removePlayer(jerseyNumber);
      view.showSuccessMessage(removalMessage); // Player removal success
      clearJerseyNumberFields();
    } catch (IllegalStateException e) {
      view.showFailureMessage(e.getMessage()); // Team is empty
      clearJerseyNumberFields();
    } catch (NumberFormatException e) {
      view.showFailureMessage("Invalid jersey number. Please enter a valid number.");
      clearJerseyNumberFields();
    }
  }

  /**
   * Clears the input fields for player information in the GUI.
   */
  private void clearPlayerInfoFields() {
    view.clearPlayerInfoFields();
  }

  /**
   * Clears the input field for jersey number in the GUI.
   */
  private void clearJerseyNumberFields() {
    view.clearJerseyNumberFields();
  }

  /**
   * Validates and retrieves the starting lineup from the model.
   *
   * @return The starting lineup of the team as a formatted string.
   */
  private String validateStartingLineUp() {
    try {
      return model.getStartingLineupList();
    } catch (IllegalArgumentException e) {
      view.showFailureMessage(e.getMessage()); // Team size is less than 10
      return ""; // display empty string
    }
  }


}
