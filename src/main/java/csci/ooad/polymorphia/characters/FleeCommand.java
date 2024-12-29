package csci.ooad.polymorphia.characters;
import csci.ooad.polymorphia.EventType;
import csci.ooad.polymorphia.maze.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static csci.ooad.polymorphia.EventBus.post;

public class FleeCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(FleeCommand.class);
    Adventurer character;
    static final Double HEALTH_LOST_IN_MOVING_ROOMS = 0.25;
    static final Double HEALTH_LOST_IN_FLEEING_ROOMS = 0.25;
    public FleeCommand(Adventurer character) {
        this.character = character;
    }

    protected void move() {
        Room nextLocation = character.getCurrentLocation().getRandomNeighbor();
        if (nextLocation != null) {
            String message = character.getName() + " moved from " + character.getCurrentLocation().getName() + " to " + nextLocation.getName();
            logger.info(message);
            post(EventType.Moved, message);

            nextLocation.enter(character);

            character.loseHealth(HEALTH_LOST_IN_MOVING_ROOMS);
        } else {
            logger.warn("{} has no neighbors!", character.getCurrentLocation().getName());
        }
    }
    @Override
    public void execute() {
        Room current_room = character.getCurrentLocation();
        if (current_room.hasLivingCreatures() && !current_room.getNeighbors().isEmpty()){
            character.loseHealth(HEALTH_LOST_IN_FLEEING_ROOMS);
            move();
        }
        else{
            logger.info("Cannot flee using flee command.");
        }
    }
}

