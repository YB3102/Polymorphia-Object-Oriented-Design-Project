package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Maze;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CharacterFactory {

    public static String[] ADVENTURER_NAMES = new String[]{"Frodo", "Arwen", "Nikhil", "Sierra", "Matt", "Fran"};
    public static String[] KNIGHT_NAMES = new String[]{"Sir Lancelot", "Lady Brienne", "King Arthur", "Sir Jamey", "Aragorn", "Isildur"};
    public static String[] COWARD_NAMES = new String[]{"Sir Robin", "Sir Scaredy Cat", "Lady Faints-a-lot", "Lady Runaway", "Sir Chicken", "Lady Hides-a-lot"};
    public static String[] GLUTTON_NAMES = new String[]{"Sir Eats-a-lot", "Sir Gobbles", "Lady Munches", "Lady Snacks", "Sir Nibbles", "Lady Noshes"};
    public static String[] CREATURE_NAMES = new String[]{"Dragon", "Ogre", "Orc", "Shelob", "Troll", "Evil Wizard"};
    public static String[] DEMON_NAMES = new String[]{"Satan", "Beelzebub", "Devil", "Incubus", "Lucifer", "Succubus"};

    Random random = new Random();

    public List<Adventurer> createNumberOfAdventurers(Integer numAdventurers) {
        return IntStream.range(0, numAdventurers)
                .mapToObj(i -> new Adventurer(ADVENTURER_NAMES[i % ADVENTURER_NAMES.length], new AdventurerStrategy()))
                .map(Adventurer.class::cast)
                .toList();
    }

    public List<Creature> createNumberOfCreatures(Integer numCreatures) {
        return IntStream.range(0, numCreatures)
                .mapToObj(i -> new Creature(CREATURE_NAMES[i % CREATURE_NAMES.length], new CreatureStrategy()))
                .map(Creature.class::cast)
                .toList();
    }

    public List<Creature> createNumberOfDemons(Integer numDemons) {
        return IntStream.range(0, numDemons)
                .mapToObj(i -> new Creature(DEMON_NAMES[i % DEMON_NAMES.length],Creature.DEMON_INITIAL_HEALTH, new CreatureStrategy()))
                .map(Creature.class::cast)
                .toList();
    }

    public List<Adventurer> createNumberOfKnights(Integer numAdventurers) {
        return IntStream.range(0, numAdventurers)
                .mapToObj(i -> new Adventurer(KNIGHT_NAMES[random.nextInt(KNIGHT_NAMES.length)],Adventurer.KNIGHT_INITIAL_HEALTH, new KnightStrategy()))
                .map(Adventurer.class::cast)
                .toList();
    }

    public List<Adventurer> createNumberOfCowards(Integer numAdventurers) {
        return IntStream.range(0, numAdventurers)
                .mapToObj(i -> new Adventurer(COWARD_NAMES[random.nextInt(COWARD_NAMES.length)],Adventurer.COWARD_INITIAL_HEALTH, new CowardStrategy()))
                .map(Adventurer.class::cast)
                .toList();
    }

    public List<Adventurer> createNumberOfGluttons(Integer numAdventurers) {
        return IntStream.range(0, numAdventurers)
                .mapToObj(i -> new Adventurer(GLUTTON_NAMES[random.nextInt(GLUTTON_NAMES.length)],Adventurer.GLUTTON_INITIAL_HEALTH, new GluttonStrategy()))
                .map(Adventurer.class::cast)
                .toList();
    }

    public Adventurer createHumanAdventurer(String name) {
        return new Adventurer(name, new HumanStrategy());
    }


    public Adventurer createAdventurer(String name) {
        return new Adventurer(name, new AdventurerStrategy());
    }
    public Adventurer createAdventurer(String name, double health) {
        return new Adventurer(name, health, new AdventurerStrategy());
    }
    public Adventurer createAdventurer(String name, Strategy strategy) {
        return new Adventurer(name, Adventurer.DEFAULT_INITIAL_HEALTH, strategy);
    }

    public Adventurer createKnight(String name) {
        return new Adventurer(name, Adventurer.KNIGHT_INITIAL_HEALTH, new KnightStrategy());
    }
    public Adventurer createCoward(String name) {
        return new Adventurer(name, Adventurer.COWARD_INITIAL_HEALTH, new CowardStrategy());
    }
    public Adventurer createGlutton(String name) {
        return new Adventurer(name, Adventurer.GLUTTON_INITIAL_HEALTH, new GluttonStrategy());
    }
    public Creature createCreature(String name) { return new Creature(name, new CreatureStrategy());}
    public Creature createCreature(String name, double health) {
        return new Creature(name, health, new CreatureStrategy());
    }
    public Creature createDemon(String name) { return new Creature(name, Creature.DEMON_INITIAL_HEALTH, new DemonStrategy());}
}
