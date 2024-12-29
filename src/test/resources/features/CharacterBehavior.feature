Feature: Character Behavior and Interactions

  Description: This feature validates the unique behaviors of different character types in the maze, such as knights, cowards,
  gluttons, and demons. It ensures that each character behaves as expected in interactions, maintaining consistent gameplay mechanics.

  Scenario: Knight behavior. 2 Room Maze. Knight and Creature in same room. Game should be over without anyone moving.
    Given a Knight named "Gallant Knight" with 8 health
    And a Creature named "Ogre" with 1 health
    And both are in the same room
    When the game starts
    Then Ogre should be dead
    And Game should be over
    And no movement events should be recorded

  Scenario: Coward behavior. 2 Room Maze. Coward and Creature in same room. Coward must be dead.
    Given a Coward named "Henry" with 1 health
    And a Creature named "Ogre" with 3 health
    And both are in the same room
    When the game starts
    Then Game should be over
    And Coward should be dead
    And Ogre should be alive
    And no fight events should be recorded

  Scenario: Glutton behavior. 2 Room Maze. Coward and Creature in same room. Glutton must not eat.
    Given a Glutton named "Gotenks" with 1 health
    And a Demon named "Majin Buu" with 15 health
    And a Food named "Senzu" with 20 health value
    And all three are in the same room
    When the game starts
    Then Game should be over
    And Glutton should be dead
    And Demon should be alive
    And no eating events should be recorded

  Scenario: Demon behavior. 2 Room Maze. Coward and Demon in same room. Coward must be dead without switching rooms.
    Given a Coward named "Mr Satan" with 2 health
    And a Demon named "Majin Buu" with 15 health
    And both are in the same room
    When the game starts
    Then Game should be over
    And Coward should be dead
    And no movement events should be recorded
