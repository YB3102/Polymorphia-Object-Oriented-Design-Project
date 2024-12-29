package csci.ooad.polymorphia;

import csci.ooad.polymorphia.characters.Adventurer;
import csci.ooad.polymorphia.characters.CharacterFactory;
import csci.ooad.polymorphia.characters.Creature;
import csci.ooad.polymorphia.maze.Room;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    CharacterFactory characterFactory = new CharacterFactory();

    @Test
    void getRandomNeighbor() {
        Room room = new Room("mainRoom");
        Room neighbor = new Room("neighbor");
        room.addNeighbor(neighbor);

        assertEquals(room.getRandomNeighbor(), neighbor);
    }

    @Test
    void testGetRandomNeighborOnRoomWithNoNeighbors() {
        Room room = new Room("onlyRoom");
        assertNull(room.getRandomNeighbor());
    }

    @Test
    void testToString() {
        Room room = new Room("onlyRoom");
        room.add(characterFactory.createAdventurer("Frodo"));
        room.add(characterFactory.createCreature("Ogre"));

        System.out.println(room);

        assertTrue(room.toString().contains("onlyRoom"));
        assertTrue(room.toString().contains("Frodo"));
        assertTrue(room.toString().contains("Ogre"));
    }

    @Test
    void testGetHealthiestAdventurer() {
        // Arrange
        double highestHealth = 5;
        double lowestHealth = 3;

        Room room = new Room("onlyRoom");
        Adventurer bilbo = characterFactory.createAdventurer("Bilbo", highestHealth);
        room.add(bilbo);
        room.add(characterFactory.createAdventurer("Frodo", lowestHealth));
        Creature troll = characterFactory.createCreature("Troll", highestHealth);
        room.add(troll);
        room.add(characterFactory.createCreature("Orc", lowestHealth));

        // Act
        Adventurer fittestAdventurer = room.getHealthiestAdventurer();
        Creature fittestCreature = room.getHealthiestCreature();

        // Assert
        assertEquals(bilbo, fittestAdventurer);
        assertEquals(troll, fittestCreature);
    }


    @Test
    void testEatNonExistentFood() {
        // Arrange
        Room room = new Room("onlyRoom");
        assertThrows(NoFoodException.class, room::removeFoodItem);
    }
}