package csci.ooad.polymorphia;

import csci.ooad.polymorphia.characters.*;
import csci.ooad.polymorphia.maze.Maze;
import org.apache.commons.cli.*;

import java.util.List;

public class GameConfigurator {
    static int SECONDS_TO_PAUSE_BETWEEN_TURNS = 1;

    private final Maze.Builder mazeBuilder;

    GameConfigurator(CommandLine cmdLine) throws ParseException {
        mazeBuilder = Maze.getNewBuilder();
        buildMazeFromArguments(cmdLine);
    }

    public static void main(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = parser.parse(getOptions(), args);
        GameConfigurator gameConfig = new GameConfigurator(cmdLine);
        gameConfig.createAndStartGame();
        System.exit(0);
    }


    static Options getOptions() {
        Option numAdventurers = Option.builder("a")
                .longOpt("numberOfAdventurers")
                .argName("numAdventurers")
                .hasArg()
                .numberOfArgs(1)
                .type(Number.class)
                .desc("The number of adventurers to place in the maze")
                .build();

        Option numCreatures = Option.builder("c")
                .longOpt("numberOfCreatures")
                .argName("numCreatures")
                .hasArg()
                .type(Number.class)
                .numberOfArgs(1)
                .desc("The number of creatures to place in the maze")
                .build();

        Option numDemons = Option.builder("d")
                .longOpt("numberOfDemons")
                .argName("numDemons")
                .hasArg()
                .type(Number.class)
                .numberOfArgs(1)
                .desc("The number of demons to place in the maze")
                .build();

        Option numFoodItems = Option.builder("f")
                .longOpt("numberOfFoodItems")
                .argName("numFoodItems")
                .hasArg()
                .numberOfArgs(1)
                .type(Number.class)
                .desc("The number of food items to place in the maze")
                .build();

        Option numArmoredSuits = Option.builder("m")
                .longOpt("numberOfArmoredSuits")
                .argName("numArmoredSuits")
                .hasArg()
                .numberOfArgs(1)
                .type(Number.class)
                .desc("The number of armored suits in the maze")
                .build();

        Option numRooms = Option.builder("r")
                .longOpt("numberOfRooms")
                .argName("numRooms")
                .hasArg()
                .numberOfArgs(1)
                .type(Number.class)
                .desc("The number of rooms in the maze")
                .build();

        // Add this option for --humanPlayer
        Option humanPlayer = Option.builder("h")
                .longOpt("humanPlayer")
                .argName("humanPlayerName")
                .hasArg()
                .numberOfArgs(1)
                .type(String.class)
                .desc("The human player's name")
                .build();

        Options options = new Options();
        options.addOption(numAdventurers);
        options.addOption(numCreatures);
        options.addOption(numDemons);
        options.addOption(numFoodItems);
        options.addOption(numArmoredSuits);
        options.addOption(numRooms);
        options.addOption(humanPlayer); // Include humanPlayer option here

        return options;
    }


    private void buildMazeFromArguments(CommandLine cmdLine) throws ParseException {
        int numRooms = cmdLine.hasOption("r") ? ((Number) cmdLine.getParsedOptionValue("r")).intValue() : 6;
        int numAdventurers = cmdLine.hasOption("a") ? ((Number) cmdLine.getParsedOptionValue("a")).intValue() : 1;
        int numCreatures = cmdLine.hasOption("c") ? ((Number) cmdLine.getParsedOptionValue("c")).intValue() : 2;
        int numDemons = cmdLine.hasOption("d") ? ((Number) cmdLine.getParsedOptionValue("d")).intValue() : 1;
        int numFoodItems = cmdLine.hasOption("f") ? ((Number) cmdLine.getParsedOptionValue("f")).intValue() : 3;
        int numArmoredSuits = cmdLine.hasOption("m") ? ((Number) cmdLine.getParsedOptionValue("m")).intValue() : 2;

        mazeBuilder.createFullyConnectedRooms(numRooms);

        // Add human player if the "h" option is provided
        if (cmdLine.hasOption("h")) {
            String humanPlayerName = cmdLine.getOptionValue("h");
            mazeBuilder.createAndAddHumanPlayer(humanPlayerName);
        }

        // Add other adventurers, creatures, demons, food, and armor
        mazeBuilder.createAndAddAdventurers(numAdventurers);
        mazeBuilder.createAndAddCreatures(numCreatures);
        mazeBuilder.createAndAddDemons(numDemons);
        mazeBuilder.createAndAddFoodItems(numFoodItems);
        mazeBuilder.createAndAddArmors(numArmoredSuits);
    }




    public void createAndStartGame() {
        Maze maze = mazeBuilder.build();
        Polymorphia game = new Polymorphia(maze);
        game.play();
    }
}
