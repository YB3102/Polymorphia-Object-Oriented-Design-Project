package csci.ooad.polymorphia.characters;
import csci.ooad.polymorphia.Food;

public class CommandFactory {

    public static Command createFightCommand(Adventurer character) {
        return new FightCommand(character);
    }

    public static Command createFleeCommand(Adventurer character) {
        return new FleeCommand(character);
    }

    public static Command createEatCommand(Adventurer character, Food food) {
        return new EatCommand(character, food);
    }

    public static Command createAdventurerMoveCommand(Adventurer character) {
        return new AdventurerMoveCommand(character);
    }

    public static Command createDoNothingCommand(Character character){
        return new DoNothingCommand(character);
    }

    public static Command createDemonMoveCommand(Creature character) {
        return new DemonMoveCommand(character);
    }

    public static Command createDemonFightCommand(Creature character) {
        return new DemonFightCommand(character);
    }

    public static Command createWearCommand(Character character, Armor armor) {
        return new WearCommand(character, armor);
    }

}
