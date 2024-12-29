package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.Food;
import csci.ooad.polymorphia.maze.Maze;
import csci.ooad.polymorphia.NoSuchRoomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GluttonTest {

    CharacterFactory characterFactory = new CharacterFactory();

    @Test
    void testEating() {
        // Arrange
        Double initialHealth = 3.0;
        Adventurer glutton = characterFactory.createGlutton("Glutton");
        Maze.getNewBuilder()
                .createFullyConnectedRooms(1)
                .addAdventurers(glutton)
                .createAndAddCreatures("Ogre")
                .createAndAddFoodItems("Cake")
                .build();

        // Act - the glutton should not fight. It should eat
        //execute glutton's action
        glutton.getAction().execute();

        // Assert – the glutton ate the cake and gain 1 health point
        assertEquals(initialHealth + 1, glutton.getHealth());
    }

    @Test
    void testFighting() throws NoSuchRoomException {
        // Arrange - put Demon in room with Glutton
        Adventurer glutton = characterFactory.createGlutton("Glutton");
        Creature satan = characterFactory.createDemon("Satan");
        Food steak = new Food("Steak");
        Maze twoRoomMaze = Maze.getNewBuilder()
                .createFullyConnectedRooms("initial", "final")
                .addToRoom("initial", glutton)
                .addToRoom("initial", satan)
                .addToRoom("initial", steak)
                .build();

        // Act - the coward must fight a Demon
        satan.getAction().execute();

        // Assert – glutton must not have consumed food because of fight with demon
        assertTrue(twoRoomMaze.getRoom("initial").hasFood());
    }
}
