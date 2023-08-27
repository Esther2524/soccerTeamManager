# U10 Soccer Team Manager

## Overview

The Soccer Team Manager is a Java program designed to build soccer teams for children under ten years old (U10).
U10 soccer teams have seven players on the field. 
The best players are usually selected as the starting lineup and the remaining players are on the bench.
The program includes an interactive graphical user interface (GUI) that enables users to add and remove players, 
view team details, generate starting lineups, and perform other actions. 
The software follows the Model-View-Controller (MVC) architectural pattern for its construction.


## Features
***
1. Add new players to the soccer team, including their personal information. 
2. Remove players from the team using their jersey number.
3. View details of all players in the team, the starting lineup, and players on the bench.
4. Generate a starting lineup based on the team's players.
5. Interactive GUI for user-friendly interaction.

## How To Run
***
There are two ways to run the Soccer Team Manager:
- Double-click the JAR file to run it 
- Run the JAR file using the command: `java -jar SoccerTeamManager.jar`

## How to Use the Program
***
### Add New Players

1. Click the **Add New Player** button to access the *Add New Player Page*.
2. Fill in the player's details, including their first name, last name, birthdate (year, month, day), preferred position, and skill level.
3. After providing the necessary information, hit the **Submit** button. The application will validate the input and include the player in the team. On successful submission, you'll receive a confirmation message. In case of input errors, you'll be notified with an error message.
4. The right-hand display area showcases essential details about the members in your current team and provides the count of team members.
   To view the updated list of current players, click the **Refresh** button. This feature helps you stay up-to-date with your team's composition after adding new players.
5. Use the **Go Back** button to return to the initial interface at any time.

    

### Remove Players

1. To initiate player removal, select the **Remove Player** button and access the *Remove Player Page*.
2. Input the jersey number of the player you intend to remove, then press the **Remove** button.
3. The application will validate the jersey number and remove the corresponding player from the team. In case of a successful removal, you will receive a confirmation message. If the jersey number is invalid or the team is empty, appropriate error messages will be displayed.
4. The display area showcases essential details about the members in your current team and provides the count of team members. To view the updated list of current players, click the **Refresh** button. This feature helps you stay up-to-date with your team's composition after removing players.
5. Use the **Go Back** button to return to the initial interface at any time.

**Note:** Jersey numbers are randomly assigned between 1 and 20 for each player.



### View Team Details and Generate Starting Lineup

1. Click the **View Team Details** button to access the *Team Details Page*.
2. Upon clicking **Generate Starting Line Up** button, the generated starting lineup will be displayed, along with the players on the bench. In case your team has fewer than 10 players, an error message will inform you that the team must have at least 10 players.
3. Use the **Go Back** button to return to the initial interface at any time.



## Design/Model Changes
***
- Each page includes a section to view the current players in the team, along with a refresh button. This provides users with the convenience of checking the latest team composition after adding or removing players.



## Assumptions
***
- When adding the 21st player to the team, the algorithm removes the player with the lowest skill level. If skill levels are equal, a player is removed alphabetically, maintaining a maximum of 20 players.
- The process of selecting players for each position in the starting lineup is primarily based on their preferred positions. Additionally, any remaining spots in the lineup are filled while following a specific order of roles: midfielders are prioritized, followed by defenders, forwards, and goalies.


## Limitations
***
- Once players are added to the team, they cannot be modified. To make changes, users must remove and re-add players, which can be complex.
- Users cannot manually decide the conversion between bench players and starting lineup players, as the starting lineup is automatically generated based on the model's algorithm.

## Citations
***
[1] “Java AWT: CardLayout class,” GeeksforGeeks, https://www.geeksforgeeks.org/java-awt-cardlayout-class/ (accessed Aug. 15, 2023).

[2] “Java Swing - JPanel with examples,” GeeksforGeeks, https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/ (accessed Aug. 15, 2023). 
