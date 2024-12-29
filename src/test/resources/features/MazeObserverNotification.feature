Feature: Maze Observer for Death Events

  Description: This feature ensures the maze observer correctly tracks and logs death events within the maze. It verifies
  that each death is recorded accurately, allowing a detailed log of the game’s progress.

  Scenario: Observer tracks death events for adventurers and creatures
    Given a maze with an observer tracking death events
    And an Adventurer named "Bill" with 1 health point
    And a Creature named "Troll" with high attack damage
    When the Creature attacks the Adventurer
    Then the Adventurer should die
    And the observer should log one death event

  Scenario: Multiple death events recorded in a battle
    Given a maze with an observer tracking death events
    And two Creatures named "Goblin" and "Troll"
    And "Troll" has low health
    And an Adventurer named "Lucas" with low health
    When both the Creature and Adventurer take lethal damage simultaneously
    Then the Adventurer should die
    Then the Creature should die
    And the observer should log the death of the Adventurer
    And the observer should log the death of the Creature



