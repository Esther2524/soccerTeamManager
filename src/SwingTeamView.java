import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class represents the graphical user interface for the U10 Soccer Team Manager application.
 * It implements the ViewInterface and provides methods
 * to display different pages and handle user interactions.
 */
public class SwingTeamView extends JFrame implements ViewInterface {
  private final JButton addPageButton;
  private final JButton removePageButton;
  private final JButton teamPageButton;
  private final JButton returnButton;
  private final JButton exitButton;
  private final JPanel panelContainer;
  private final JPanel panelStart;
  private final JPanel panelAddPlayer;
  private final JPanel panelRemovePlayer;
  private final JPanel panelTeam;

  private final JButton submitButton;
  private final JButton removeButton;
  private final JButton refreshButtonForAdd;
  private final JButton refreshButtonForRemove;
  private final JButton startingLineUpButton;

  private JTextField firstNameField, lastNameField, yearField, dayField, jerseyNumberField;
  private static final int DEFAULT_MONTH_INDEX = 0;
  private static final int DEFAULT_POSITION_INDEX = 0;
  private static final int DEFAULT_SKILLED_LEVEL_INDEX = 0;
  private JComboBox<String> monthComboBox, positionComboBox, skilledLevelComboBox;
  private final CardLayout c1;

  /**
   * Constructs a SwingTeamView with the specified title.
   *
   * @param title The title of the JFrame.
   */
  public SwingTeamView(String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(1200, 800)); // set window's size
    setResizable(false);

    c1 = new CardLayout();

    panelContainer = new JPanel();
    panelStart = new JPanel();
    panelAddPlayer = new JPanel();
    panelRemovePlayer = new JPanel();
    panelTeam = new JPanel();

    /*
      Very important point: Buttons must be defined within the view constructor.
      This is because the logic in the controller involves creating the view first and then
      invoking view.setController() to assign action listeners to each button.
      Therefore, if buttons are not defined within the view constructor,
      they won't be assigned action listeners.
     */
    addPageButton = new JButton("Add New Player");
    addPageButton.setForeground(new Color(228, 133, 134));
    removePageButton = new JButton("Remove Player");
    removePageButton.setForeground(new Color(68, 119, 206));
    teamPageButton = new JButton("View Team Details");
    teamPageButton.setForeground(new Color(0, 128, 0));
    returnButton = new JButton("Go Back");
    exitButton = new JButton("Exit Application");

    submitButton = new JButton("Submit");
    submitButton.setForeground(new Color(248, 111, 3));
    removeButton = new JButton("Remove");
    removeButton.setForeground(new Color(68, 119, 206));
    refreshButtonForAdd = new JButton("Refresh Add Page");
    refreshButtonForAdd.setForeground(new Color(164, 89, 209));
    refreshButtonForRemove = new JButton("Refresh Remove Page");
    refreshButtonForRemove.setForeground(new Color(164, 89, 209));
    startingLineUpButton = new JButton("Generate Starting Line Up");
    startingLineUpButton.setForeground(new Color(0, 128, 0));

    // Set the layout of the panelContainer to use CardLayout. This layout allows switching
    // between different panels by showing one at a time while others remain hidden.
    panelContainer.setLayout(c1);

    initializeStartPage(); // initialize start page

    // These names are used as identifiers for each panel so that
    // we can easily switch between them using the CardLayout methods
    panelContainer.add(panelStart, "1");
    panelContainer.add(panelAddPlayer, "2-1");
    panelContainer.add(panelRemovePlayer, "2-2");
    panelContainer.add(panelTeam, "2-3");

    // By default, set the start page as the initially displayed panel.
    c1.show(panelContainer, "1");

