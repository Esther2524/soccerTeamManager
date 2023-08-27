import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

/**
 * The Player class represents a soccer player in the U10 soccer team.
 * It implements the PlayerInterface to provide methods for
 * accessing and modifying the player's information.
 */
public class Player implements PlayerInterface {
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private Position preferredPosition;
  private int skilledLevel;
  private Position actualPosition; // default state for players before any specific assignments are made.

  /**
   * Constructs a new Player with the provided information.
   *
   * @param firstName           The first name of the player.
   * @param lastName            The last name of the player.
   * @param year                The year of birth.
   * @param month               The month of birth.
   * @param day                 The day of birth.
   * @param preferredPosition   The preferred position of the player in the team.
   * @param skilledLevel        The skill level of the player, ranging from 1 to 5.
   * @throws IllegalArgumentException If the provided skilledLevel is not within the
   * range of 1 to 5, or if the player's age is 10 years or older.
   */
  public Player(String firstName, String lastName, int year, int month, int day,
      Position preferredPosition, int skilledLevel) throws IllegalArgumentException {
    if (skilledLevel < 1 || skilledLevel > 5) {
      throw new IllegalArgumentException("Skill level must be between 1 and 5.");
    }

    this.firstName = firstName;
    this.lastName = lastName;

    try {
      this.dateOfBirth = LocalDate.of(year, month, day);
      if (getAge() >= 10 || getAge() <= 0) {
        throw new IllegalArgumentException("Only players under ten years of age can "
            + "be part of the team.");
      }
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Invalid date of birth.");
    }

    this.preferredPosition = preferredPosition;
    this.skilledLevel = skilledLevel;

    // assign the value Position.BENCH to the actualPosition field
    this.actualPosition = Position.BENCH;
  }

  /**
   * Calculates and returns the age of the player based on the date of birth.
   *
   * @return The age of the player.
   */
  private int getAge() {
    LocalDate currentDate = LocalDate.now();
    Period age = Period.between(dateOfBirth, currentDate);
    return age.getYears();
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getDateOfBirth() {
    return dateOfBirth.toString();
  }

  @Override
  public void setDateOfBirth(int year, int month, int day) {
    this.dateOfBirth = LocalDate.of(year, month, day);
  }

  @Override
  public Position getPreferredPosition() {
    return preferredPosition;
  }

  @Override
  public void setPreferredPosition(Position preferredPosition) {
    this.preferredPosition = preferredPosition;
  }

  @Override
  public int getSkilledLevel() {
    return skilledLevel;
  }

  @Override
  public void setSkilledLevel(int skilledLevel) {
    this.skilledLevel = skilledLevel;
  }

  @Override
  public Position getActualPosition() {
    return actualPosition;
  }

  @Override
  public void setActualPosition(Position actualPosition) {
    this.actualPosition = actualPosition;
  }

}
