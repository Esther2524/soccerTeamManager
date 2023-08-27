import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The PlayerTest class is a test suite for the Player class.
 * It contains unit tests for each method in the Player class.
 */
public class PlayerTest {

  private PlayerInterface player1, player2, player3, player4;

  /**
   * Set up the test player before each test case.
   */
  @Before
  public void setUp() {
    player1 = new Player("John", "Doe", 2015, 6, 15,
        Position.FORWARD, 4);
    player2 = new Player("Harry", "Potter", 2019, 2, 24,
        Position.GOALIE, 5);
    player3 = new Player("James", "Smith", 2018, 7, 1,
        Position.DEFENDER, 3);
    player4 = new Player("Maria", "Johnson", 2014, 12, 10,
        Position.MIDFIELDER, 1);
  }

  /**
   * Tests a Player object.
   */
  @Test
  public void createAPlayer() {
    PlayerInterface player = new Player("Apple", "Banana",
        2016, 2, 10, Position.MIDFIELDER, 1);
    Assert.assertEquals("Apple", player.getFirstName());
    Assert.assertEquals("Banana", player.getLastName());
    Assert.assertEquals("2016-02-10", player.getDateOfBirth());
    Assert.assertEquals(Position.MIDFIELDER, player.getPreferredPosition());
    Assert.assertEquals(1, player.getSkilledLevel());
  }

  /**
   * Tests the constructor with invalid age.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerOlderThanTen() {
    new Player("Mike", "Johnson", 2012, 1, 1,
        Position.DEFENDER, 3);
  }

  /**
   * Tests the constructor with invalid skilled level.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkilledLevel() {
    new Player("Jane", "Smith", 2017, 10, 5,
        Position.GOALIE, 6);
  }

  /**
   * Tests the constructor with invalid skilled level.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkilledLevelWithZero() {
    new Player("Jane", "Smith", 2017, 10, 5,
        Position.GOALIE, 0);
  }

  /**
   * Tests the constructor with invalid negative skilled level.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkilledLevelWithNegative() {
    new Player("Jane", "Smith", 2017, 10, 5,
        Position.GOALIE, -2);
  }

  /**
   * Tests the getFirstName() method.
   */
  @Test
  public void testGetFirstName() {
    assertEquals("John", player1.getFirstName());
    assertEquals("Harry", player2.getFirstName());
    assertEquals("James", player3.getFirstName());
    assertEquals("Maria", player4.getFirstName());
  }

  /**
   * Tests the setFirstName() method.
   */
  @Test
  public void testSetFirstName() {
    player1.setFirstName("Mike");
    assertEquals("Mike", player1.getFirstName());
  }

  /**
   * Tests the getLastName() method.
   */
  @Test
  public void testGetLastName() {
    assertEquals("Doe", player1.getLastName());
    assertEquals("Potter", player2.getLastName());
    assertEquals("Smith", player3.getLastName());
    assertEquals("Johnson", player4.getLastName());
  }

  /**
   * Tests the testLastName() method.
   */
  @Test
  public void testSetLastName() {
    player1.setLastName("Smith");
    assertEquals("Smith", player1.getLastName());
  }

  /**
   * Tests the getDateOfBirth() method.
   */
  @Test
  public void testGetDateOfBirth() {
    assertEquals("2015-06-15", player1.getDateOfBirth());
    assertEquals("2019-02-24", player2.getDateOfBirth());
    assertEquals("2018-07-01", player3.getDateOfBirth());
    assertEquals("2014-12-10", player4.getDateOfBirth());
  }

  /**
   * Tests the setDateOfBirth() method.
   */
  @Test
  public void testSetDateOfBirth() {
    player1.setDateOfBirth(2016, 3, 20);
    assertEquals("2016-03-20", player1.getDateOfBirth());
  }


  /**
   * Tests the getPreferredPosition() method.
   */
  @Test
  public void testGetPreferredPosition() {
    assertEquals(Position.FORWARD, player1.getPreferredPosition());
    assertEquals(Position.GOALIE, player2.getPreferredPosition());
    assertEquals(Position.DEFENDER, player3.getPreferredPosition());
    assertEquals(Position.MIDFIELDER, player4.getPreferredPosition());
  }

  /**
   * Tests the setPreferredPosition() method.
   */
  @Test
  public void testSetPreferredPosition() {
    player1.setPreferredPosition(Position.MIDFIELDER);
    assertEquals(Position.MIDFIELDER, player1.getPreferredPosition());
  }

  /**
   * Tests the getSkilledLevel() method.
   */
  @Test
  public void testGetSkilledLevel() {
    assertEquals(4, player1.getSkilledLevel());
    assertEquals(5, player2.getSkilledLevel());
    assertEquals(3, player3.getSkilledLevel());
    assertEquals(1, player4.getSkilledLevel());
  }

  /**
   * Tests the setSkilledLevel() method.
   */
  @Test
  public void testSetSkilledLevel() {
    player1.setSkilledLevel(3);
    assertEquals(3, player1.getSkilledLevel());
  }


  /**
   * Tests the getActualPosition() method.
   */
  @Test
  public void testGetActualPosition() {
    assertEquals(Position.BENCH, player1.getActualPosition());
    assertEquals(Position.BENCH, player2.getActualPosition());
    assertEquals(Position.BENCH, player3.getActualPosition());
    assertEquals(Position.BENCH, player4.getActualPosition());
  }

  /**
   * Tests the setActualPosition() method.
   */
  @Test
  public void testSetActualPosition() {
    player1.setActualPosition(Position.FORWARD);
    assertEquals(Position.FORWARD, player1.getActualPosition());

    player2.setActualPosition(Position.GOALIE);
    assertEquals(Position.GOALIE, player2.getActualPosition());

    player3.setActualPosition(Position.FORWARD);
    assertEquals(Position.FORWARD, player3.getActualPosition());

    player4.setActualPosition(Position.MIDFIELDER);
    assertEquals(Position.MIDFIELDER, player4.getActualPosition());
  }



}
