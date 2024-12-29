package csci.ooad.polymorphia.characters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FightCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(FightCommand.class);
    Adventurer character;
    Creature enemy;
    public FightCommand(Adventurer character) {
        this.character = character;
        this.enemy = character.getCurrentLocation().getHealthiestCreature();
    }
    @Override
    public void execute() {
        if (enemy != null && this.character.getCurrentLocation().hasLivingCreatures()){
            character.fight(enemy);
        }
        else{
            logger.warn("No creature enemy present for adventurer to fight");
        }
    }
}
