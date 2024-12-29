package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Room;
import csci.ooad.polymorphia.Food;

import java.util.List;
import java.util.Scanner;

public class HumanStrategy implements Strategy {

    @Override
    public Command getCommand(Character character) {
        Room currentRoom = character.getCurrentLocation();
        Scanner scanner = new Scanner(System.in);

        System.out.println("You are in room: " + currentRoom.getName());
        System.out.println("Room Contents:");
        System.out.println(currentRoom.getContents());

        System.out.println("\nWhat do you want to do?");
        List<String> options = getAvailableOptions(currentRoom);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }

        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice (1-" + options.size() + "): ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= options.size()) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + options.size() + ".");
            }
        }

        String selectedOption = options.get(choice - 1);

        switch (selectedOption) {
            case "EAT":
                Food food = currentRoom.getFirstFoodItem();
                return CommandFactory.createEatCommand((Adventurer) character, food);
            case "FIGHT":
                return CommandFactory.createFightCommand((Adventurer) character);
            case "MOVE":
                Room neighbor = currentRoom.getRandomNeighbor();
                if (neighbor == null) {
                    throw new IllegalStateException("No neighboring rooms available to move to.");
                }
                return CommandFactory.createAdventurerMoveCommand((Adventurer) character);

            case "WEAR_ARMOR":
                Armor armor = currentRoom.getFirstArmor();
                return CommandFactory.createWearCommand(character, armor);
            case "DO_NOTHING":
                return CommandFactory.createDoNothingCommand(character);
            default:
                throw new IllegalArgumentException("Invalid option selected.");
        }
    }

    private List<String> getAvailableOptions(Room room) {
        List<String> options = new java.util.ArrayList<>();
        if (room.hasFood()) {
            options.add("EAT");
        }
        if (room.hasLivingCreatures()) {
            options.add("FIGHT");
        }
        if (room.hasArmor()) {
            options.add("WEAR_ARMOR");
        }
        options.add("MOVE");
        options.add("DO_NOTHING");
        return options;
    }
}
