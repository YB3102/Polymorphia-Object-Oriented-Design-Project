package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Room;

public class KnightStrategy implements Strategy {

    @Override
    public Command getCommand(Character character) {

        Room current_room = character.getCurrentLocation();

        // Prioritize armor first
        if (current_room.hasArmor()) {
            return CommandFactory.createWearCommand(character, current_room.getFirstArmor());
        }

        // Next, handle creatures in the room
        if (current_room.hasLivingCreatures()) {
            return CommandFactory.createFightCommand((Adventurer) character);
        }

        // Next, handle food in the room
        if (current_room.hasFood()) {
            return CommandFactory.createEatCommand((Adventurer) character, current_room.getFirstFoodItem());
        }

        // Default: Move to another room
        return CommandFactory.createAdventurerMoveCommand((Adventurer) character);
    }
}
