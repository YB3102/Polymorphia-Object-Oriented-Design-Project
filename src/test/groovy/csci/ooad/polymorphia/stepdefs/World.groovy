/*
package csci.ooad.polymorphia.stepdefs

import csci.ooad.polymorphia.EventBus
import csci.ooad.polymorphia.EventType
import csci.ooad.polymorphia.Food
import csci.ooad.polymorphia.Polymorphia
import csci.ooad.polymorphia.characters.*
import csci.ooad.polymorphia.maze.Maze
import csci.ooad.polymorphia.observer.TestObserver
import io.cucumber.java.After

class World {
    Maze maze
    List<Adventurer> adventurers = []
    List<Knight> knights = []
    List<Coward> cowards = []
    List<Glutton> gluttons = []
    List<Creature> creatures = []
    List<Demon> demons = []
    List<Food> foodItems = []

    Polymorphia polymorphia

    static TestObserver observer = new TestObserver();

    @After
    void tearDown(){
        EventBus.getInstance().detach(observer)
        observer.clear()

        adventurers.clear()
        knights.clear()
        cowards.clear()
        gluttons.clear()
        creatures.clear()
        demons.clear()
        foodItems.clear()

        // Optionally reset the maze and polymorphia instances if they need reinitialization.
        // maze = new Maze()
        // polymorphia = new Polymorphia()
    }

    public static void attachObserver(List<EventType> events) {
        for (EventType event : events){
            EventBus.getInstance().attach(observer, event);
        }
    }

    // Initializes a new Maze builder with specified parameters.
    void createMaze(int rooms, int adventurersCount, int knightsCount, int cowardsCount,
                    int gluttonsCount, int creaturesCount, int demonsCount, int foodItemsCount) {
        def builder = Maze.getNewBuilder().createConnectedRooms(rooms)

        // Populate characters
        adventurers = builder.createAndAddAdventurers(adventurersCount)
        knights = builder.createAndAddKnights(knightsCount)
        cowards = builder.createAndAddCowards(cowardsCount)
        gluttons = builder.createAndAddGluttons(gluttonsCount)
        creatures = builder.createAndAddCreatures(creaturesCount)
        demons = builder.createAndAddDemons(demonsCount)

        // Add food items
        foodItems = builder.createAndAddFoodItems(foodItemsCount)

        // Finalize the maze creation
        maze = builder.build()
    }

    // Creates a grid layout for the maze and assigns it to this world.
    void createGridLayoutMaze(int rows, int columns) {
        maze = Maze.getNewBuilder().createGridOfRooms(rows, columns).build()
    }

    // Configures the maze for sequential distribution of items.
    void setSequentialDistribution(boolean isSequential) {
        maze.setSequentialDistribution(isSequential)
    }

    // Distributes items across the maze as per current configuration.
    void distributeItems() {
        maze.distributeItems()
    }

    // Checks if each room in the maze contains at least one character or item.
    boolean eachRoomContainsCharacterOrItem() {
        return maze.getRooms().every { room -> room.getCharacters().size() > 0 || room.getItems().size() > 0 }
    }

    // Checks if food items are distributed across multiple rooms.
    boolean foodItemsDistributedAcrossMultipleRooms() {
        def roomsWithFood = maze.getRooms().count { room -> !room.getFoodItems().isEmpty() }
        return roomsWithFood > 1
    }

    // Checks if all rooms are interconnected in a fully connected layout.
    boolean allRoomsInterconnected() {
        return maze.getRooms().every { room -> room.getConnectedRooms().size() == maze.getRooms().size() - 1 }
    }

    void playPolymorphia(){
        polymorphia = new Polymorphia("poly",this.maze)
        polymorphia.play();
    }

}
*/
