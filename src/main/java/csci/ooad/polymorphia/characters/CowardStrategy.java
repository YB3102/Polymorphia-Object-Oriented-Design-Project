package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Room;

public class CowardStrategy implements Strategy{

    @Override
    public Command getCommand(Character character) {

        Room current_room = character.getCurrentLocation();

        Adventurer adventurer = (Adventurer) character;

        if (current_room.hasLivingCreatures() && current_room.getNeighbors().isEmpty()){
            return CommandFactory.createFightCommand(adventurer);
        }

        if (current_room.hasFood()){
            return CommandFactory.createEatCommand(adventurer, current_room.getFirstFoodItem());
        }

        if (current_room.hasLivingCreatures()){
            return CommandFactory.createFleeCommand(adventurer);
        }
        else{
            return CommandFactory.createAdventurerMoveCommand(adventurer);
        }
    }
}
