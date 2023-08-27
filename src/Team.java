import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a U10 soccer team that manages players and their positions.
 */
public class Team implements TeamModel {
  private final Map<Integer, PlayerInterface> playerMap;

  /**
   * Constructs a new Team instance with an empty playerMap.
   */
  public Team() {
    playerMap = new HashMap<>();  // initializes a new Team instance with an empty map to store players.
  }

  @Override
  public void createValidTeam() throws IllegalStateException{
    // check whether the team has at least 10 players
    if (playerMap.size() < 10) {
      throw new IllegalStateException("The team must have a minimum of 10 players.");
    }
  }


  @Override
  public void addPlayer(String firstName, String lastName, int year, int month, int day,
      String positionString, int skilledLevel) {

    // convert the preferredPosition String to Position enum
    Position preferredPosition = getPositionFromString(positionString);

    // controller should only access Team, not access Player
    PlayerInterface player = new Player(firstName, lastName, year, month, day,
        preferredPosition, skilledLevel);

    // If the team already has 20 players, the player with the lowest skill level must be ignored
    if (playerMap.size() >= 20) {
      removeLowestSkilledLevelPlayers(player);
    } else {
      int jerseyNumber = generateUniqueJerseyNumber();
      playerMap.put(jerseyNumber, player);
    }

  }

  /**
   * Converts a String representation of a position to the corresponding Position enum value.
   *
   * @param positionString The String representation of the position.
   * @return The Position enum value.
   * @throws IllegalArgumentException If the positionString does not match any valid position.
   */
  private Position getPositionFromString(String positionString) throws IllegalArgumentException {
    switch (positionString.toUpperCase()) {
      case "GOALIE":
        return Position.GOALIE;
      case "DEFENDER":
        return Position.DEFENDER;
      case "MIDFIELDER":
        return Position.MIDFIELDER;
      case "FORWARD":
        return Position.FORWARD;
      default:
        throw new IllegalArgumentException("Invalid position: " + positionString);
    }
  }





  /**
   * Removes the player with the lowest skilled level from the team to ensure that
   * the team size remains within the maximum limit of 20 players.
   */
  private void removeLowestSkilledLevelPlayers(PlayerInterface newPlayer) {
    List<PlayerInterface> playerList = new ArrayList<>(playerMap.values());
    playerList.add(newPlayer); // Add the newPlayer to the list to compare the skilledLevel

    playerList.sort(Comparator.comparingInt(PlayerInterface::getSkilledLevel)
        .thenComparing(PlayerInterface::getLastName).thenComparing(PlayerInterface::getFirstName));
    // first sort by skilled level, and then by alphabetical order

    // the element at index 0 has the lowest skilled level
    PlayerInterface lowestSkilledLevelPlayer = playerList.get(0);

    // if the lowest skilled player is not the newPlayer, then remove it
    // meanwhile we should put the newPlayer to the playerMap
    if (lowestSkilledLevelPlayer != newPlayer) {
      playerMap.remove(getJerseyNumberByPlayer(lowestSkilledLevelPlayer));
      int jerseyNumber = generateUniqueJerseyNumber();
      playerMap.put(jerseyNumber, newPlayer);
    }

    // if the lowest skilled player is the newPlayer, we just do nothing.
    // the original playerMap remain unchanged
  }



  /**
   * Generates a unique jersey number for a player that is not already in use by another player.
   * Jersey numbers are randomly assigned in the range of [1, 20],
   * ensuring uniqueness within the team.
   *
   * @return A unique jersey number for a player.
   */
  private int generateUniqueJerseyNumber() {
    Random random = new Random();
    int jerseyNumber;

    // nextInt(20) means [0, 19) and we want to get a number in the range [1, 21)
    jerseyNumber = random.nextInt(20) + 1;

    while (playerMap.containsKey(jerseyNumber)) {
      jerseyNumber = random.nextInt(20) + 1;
    }

    return jerseyNumber;
  }



  @Override
  public String removePlayer(int jerseyNumber) {
    if (playerMap.size() == 0) {
      throw new IllegalStateException("Cannot remove player. The team is empty.");
    }

    PlayerInterface removedPlayer = playerMap.remove(jerseyNumber);
    if (removedPlayer != null) {
      return "Player with jersey number " + jerseyNumber + " has been removed.";
    } else {
      return "No player found with the given jersey number.";
    }
  }

  @Override
  public PlayerInterface getPlayerByJerseyNumber(int jerseyNumber) {
    return playerMap.get(jerseyNumber);
  }

  @Override
  public int getJerseyNumberByPlayer(PlayerInterface player) {
    for (Map.Entry<Integer, PlayerInterface> entry : playerMap.entrySet()) {
      if (entry.getValue() == player) {
        return entry.getKey();
      }
    }
    return -1; // If the player is not found in the playerMap
  }



  @Override
  public int getTeamSize() {
    return playerMap.size();
  }



