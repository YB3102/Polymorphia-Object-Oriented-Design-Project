package csci.ooad.polymorphia.characters;
import csci.ooad.polymorphia.maze.Room;

public class CreatureStrategy implements Strategy{

    @Override
    public Command getCommand(Character character) {

        Room current_room = character.getCurrentLocation();

        Creature creature = (Creature) character;

        return CommandFactory.createDoNothingCommand(creature);

    }

}