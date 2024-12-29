package csci.ooad.polymorphia.maze;

import csci.ooad.polymorphia.Die;
import csci.ooad.polymorphia.Food;
import csci.ooad.polymorphia.NoFoodException;
import csci.ooad.polymorphia.characters.Adventurer;
import csci.ooad.polymorphia.characters.Character;
import csci.ooad.polymorphia.characters.Creature;
import csci.ooad.polymorphia.characters.Armor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Room {
    private final String name;
    private final List<Room> neighbors = new ArrayList<>();
    private final List<Character> characters = new ArrayList<>();
    private final List<Food> foodItems = new ArrayList<>();
    private final List<Armor> armors = new ArrayList<>(); // New field for Armor items

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Get living adventurers in the room
    public List<Adventurer> getLivingAdventurers() {
        return characters.stream()
                .filter(Character::isAdventurer)
                .filter(Character::isAlive)
                .map(Adventurer.class::cast)
                .sorted()
                .toList();
    }

    // Get living creatures in the room
    public List<Creature> getLivingCreatures() {
        return characters.stream()
                .filter(Character::isCreature)
                .filter(Character::isAlive)
                .map(Creature.class::cast)
                .sorted()
                .toList();
    }

    // Get all contents of the room
    public List<String> getContents() {
        List<String> contents = new ArrayList<>(getLivingCharacters().stream()
                .map(Object::toString)
                .toList());
        contents.addAll(this.foodItems.stream()
                .map(Object::toString)
                .toList());
        contents.addAll(this.armors.stream()
                .map(Object::toString)
                .toList()); // Include Armor in the contents
        return contents;
    }

    // Add a neighbor to the room
    public void addNeighbor(Room neighbor) {
        assert this != neighbor; // Prevent self-neighboring
        this.neighbors.add(neighbor);
    }

    @Override
    public String toString() {
        String representation = "\t" + name + ":\n\t\t";
        representation += String.join("\n\t\t", getContents());
        return representation;
    }

    // Add a character to the room
    public void add(Character character) {
        characters.add(character);
        character.enterRoom(this);
    }

    // Check if room has living creatures
    public Boolean hasLivingCreatures() {
        return characters.stream()
                .filter(Character::isCreature)
                .filter(Character::isAlive)
                .anyMatch(Character::isAlive);
    }

    // Check if room has living adventurers
    public Boolean hasLivingAdventurers() {
        return characters.stream()
                .filter(Character::isAdventurer)
                .filter(Character::isAlive)
                .anyMatch(Character::isAlive);
    }

    public void remove(Character character) {
        characters.remove(character);
    }

    // Get a random neighbor of the room
    public Room getRandomNeighbor() {
        if (neighbors.isEmpty()) {
            return null;
        }
        return neighbors.get(Die.randomLessThan(neighbors.size()));
    }

    public void enter(Character character) {
        add(character);
    }

    // Get living characters in the room
    public List<Character> getLivingCharacters() {
        return characters.stream()
                .filter(Character::isAlive)
                .toList();
    }

    public void add(Food foodItem) {
        foodItems.add(foodItem);
    }

    public Adventurer getHealthiestAdventurer() {
        return getLivingAdventurers().stream()
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public Creature getHealthiestCreature() {
        return getLivingCreatures().stream()
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public boolean hasFood() {
        return !foodItems.isEmpty();
    }

    public Food removeFoodItem() throws NoFoodException {
        if (foodItems.isEmpty()) {
            throw new NoFoodException("No food in room");
        }
        return foodItems.remove(0);
    }

    public Boolean hasDemon() {
        return characters.stream().filter(Character::isAlive).anyMatch(Character::isDemon);
    }

    public int numberOfNeighbors() {
        return neighbors.size();
    }

    public boolean hasNeighbor(Room neighbor) {
        return neighbors.contains(neighbor);
    }

    public List<Room> getNeighbors() {
        return List.copyOf(neighbors);
    }

    public boolean contains(Food foodItem) {
        return this.foodItems.contains(foodItem);
    }

    public void removeItem(Food foodItem) {
        this.foodItems.remove(foodItem);
    }

    public Food getFirstFoodItem() {
        if (this.foodItems.isEmpty()) {
            return null;
        }
        return foodItems.get(0);
    }

    // New method: Check if the room has armor
    public boolean hasArmor() {
        return !armors.isEmpty();
    }

    // New method: Add armor to the room
    public void add(Armor armor) {
        armors.add(armor);
    }

    // New method: Get the first armor in the room
    public Armor getFirstArmor() {
        if (this.armors.isEmpty()) {
            return null;
        }
        return armors.get(0);
    }

    // New method: Remove a specific armor from the room
    public void removeArmor(Armor armor) {
        armors.remove(armor);
    }


}
