Feature: Game Play

  Description: This feature tests complex gameplay scenarios within the maze, including interactions and survival outcomes.
  It ensures the game handles various configurations and behaviors of characters, leading to a defined end state based on
  survival criteria.



  Scenario: Play a complicated game
    Given I have a maze with the following attributes:
      | number of rooms       | 7 |
      | number of adventurers | 1 |
      | number of knights     | 2 |
      | number of cowards     | 2 |
      | number of gluttons    | 2 |
      | number of creatures   | 5 |
      | number of demons      | 2 |
      | number of food items  | 9 |
    When I play the game in the created maze
    Then I should be told that either all the adventurers or all of the creatures have died
    And the game should be over



  Scenario: Coward will fight a Creature if he cannot run away
    Given a Coward "Sir Run Away"
    And a Creature "Ogre"
    And a room named "Only Room" with no neighbors
    And all characters are in room "Only Room"
    When adventurer "Sir Run Away" executes his turn
    Then a fight took place
    And creature "Ogre" lost some health



  Scenario: Coward will fight a Creature if he cannot run away - version 2
    Given I have a maze with the following attributes:
      | number of rooms       | 1 |
      | number of cowards     | 1 |
      | number of creatures   | 1 |
    When I play the game in the created maze
    Then a fight took place
    And the creature lost some health
