package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Room;

public class WearCommand implements Command {
    private final Character character;
    private final Armor armor;

    public WearCommand(Character character, Armor armor) {
        this.character = character;
        this.armor = armor;
    }

    @Override
    public void execute() {
        if (character instanceof ArmoredCharacter) {
            ((ArmoredCharacter) character).addArmor(armor);
        } else {
            ArmoredCharacter armoredCharacter = new ArmoredCharacter(character);
            armoredCharacter.addArmor(armor);

            Room currentRoom = character.getCurrentLocation();
            currentRoom.remove(character);
            currentRoom.add(armoredCharacter);
        }

        System.out.println(character.getName() + " put on " + armor.getName());
    }
}
