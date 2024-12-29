package csci.ooad.polymorphia.characters;
import csci.ooad.polymorphia.Food;
import csci.ooad.polymorphia.maze.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EatCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(EatCommand.class);
    Adventurer character;
    Food foodItem;
    public EatCommand(Adventurer character, Food foodItem) {
        this.character = character;
        this.foodItem = foodItem;
    }
    @Override
    public void execute() {
        Room currentRoom = character.getCurrentLocation();
        if (currentRoom.contains(foodItem)) {
            character.gainHealth(foodItem.getHealthValue());
            currentRoom.removeItem(foodItem);
        } else {
            logger.warn("No Food Item available in EatCommand");
        }
    }
}

