/**
 * The Main class serves as the entry point for the U10 Soccer Team Manager application.
 * It initializes the necessary components, including the team model, user interface view,
 * and controller, and starts the application by invoking the controller's start method.
 */
public class Main {
  /**
   * Initializes the U10 Soccer Team Manager application.
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    TeamModel team = new Team();
    ViewInterface view = new SwingTeamView("U10 Soccer Team Manager");
    ControllerInterface controller = new SwingTeamController(team, view);
    controller.start();
  }
}



