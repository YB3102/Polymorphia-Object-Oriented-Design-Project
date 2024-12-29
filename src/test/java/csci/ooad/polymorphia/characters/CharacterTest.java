package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.Food;
import csci.ooad.polymorphia.maze.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    CharacterFactory characterFactory = new CharacterFactory();

    Character joe;

    @BeforeEach
    void setUp() {
        joe = characterFactory.createAdventurer("Joe");
    }

    @Test
    void testToString() {
        System.out.println(joe);

        assertTrue(joe.toString().contains("Joe"));
    }

    @Test
    void isAlive() {
        assertTrue(joe.isAlive());
    }

    @Test
    void testSorting() {
        double mostHealth = 10.0;
        double mediumHealth = 5.0;
        double leastHealth = 3.0;

        List<Character> characters = new ArrayList<>(Arrays.asList(
                characterFactory.createAdventurer("Frodo", mostHealth),
                characterFactory.createCreature("Ogre", mediumHealth),
                characterFactory.createAdventurer("Arwen", leastHealth)));

        Collections.sort(characters);

        System.out.println(characters);
    }

    @Test
    void testLoseHealthAndDeath() {
        joe.loseHealth(3.0);
        assertEquals(2.0, joe.getHealth());

        joe.loseHealth(2.0);
        assertFalse(joe.isAlive());
    }

    @Test
    void testFightingMandatoryLossOfHalfAPoint() {
        Creature ogre = characterFactory.createCreature("Ogre");
        joe.fight(ogre);
        System.out.println("After the fight, Joe's health is: " + joe.getHealth());

        // Joe should have lost 0.5 health and he started with a integer health value
        // of 5.0. After the fight he should have 4.5 health. Or 3.5, or 2.5, etc. depending
        // upon the outcome of the fight. So, we just check to make sure the health is x.5
        assertEquals(0.5, joe.getHealth() % 1);
    }

    @Test
    void testMovingRoomLossOfQuarterPoint() {
        Room currentRoom = new Room("currentRoom");
        Room newRoom = new Room("newRoom");
        currentRoom.addNeighbor(newRoom);
        joe.enterRoom(currentRoom);

        // Since nothing is in the current room with Joe, he should move to the new room
        joe.getAction().execute();

        System.out.println("After moving rooms, Joe's health is: " + joe.getHealth());
        assertEquals(4.75, joe.getHealth());
        assertEquals(newRoom, joe.getCurrentLocation());
    }

    @Test
    void testMovingWithNoNeighbors() {
        Room room = new Room("room");
        Adventurer adventurer = characterFactory.createAdventurer("Adventurer");
        room.add(adventurer);

        // Act -- no error occurs
        adventurer.getAction().execute();
    }

    @Test
    void testEatingFood() {
        Room room = new Room("room");
        Adventurer adventurer = characterFactory.createAdventurer("Adventurer");
        room.add(adventurer);
        Food popcorn = new Food("popcorn");
        room.add(popcorn);

        adventurer.getAction().execute();

        assertEquals(adventurer.getHealth(), Character.DEFAULT_INITIAL_HEALTH + popcorn.getHealthValue() );
        assertFalse(room.hasFood());
    }

    @Test
    void testFighting() {
        Adventurer adventurer = characterFactory.createAdventurer("Adventurer");
        Creature creature = characterFactory.createCreature("Creature");

        double initialHealth = adventurer.getHealth();
        adventurer.fight(creature);

        assertNotEquals(initialHealth, adventurer.getHealth());
    }

    @Test
    void testCreatureDoesNotDoAction() {
        Creature creature = characterFactory.createCreature("Creature");
        creature.getAction().execute();
    }
}