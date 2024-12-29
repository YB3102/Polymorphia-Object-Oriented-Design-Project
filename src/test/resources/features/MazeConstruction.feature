Feature: Maze Creation and Configuration

  Description: This feature validates various ways to create and configure mazes with different
  layouts, distributions, and placements of characters and items, ensuring flexible maze setups.



  Scenario: Create a 2x2 grid layout maze
    Given I have a 2x2 grid layout maze
    And I have a maze with the following attributes:
      | number of rooms       | 4 |
      | number of adventurers | 2 |
      | number of knights     | 1 |
      | number of cowards     | 1 |
      | number of gluttons    | 1 |
      | number of creatures   | 2 |
      | number of demons      | 1 |
      | number of food items  | 3 |
    Then the maze should have the specified mix of characters and food items
    And the maze should contain:
      | adventurers | 5 |
      | knights     | 1 |
      | cowards     | 1 |
      | gluttons    | 1 |
      | creatures   | 3 |
      | demons      | 1 |



  Scenario: Create a 3x3 grid layout maze
    Given I have a 3x3 grid layout maze
    And I have a maze with the following attributes:
      | number of rooms       | 9 |
      | number of adventurers | 3 |
      | number of knights     | 2 |
      | number of cowards     | 2 |
      | number of gluttons    | 2 |
      | number of creatures   | 4 |
      | number of demons      | 2 |
      | number of food items  | 6 |
    Then the maze should have the specified mix of characters and food items
    And the maze should contain:
      | adventurers | 9 |
      | knights     | 2 |
      | cowards     | 2 |
      | gluttons    | 2 |
      | creatures   | 6 |
      | demons      | 2 |



  Scenario: Create a fully connected maze with a mix of characters and items
    Given the maze is created as a fully connected layout with 5 rooms
    And I have a maze with the following attributes:
      | number of rooms       | 5 |
      | number of adventurers | 1 |
      | number of knights     | 1 |
      | number of cowards     | 1 |
      | number of gluttons    | 1 |
      | number of creatures   | 1 |
      | number of demons      | 1 |
      | number of food items  | 3 |
    Then the maze should have the specified mix of characters and food items
    And the maze should contain:
      | adventurers | 4 |
      | knights     | 1 |
      | cowards     | 1 |
      | gluttons    | 1 |
      | creatures   | 2 |
      | demons      | 1 |


  Scenario: Create a sequentially distributed maze with diverse items
    Given I have a maze with the following attributes:
      | number of rooms       | 6 |
      | number of adventurers | 2 |
      | number of knights     | 1 |
      | number of cowards     | 1 |
      | number of gluttons    | 1 |
      | number of creatures   | 3 |
      | number of demons      | 1 |
      | number of food items  | 5 |
    And the maze should contain:
      | adventurers | 5 |
      | knights     | 1 |
      | cowards     | 1 |
      | gluttons    | 1 |
      | creatures   | 4 |
      | demons      | 1 |


