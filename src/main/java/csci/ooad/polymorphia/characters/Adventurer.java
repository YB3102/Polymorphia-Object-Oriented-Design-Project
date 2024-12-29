package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.EventType;
import csci.ooad.polymorphia.Food;
import csci.ooad.polymorphia.NoFoodException;
import csci.ooad.polymorphia.maze.Maze;
import csci.ooad.polymorphia.maze.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static csci.ooad.polymorphia.EventBus.post;


public class Adventurer extends Character {
    private static final Logger logger = LoggerFactory.getLogger(Adventurer.class);

    public static final double COWARD_INITIAL_HEALTH = 5.0;
    public static final double GLUTTON_INITIAL_HEALTH = 3.0;
    public static final double KNIGHT_INITIAL_HEALTH = 12.0;

    public Adventurer(String name, Strategy strategy) {
        super(name, strategy);
    }

    public Adventurer(String name, Double initialHealth, Strategy strategy) {
        super(name, initialHealth, strategy);
    }

    public void enterRoom(Room room) {
        if (getCurrentLocation() != null) {
            if (getCurrentLocation().equals(room)) {
                return;
            }
            getCurrentLocation().remove(this);
        }
        super.enterRoom(room);
    }

    Boolean shouldFight() {
        return creatureInRoomWithMe() && iAmHealthiestInRoom();
    }

    private boolean iAmHealthiestInRoom() {
        return this.equals(getCurrentLocation().getHealthiestAdventurer());
    }

    Boolean creatureInRoomWithMe() {
        return getCurrentLocation().hasLivingCreatures();
    }

    @Override
    public Boolean isAdventurer() {
        return true;
    }

}