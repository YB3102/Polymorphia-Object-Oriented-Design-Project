package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Maze;
import csci.ooad.polymorphia.maze.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Creature extends Character {
    public static final Double DEFAULT_INITIAL_HEALTH = 3.0;

    public static final Double DEMON_INITIAL_HEALTH = 15.0;

    public Creature( String name, Strategy strategy) {
        super(name, DEFAULT_INITIAL_HEALTH, strategy);
    }

    public Creature(String name, double health, Strategy strategy) {
        super(name, health, strategy);
    }

    @Override
    public Boolean isCreature() {
        return true;
    }

    @Override
    public Boolean isDemon(){
        return strategy instanceof DemonStrategy;
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

}
