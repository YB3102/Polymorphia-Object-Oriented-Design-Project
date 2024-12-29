package csci.ooad.polymorphia;

import csci.ooad.layout.intf.IMaze;
import csci.ooad.layout.intf.IMazeObserver;
import csci.ooad.layout.intf.IMazeSubject;
import csci.ooad.polymorphia.characters.Adventurer;
import csci.ooad.polymorphia.characters.Character;
import csci.ooad.polymorphia.characters.Command;
import csci.ooad.polymorphia.characters.Creature;
import csci.ooad.polymorphia.maze.Maze;
import csci.ooad.polymorphia.observer.MazeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Polymorphia implements IMazeSubject, IObservable {
    private static final Logger logger = LoggerFactory.getLogger(Polymorphia.class);
    private static int gameNumber = 1;

    List<IMazeObserver> observers = new ArrayList<>();

    public void attach(IMazeObserver observer) {
        observers.add(observer);
    }

    @Override
    public List<IMazeObserver> getObservers() {
        return observers;
    }

    private final String name;
    private final Maze maze;
    private Integer turnCount = 0;
    private final Random rand = new Random();

    public Polymorphia(Maze maze) {
        this("Polymorphia Game " + gameNumber, maze);
    }

    public Polymorphia(String name, Maze maze) {

        this.name = name;
        this.maze = maze;
        logger.info(maze.toString());
        gameNumber++;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.join("\n", status()) + "\n" + maze.toString();
    }

    private List<String> status() {
        return List.of(
                "Polymorphia " + getName() + " after turn " + turnCount,
                "# of live adventurers: " + numberOfLivingAdventurers(),
                "# of live creatures: " + numberOfLivingCreatures()
        );
    }

    public int numberOfLivingAdventurers() {
        return getLivingAdventurers().size();
    }

    public List<Adventurer> getLivingAdventurers() {
        return maze.getLivingAdventurers();
    }

    public int numberOfLivingCreatures() {
        return getLivingCreatures().size();
    }

    // Game is over when all creatures are killed
    // or all adventurers are killed
    public boolean isOver() {
        if (maze.getLivingAdventurers().isEmpty()) {
            System.out.println("The creatures won!");
            return true;
        }
        if (maze.getLivingCreatures().isEmpty()) {
            System.out.println("The adventurers won!");
            return true;
        }
        return false;
    }


    public Boolean hasLivingCreatures() {
        return maze.hasLivingCreatures();
    }

    public Boolean hasLivingAdventurers() {
        return maze.hasLivingAdventurers();
    }

    public void playTurn() {
        if (turnCount == 0) {
            logger.info("Starting play...");
            postMessage(EventType.GameStart, "Game is starting");
        }
        turnCount += 1;

        // Process player action first
        Adventurer player = getLivingAdventurers().get(0); // Assuming one human player
        System.out.println("You are in room: " + player.getCurrentLocation().getName());
        System.out.println("Room Contents:");
        player.getCurrentLocation().getContents().forEach(System.out::println);

        // Handle player action
        Command playerCommand = player.getStrategy().getCommand(player);
        playerCommand.execute();
        notifyObservers(status());

        // Handle NPC actions after player action
        List<Character> npcs = getLivingCharacters().stream()
                .filter(character -> !(character instanceof Adventurer))
                .toList();

        for (Character npc : npcs) {
            Command npcCommand = npc.getStrategy().getCommand(npc);
            npcCommand.execute();
            notifyObservers(status());
        }
    }


    public List<Character> getLivingCharacters() {
        return maze.getLivingCharacters();
    }

    public void postMessage(EventType eventType, String eventDescription) {
        logger.info(eventDescription);
        EventBus.getInstance().postMessage(eventType, eventDescription);
    }

    public void play() {
        while (!isOver()) {
            logger.info(this.toString());
            playTurn();
        }
        String eventDescription = "The game ended after " + turnCount + " turns.\n";
        if (hasLivingAdventurers()) {
            eventDescription += "The adventurers won! Left standing are:\n" + getAdventurerNames() + "\n";
        } else if (hasLivingCreatures()) {
            eventDescription += "The creatures won! Left standing are:\n" + getCreatureNames() + "\n";
        } else {
            eventDescription += "No team won! Everyone died!\n";
        }
        postMessage(EventType.GameOver, eventDescription);
    }

    String getAdventurerNames() {
        return String.join("\n ", getLivingCharacters().stream().map(Object::toString).toList());
    }

    String getCreatureNames() {
        return String.join("\n ", getLivingCreatures().stream().map(Object::toString).toList());
    }

    public List<Creature> getLivingCreatures() {
        return maze.getLivingCreatures();
    }

    public Character getWinner() {
        if (!isOver() || !hasLivingCharacters()) {
            // No one has won yet or no one won -- all died
            return null;
        }
        return getLivingCharacters().getFirst();
    }

    private boolean hasLivingCharacters() {
        return !getLivingCharacters().isEmpty();
    }

    @Override
    public IMaze getMaze() {
        return new MazeAdapter(maze);
    }

    public void detach(IMazeObserver mazeObserver) {

    }
}