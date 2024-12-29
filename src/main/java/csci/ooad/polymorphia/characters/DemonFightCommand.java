package csci.ooad.polymorphia.characters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemonFightCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DemonFightCommand.class);
    Creature character;
    Adventurer enemy;
    public DemonFightCommand(Creature character) {
        this.character = character;
        this.enemy = character.getCurrentLocation().getHealthiestAdventurer();
    }
    @Override
    public void execute() {
        if (enemy != null && this.character.getCurrentLocation().hasLivingAdventurers()){
            character.fight(enemy);
        }
        else{
            logger.warn("No adventurer enemy present for demon to fight");
        }
    }
}