  /**
   * Filters and selects players for the starting lin eup based on specific criteria:
   * 1 Goalie, 2 Defenders, 3 Midfielders, 1 Forward.
   * If there are multiple candidates for the same position,
   * choose the player with the highest skilledLevel.
   *
   * @return A list of players in the starting line up.
   */
  private List<PlayerInterface> filterStartingLineupPlayers() {
    // get the list of all players in the team using playerMap.values()
    List<PlayerInterface> allPlayers = new ArrayList<>(playerMap.values());

    // first, sort players by skilledLevel in descending order (highest skilled level first),
    // then by preferredPosition
    allPlayers.sort(Comparator.comparing(PlayerInterface::getSkilledLevel).reversed()
        .thenComparing(PlayerInterface::getPreferredPosition));

    List<PlayerInterface> startingLineup = new ArrayList<>();

    // initializes counters for each position (goalies, defenders, midfielders, and forwards) to
    // keep track of how many players of each position have been selected for the starting lineup.
    int goalies = 0, defenders = 0, midfielders = 0, forwards = 0;


    for (PlayerInterface player : allPlayers) {
      if (player.getPreferredPosition() == Position.GOALIE && goalies < 1) {
        // If a player's preferred position matches the criteria and
        // the required number of players for that position has not been reached,
        startingLineup.add(player); // add this player to the starting lineup list
        player.setActualPosition(Position.GOALIE); // actualPosition is set accordingly
        goalies++; // increment the counter for that position
      } else if (player.getPreferredPosition() == Position.DEFENDER && defenders < 2) {
        startingLineup.add(player);
        player.setActualPosition(Position.DEFENDER);
        defenders++;
      } else if (player.getPreferredPosition() == Position.MIDFIELDER && midfielders < 3) {
        startingLineup.add(player);
        player.setActualPosition(Position.MIDFIELDER);
        midfielders++;
      } else if (player.getPreferredPosition() == Position.FORWARD && forwards < 1) {
        startingLineup.add(player);
        player.setActualPosition(Position.FORWARD);
        forwards++;
      }
    }


    // After selecting players for each specific position
    // if any position is not filled, add remaining players based on skilledLevel
    for (PlayerInterface player : allPlayers) {

      if (startingLineup.size() >= 7) {
        break; //  checks if the starting lineup already contains 7 players. if so, break out of the loop
      }

      /*
        when we fill the positions, the order is: midfielders > defenders > forward > goalie
        because I think the larger the number of players in the position,
        the higher the requirement for the skilledLevel.
       */
      if (!startingLineup.contains(player)) {
        startingLineup.add(player);
        if (midfielders < 3) {
          player.setActualPosition(Position.MIDFIELDER);
          midfielders++;
        } else if (defenders < 2) {
          player.setActualPosition(Position.DEFENDER);
          defenders++;
        } else if (forwards < 1) {
          player.setActualPosition(Position.FORWARD);
          forwards++;
        } else if (goalies < 1) {
          player.setActualPosition(Position.GOALIE);
          goalies++;
        }
      }
    }

    // Finally, any remaining players that have not been included in the starting lineup are assigned the Position.BENCH
    for (PlayerInterface player : allPlayers) {
      if (!startingLineup.contains(player)) {
        player.setActualPosition(Position.BENCH);
      }
    }

    return startingLineup;
  }






  @Override
  public String getAllPlayersInTheTeam() {
    // allPlayers is a list of Player objects
    List<PlayerInterface> allPlayers = new ArrayList<>(playerMap.values());
    return outputPlayerList(allPlayers);
  }



  @Override
  public String getStartingLineupList() {
    if (getTeamSize() < 10) {
      throw new IllegalArgumentException("Cannot generate starting lineup. "
          + "The team must have at least 10 players.");
    }

    // startingLineup is a list of Player objects
    List<PlayerInterface> startingLineup = filterStartingLineupPlayers();

    startingLineup.sort(Comparator.comparing(PlayerInterface::getActualPosition)
        .thenComparing(PlayerInterface::getLastName)
        .thenComparing(PlayerInterface::getFirstName));

    StringBuilder listBuilder = new StringBuilder();

    for (PlayerInterface player : startingLineup) {
      listBuilder.append(player.getFirstName()).append(", ").append(player.getLastName())
          .append(", Jersey Number: ").append(getJerseyNumberByPlayer(player)).append(", Position: ")
          .append(player.getActualPosition()).append("\n");
    }
    return listBuilder.toString();
  }



  @Override
  public String getBenchPlayers() {
    if (getTeamSize() < 10) {
      throw new IllegalArgumentException("Cannot retrieve bench players. "
          + "The team must have at least 10 players.");
    }

    List<PlayerInterface> benchPlayers = new ArrayList<>();

    for (PlayerInterface player : playerMap.values()) {
      if (player.getActualPosition() == Position.BENCH) {
        benchPlayers.add(player);
      }
    }
    return outputPlayerList(benchPlayers);
  }

  /**
   * Generates a formatted player list with details for each player.
   *
   * @param players The list of players to be included in the list.
   * @return A formatted string containing player details.
   */
  private String outputPlayerList(List<PlayerInterface> players) {
    // sort by last name
    players.sort(Comparator.comparing(PlayerInterface::getLastName));
    StringBuilder listBuilder = new StringBuilder();


    for (PlayerInterface player : players) {
      listBuilder.append(player.getFirstName()).append(", ").append(player.getLastName())
          .append(", Jersey Number: ").append(getJerseyNumberByPlayer(player));
      listBuilder.append("\n");
    }

    return listBuilder.toString();
  }

  }
