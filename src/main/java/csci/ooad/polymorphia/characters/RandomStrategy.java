package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {

    private static final Random random = new Random();

    @Override
    public Command getCommand(Character character) {
        Room currentRoom = character.getCurrentLocation();
        List<Command> possibleCommands = new ArrayList<>();

        // Add possible actions based on the current room's contents
        if (currentRoom.hasLivingCreatures() && character instanceof Adventurer) {
            possibleCommands.add(CommandFactory.createFightCommand((Adventurer) character));
        }
        if (currentRoom.hasFood() && character instanceof Adventurer) {
            possibleCommands.add(CommandFactory.createEatCommand((Adventurer) character, currentRoom.getFirstFoodItem()));
        }
        if (currentRoom.hasArmor() && character instanceof Adventurer) {
            possibleCommands.add(CommandFactory.createWearCommand(character, currentRoom.getFirstArmor()));
        }

        // Moving or doing nothing are always options
        if (character instanceof Adventurer) {
            possibleCommands.add(CommandFactory.createAdventurerMoveCommand((Adventurer) character));
        }
        possibleCommands.add(CommandFactory.createDoNothingCommand(character));

        // Randomly select one of the possible commands
        return possibleCommands.get(random.nextInt(possibleCommands.size()));
    }
}
