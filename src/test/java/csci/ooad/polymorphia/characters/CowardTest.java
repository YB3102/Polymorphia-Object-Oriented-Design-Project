package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Maze;
import csci.ooad.polymorphia.NoSuchRoomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CowardTest {

    CharacterFactory characterFactory = new CharacterFactory();
    @Test
    void testRunning() throws NoSuchRoomException {
        // Arrange - put creature in room with two adventurers
        Double initialHealth = 5.0;
        Adventurer coward = characterFactory.createCoward("Coward");
        Creature dragon = characterFactory.createCreature("Dragon");
        Maze twoRoomMaze = Maze.getNewBuilder()
                .createFullyConnectedRooms("initial", "final")
                .addToRoom("initial", coward)
                .addToRoom("initial", dragon)
                .build();

        // Act - the weak knight should fight
        coward.getAction().execute();

        // Assert – the coward ran to the other room and lost some health doing it
        // since there was a creature in the room.
        assertTrue(twoRoomMaze.getRoom("final").hasLivingAdventurers());
        assertTrue(coward.getHealth() < initialHealth);
    }

    @Test
    void testFighting() {
        // Arrange - put creature in room with two adventurers
        Adventurer coward = characterFactory.createCoward("Coward");
        Creature satan = characterFactory.createDemon("Satan");
        Maze.getNewBuilder()
                .createFullyConnectedRooms("initial", "final")
                .addToRoom("initial", coward)
                .addToRoom("initial", satan)
                .build();

        // Act - the coward must fight a Demon
        satan.getAction().execute();

        // Assert – the coward ran to the other room
        assertNotEquals(Creature.DEMON_INITIAL_HEALTH, satan.getHealth());
    }
}