    // Add the panelContainer to the main frame
    add(panelContainer);
    pack();
  }


  @Override
  public void switchToStartPage() {
    c1.show(panelContainer, "1");
  }

  /**
   * Initializes the start page with buttons and introductory text.
   */
  private void initializeStartPage() {
    // Create a panel for the buttons and set its layout to vertical
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    buttonPanel.add(Box.createVerticalGlue());
    buttonPanel.add(Box.createVerticalStrut(30));

    String introText = "<html><div style='text-align: center; font-size: 14px;'>"
        + "<b style='font-size: 24px;'>Welcome to U10 Soccer Team Manager!</b><br><br></div>"
        + "<div style='text-align: left; margin-left: 20px; font-size: 12px;'>"
        + "You can switch to three different pages below and perform different tasks:<br><br>"
        + "- Switch to <font color= '#E48586'>Add New Player Page</font> "
        + "to add a new player to the team.<br>"
        + "- Switch to <font color='#4477CE'>Remove Player Page</font> "
        + "to remove a player from the team.<br>"
        + "- Switch to <font color='green'>View Team Details Page</font> "
        + "to create the team's starting lineup, "
        + "view all players, or players on the bench.<br>"
        + "- Click the Exit Application button to close the application.<br><br>"
        + "<b>Team Size Limit :</b><br>A team can have a maximum of 20 players.<br>"
        + "If the team size exceeds 20, "
        + "the player with the lowest skilled level will be removed.<br>"
        + "If multiple players have the same lowest skilled level, "
        + "they will be removed in alphabetical order of their last names.<br><br>"
        + "<b>Hint :</b><br>*After adding or removing a player, "
        + "please click the <font color= '#A459D1'>Refresh</font> "
        + "button to see the updated list of current players in the team.<br>"
        + "*You can return to this main page at any time by clicking the Go Back button.<br>"
        + "</div></html>";

    JLabel intro = new JLabel(introText);

    // Add spacing between intro and buttons
    buttonPanel.add(Box.createVerticalStrut(50));
    // Add buttons to the buttonPanel
    buttonPanel.add(addPageButton);
    buttonPanel.add(Box.createVerticalStrut(20));
    buttonPanel.add(removePageButton);
    buttonPanel.add(Box.createVerticalStrut(20));
    buttonPanel.add(teamPageButton);
    buttonPanel.add(Box.createVerticalStrut(20));
    buttonPanel.add(exitButton);

    // Create a panel for intro label and button panel, and set its layout to vertical
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

    // Add components to the content panel
    contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    contentPanel.add(intro);
    contentPanel.add(buttonPanel);

    // Add the content panel to the start panel
    panelStart.add(contentPanel);

    intro.setAlignmentX(Component.CENTER_ALIGNMENT);
    addPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    removePageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    teamPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    setVisible(true);
  }



  @Override
  public void switchToAddPage(String currentPlayers, int teamSize) {
    showAddPage(currentPlayers, teamSize);
    c1.show(panelContainer, "2-1");
  }

  /**
   * Initializes and displays the "Add New Player" page with input fields and information.
   *
   * @param currentPlayers A string containing information about the current players in the team.
   * @param teamSize The current size of the team.
   */
  private void showAddPage(String currentPlayers, int teamSize) {
    clearPage(panelAddPlayer); // Clear existing components

    panelAddPlayer.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 100, 20, 100);

    // Add label for indicating the page
    String pageTitle = "<html><div style='text-align: center; color: #E48586; font-weight: bold; "
        + "font-size: 18px;'>Add New Player Page</div></html>";
    JLabel pageTitleLabel = new JLabel(pageTitle);

    gbc.gridx = 0;
    gbc.gridy = 0;
    panelAddPlayer.add(pageTitleLabel, gbc);

    // Reset grid width to default
    gbc.gridwidth = 1;

    // Create a panel for buttons and text fields (left side of the second row)
    JPanel buttonAndFieldsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbcFields = new GridBagConstraints();
    gbcFields.anchor = GridBagConstraints.WEST;
    gbcFields.insets = new Insets(5, 5, 5, 5);


    JLabel firstName = new JLabel("First Name:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 0;
    buttonAndFieldsPanel.add(firstName, gbcFields);


    firstNameField = new JTextField(12);
    firstNameField.setPreferredSize(new Dimension(80, 25));
    gbcFields.gridx = 1;
    gbcFields.gridy = 0;
    buttonAndFieldsPanel.add(firstNameField, gbcFields);


    JLabel lastName = new JLabel("Last Name:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 1;
    buttonAndFieldsPanel.add(lastName, gbcFields);

    lastNameField = new JTextField(12);
    lastNameField.setPreferredSize(new Dimension(80, 25));
    gbcFields.gridx = 1;
    gbcFields.gridy = 1;
    buttonAndFieldsPanel.add(lastNameField, gbcFields);

    JLabel year = new JLabel("Year:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 2;
    buttonAndFieldsPanel.add(year, gbcFields);

    yearField = new JTextField(5);
    yearField.setPreferredSize(new Dimension(80, 25));
    gbcFields.gridx = 1;
    gbcFields.gridy = 2;
    buttonAndFieldsPanel.add(yearField, gbcFields);

    JLabel month = new JLabel("Month:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 3;
    buttonAndFieldsPanel.add(month, gbcFields);

    String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    monthComboBox = new JComboBox<>(months);
    gbcFields.gridx = 1;
    gbcFields.gridy = 3;
    buttonAndFieldsPanel.add(monthComboBox, gbcFields);

    JLabel day = new JLabel("Day:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 4;
    buttonAndFieldsPanel.add(day, gbcFields);

    dayField = new JTextField(5);
    dayField.setPreferredSize(new Dimension(80, 25));
    gbcFields.gridx = 1;
    gbcFields.gridy = 4;
    buttonAndFieldsPanel.add(dayField, gbcFields);

    JLabel position = new JLabel("Position:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 5;
    buttonAndFieldsPanel.add(position, gbcFields);

    String[] positions = {"Goalie", "Defender", "Midfielder", "Forward"};
    positionComboBox = new JComboBox<>(positions);
    gbcFields.gridx = 1;
    gbcFields.gridy = 5;
    buttonAndFieldsPanel.add(positionComboBox, gbcFields);

    JLabel skilledLevel = new JLabel("Skilled Level:");
    gbcFields.gridx = 0;
    gbcFields.gridy = 6;
    buttonAndFieldsPanel.add(skilledLevel, gbcFields);

    String[] skilledLevels = {"1", "2", "3", "4", "5"};
    skilledLevelComboBox = new JComboBox<>(skilledLevels);
    gbcFields.gridx = 1;
    gbcFields.gridy = 6;
    buttonAndFieldsPanel.add(skilledLevelComboBox, gbcFields);

    gbcFields.gridx = 0;
    gbcFields.gridy = 7;
    buttonAndFieldsPanel.add(returnButton, gbcFields);


    gbcFields.gridx = 1;
    gbcFields.gridy = 7;
    buttonAndFieldsPanel.add(submitButton, gbcFields);

    // Add buttons to the left section of the second row
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0.5; // Occupy half of the width
    panelAddPlayer.add(buttonAndFieldsPanel, gbc);


    // Create a panel for current players text area (right side of the second row)
    JPanel currentPlayersPanel = new JPanel();
    currentPlayersPanel.setLayout(new BorderLayout());

    String displayText = "Current Players in the Team (Team Size: "+ teamSize + ")"+"\n\n"
        + "Displayed Form:\nFirst Name, Last Name, Jersey Number\n"
        + "----------------------------------------------------------------\n";
    JTextArea currentPlayersTextArea = new JTextArea(displayText + currentPlayers);
    currentPlayersTextArea.setEditable(false);
    currentPlayersPanel.add(currentPlayersTextArea, BorderLayout.CENTER);


    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1.0; // Occupy full width
    gbc.anchor = GridBagConstraints.WEST; // Align to the right
    gbc.fill = GridBagConstraints.VERTICAL;
    panelAddPlayer.add(currentPlayersPanel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    panelAddPlayer.add(refreshButtonForAdd, gbc);
  }


  @Override
  public List<String> getPlayerInfo() {
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String year = yearField.getText();
    String month = (String) monthComboBox.getSelectedItem();
    String day = dayField.getText();
    String position = (String) positionComboBox.getSelectedItem();
    String skilledLevel = (String) skilledLevelComboBox.getSelectedItem();

    List<String> playerInfo = new ArrayList<>();
    playerInfo.add(firstName);
    playerInfo.add(lastName);
    playerInfo.add(year);
    playerInfo.add(month);
    playerInfo.add(day);
    playerInfo.add(position);
    playerInfo.add(skilledLevel);

    return playerInfo;
  }


  @Override
  public void clearPlayerInfoFields() {
    firstNameField.setText("");
    lastNameField.setText("");
    yearField.setText("");
    monthComboBox.setSelectedIndex(DEFAULT_MONTH_INDEX);
    dayField.setText("");
    positionComboBox.setSelectedIndex(DEFAULT_POSITION_INDEX);
    skilledLevelComboBox.setSelectedIndex(DEFAULT_SKILLED_LEVEL_INDEX);
  }


  @Override
  public void showSuccessMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Success!",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showFailureMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Failure!",
        JOptionPane.ERROR_MESSAGE);
  }


  @Override
  public void switchToRemovePage(String currentPlayers, int teamSize) {
    showRemovePage(currentPlayers, teamSize);
    c1.show(panelContainer, "2-2");
  }

  /**
   * Displays the "Remove Player" page with components for removing a player from the team.
   *
   * @param currentPlayers A formatted string containing the list of current players in the team.
   * @param teamSize The current size of the team.
   */
  private void showRemovePage(String currentPlayers, int teamSize) {
    clearPage(panelRemovePlayer); // Clear existing components

    panelRemovePlayer.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 20, 10);

    // Add label for indicating the page
    String text = "<html><div style='color: #4477CE; font-weight: bold; font-size: 18px;'>"
        + "Remove Player Page</div><br><br>"
        + "<div style='text-align: left;'>"
        + "You can remove a player by entering their jersey number from 1 to 20."
        + "</div><br>"
        + "<div style='text-align: left;'>"
        + "You can identify the player's jersey number based on the displayed information below."
        + "</div></html><br><br>";
    JLabel pageTitleLabel = new JLabel(text);


    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3; // Span across 3 columns
    panelRemovePlayer.add(pageTitleLabel, gbc);

    // Reset grid width to default
    gbc.gridwidth = 1;

    JLabel jerseyNumber = new JLabel("<html><b>Jersey Number:</b></html>");
    jerseyNumberField = new JTextField(5);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panelRemovePlayer.add(jerseyNumber, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    panelRemovePlayer.add(jerseyNumberField, gbc);


    gbc.gridx = 2;
    gbc.gridy = 1;
    panelRemovePlayer.add(removeButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panelRemovePlayer.add(returnButton, gbc);

    gbc.gridx = 2;
    gbc.gridy = 2;
    panelRemovePlayer.add(refreshButtonForRemove, gbc);


    // Add a JTextArea to display current players
    String displayText = "Current Players in the Team (Team Size: "+ teamSize + ")"+"\n\n"
        + "Displayed Form:\nFirst Name, Last Name, Jersey Number\n"
        + "----------------------------------------------------------------\n";
    JTextArea playerDisplayArea = new JTextArea(displayText + currentPlayers);
    playerDisplayArea.setEditable(false);


    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 3; // Span across 3 columns
    gbc.weighty = 1.0; // Take up available vertical space
    gbc.fill = GridBagConstraints.BOTH;
    panelRemovePlayer.add(playerDisplayArea, gbc);
  }

  @Override
  public void clearJerseyNumberFields() {
    jerseyNumberField.setText("");
  }

  @Override
  public String getJerseyNumberFromUserInput() {
    return jerseyNumberField.getText();
  }

  @Override
  public void switchToTeamPage(String currentPlayers, String startingLineup,
      String playersOnBench, int teamSize) {
    showTeamPage(currentPlayers, startingLineup, playersOnBench, teamSize);
    c1.show(panelContainer, "2-3");
  }

  /**
   * Displays the "Team Details" page with components for viewing and managing the team's details.
   *
   * @param currentPlayers A formatted string containing the list of current players in the team.
   * @param startingLineup A formatted string containing the starting lineup for the team.
   * @param playersOnBench A formatted string containing the list of players on the bench.
   * @param teamSize The current size of the team.
   */
  private void showTeamPage(String currentPlayers, String startingLineup, String playersOnBench,
      int teamSize) {
    clearPage(panelTeam); // Clear existing components

    panelTeam.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    // Add label for indicating the page
    String text = "<html><div style='color: #5B9A8B; font-weight: bold; font-size: 18px;'>"
        + "Team Details Page</div><br><br>"
        + "<div style='text-align: left;'>"
        + "Here, you can generate a starting lineup for the team. "
        + "Please note that the <font color='#008000'>Generate Starting Lineup</font> "
        + "button is only active when the team has 10 or more players.<br>"
        + "</div><br>"
        + "<div style='text-align: left;'>"
        + "Additionally, you can view the players on the bench at the same time."
        + "</div></html><br><br>";


    JLabel pageTitleLabel = new JLabel(text);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3; // Span across 3 columns
    gbc.weightx = 1.0; // Take up available horizontal space
    gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
    panelTeam.add(pageTitleLabel, gbc);

    // display all the current players in the team
    String displayText1 = "Current Players in the Team (Team Size: "+ teamSize + ")\n\n"
        + "Displayed Form:\nFirst Name, Last Name, Jersey Number\n"
        + "--------------------------------------------------\n";

    JTextArea currentPlayersTextArea = new JTextArea(displayText1 + currentPlayers);
    currentPlayersTextArea.setEditable(false);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1.0; // Equal width for all three columns
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    panelTeam.add(currentPlayersTextArea, gbc);


    String displayText2 = "Starting Lineup\n\n"
        + "Displayed Form:\nFirst Name, Last Name, Jersey Number, Position\n"
        + "--------------------------------------------------\n";
    JTextArea startingLineupTextArea = new JTextArea(displayText2 + startingLineup);
    startingLineupTextArea.setEditable(false);
    gbc.gridx = 1;
    gbc.gridy = 1;
    panelTeam.add(startingLineupTextArea, gbc);


    // Add players on bench text area
    String displayText3 = "Players on Bench\n\n"
        + "Displayed Form:\nFirst Name, Last Name, Jersey Number\n"
        + "--------------------------------------------------\n";
    JTextArea playersOnBenchTextArea = new JTextArea(displayText3 + playersOnBench);
    playersOnBenchTextArea.setEditable(false);
    gbc.gridx = 2;
    gbc.gridy = 1;
    panelTeam.add(playersOnBenchTextArea, gbc);

    // Add buttons panel
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonsPanel.add(returnButton);
    buttonsPanel.add(startingLineUpButton);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 3;
    gbc.weightx = 1.0; // Equal width for all three columns
    gbc.weighty = 0.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    panelTeam.add(buttonsPanel, gbc);
  }

  /**
   * Clears the content of a given JPanel by removing all its components.
   *
   * @param page The JPanel to be cleared.
   */
  private void clearPage(JPanel page) {
    page.removeAll();
  }



  @Override
  public void setActionListener(ActionListener listener) {
    addPageButton.addActionListener(listener);
    removePageButton.addActionListener(listener);
    teamPageButton.addActionListener(listener);
    returnButton.addActionListener(listener);
    exitButton.addActionListener(listener);

    submitButton.addActionListener(listener);
    removeButton.addActionListener(listener);
    refreshButtonForAdd.addActionListener(listener);
    refreshButtonForRemove.addActionListener(listener);

    startingLineUpButton.addActionListener(listener);
  }


}



