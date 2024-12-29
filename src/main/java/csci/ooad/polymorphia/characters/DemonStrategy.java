package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Room;

public class DemonStrategy implements  Strategy{

    @Override
    public Command getCommand(Character character) {
        Room currentRoom = character.getCurrentLocation();
        Creature demon = (Creature) character;

        // Demons should only fight or do nothing
        if (currentRoom.hasLivingAdventurers()) {
            return CommandFactory.createDemonFightCommand(demon);
        }

        return CommandFactory.createDoNothingCommand(demon); // Demons cannot move
    }

}
