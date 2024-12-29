/*package csci.ooad.polymorphia.stepdefs

import csci.ooad.polymorphia.EventType
import csci.ooad.polymorphia.FoodFactory
import csci.ooad.polymorphia.characters.*
import csci.ooad.polymorphia.maze.Maze
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class MazeStepDefs {
    World world
    Maze maze
    int room_count;
    CharacterFactory characterFactory = new CharacterFactory()
    FoodFactory foodFactory = new FoodFactory()
    Maze.Builder mazeBuilder = null// Declare mazeBuilder here

    MazeStepDefs(World aWorld) {
        world = aWorld
    }

    @Given("I have a maze with the following attributes:")
    public void iHaveAMazeWithTheFollowingAttributes(Map<String, Integer> mazeAttributes) {

        if(mazeBuilder == null && (mazeAttributes.get("number of rooms") == 1)){
            mazeBuilder = Maze.getNewBuilder(characterFactory, foodFactory)
            mazeBuilder.createRoom("SingleRoom")
        }

        else if (mazeBuilder == null && (mazeAttributes.get("number of rooms") != 1)) {
            mazeBuilder = Maze.getNewBuilder(characterFactory, foodFactory)
            mazeBuilder.createConnectedRooms(1, mazeAttributes.get("number of rooms"))
        }

        // Populate the maze with adventurers, knights, etc., only if the number is not null
        Integer numAdventurers = mazeAttributes.get("number of adventurers");
        if (numAdventurers != null) {
            mazeBuilder.createAndAddAdventurers(numAdventurers);
        }

        Integer numKnights = mazeAttributes.get("number of knights");
        if (numKnights != null) {
            mazeBuilder.createAndAddKnights(numKnights);
        }

        Integer numCowards = mazeAttributes.get("number of cowards");
        if (numCowards != null) {
            mazeBuilder.createAndAddCowards(numCowards);
        }

        Integer numGluttons = mazeAttributes.get("number of gluttons");
        if (numGluttons != null) {
            mazeBuilder.createAndAddGluttons(numGluttons);
        }

        Integer numCreatures = mazeAttributes.get("number of creatures");
        if (numCreatures != null) {
            mazeBuilder.createAndAddCreatures(numCreatures);
        }

        Integer numDemons = mazeAttributes.get("number of demons");
        if (numDemons != null) {
            mazeBuilder.createAndAddDemons(numDemons);
        }

        Integer numFoodItems = mazeAttributes.get("number of food items");
        if (numFoodItems != null) {
            mazeBuilder.createAndAddFoodItems(numFoodItems);
        }

        // Store the builder without building the maze yet
        world.maze = mazeBuilder.build()
        maze = world.maze
    }

    @And("the maze should contain:")
    public void theMazeShouldContain(Map<String, Integer> expectedCounts) {
        // Count the specific types of characters across the maze
        int knightsCount = maze.getLivingAdventurers().count { it instanceof Knight }
        int cowardsCount = maze.getLivingAdventurers().count { it instanceof Coward }
        int gluttonsCount = maze.getLivingAdventurers().count { it instanceof Glutton }
        int demonsCount = maze.getLivingCreatures().count { it instanceof Demon }

        // Assertions based on expected counts
        assert maze.getLivingAdventurers().size() == expectedCounts.get("adventurers") : "Expected ${expectedCounts.get("adventurers")} adventurers, but found ${maze.getLivingAdventurers().size()}"
        assert knightsCount == expectedCounts.get("knights") : "Expected ${expectedCounts.get("knights")} knights, but found $knightsCount"
        assert cowardsCount == expectedCounts.get("cowards") : "Expected ${expectedCounts.get("cowards")} cowards, but found $cowardsCount"
        assert gluttonsCount == expectedCounts.get("gluttons") : "Expected ${expectedCounts.get("gluttons")} gluttons, but found $gluttonsCount"
        assert maze.getLivingCreatures().size() == expectedCounts.get("creatures") : "Expected ${expectedCounts.get("creatures")} creatures, but found ${maze.getLivingCreatures().size()}"
        assert demonsCount == expectedCounts.get("demons") : "Expected ${expectedCounts.get("demons")} demons, but found $demonsCount"
    }

    @And("food items should be distributed across multiple rooms")
    public void foodItemsShouldBeDistributedAcrossMultipleRooms() {
        def roomsWithFood = maze.getRooms().count { room -> room.hasFood() }
        assert roomsWithFood > 1
    }

    @Then("the maze should have the specified mix of characters and food items")
    public void theMazeShouldHaveSpecifiedMixOfCharactersAndFoodItems() {
        println "Debug Info: Adventurers in maze: ${maze.getLivingAdventurers().size()}"
        println "Debug Info: Creatures in maze: ${maze.getLivingCreatures().size()}"
        println "Debug Info: Food items in maze: ${maze.getRooms().count { room -> room.hasFood() }}"

        // Check if the maze has at least one adventurer if expected
        assert maze.getLivingAdventurers().size() > 0 : "The maze should contain at least one adventurer"

        // Check if the maze has at least one creature if expected
        assert maze.getLivingCreatures().size() > 0 : "The maze should contain at least one creature"

        // Check if the maze contains at least one food item
        assert maze.getRooms().any { room -> room.hasFood() } : "The maze should contain at least one food item"
    }


    @And("food items should be randomly distributed across the maze")
    public void foodItemsShouldBeRandomlyDistributedAcrossTheMaze() {
        long foodRoomsCount = maze.getRooms().count { room -> room.hasFood() }
        assert foodRoomsCount > 1 : "Food items should be spread across multiple rooms"
    }

    @When("the maze is created as a fully connected layout with 5 rooms")
    public void theMazeIsCreatedAsAFullyConnectedLayout() {
        mazeBuilder = Maze.getNewBuilder()
                .createFullyConnectedRooms(5)

    }

    @Then("each room should contain at least one character or item")
    public void eachRoomShouldContainAtLeastOneCharacterOrItem() {
        assert maze.getRooms().every { room ->
            room.hasLivingAdventurers() || room.hasLivingCreatures() || room.hasFood()
        }
    }

    @And("all rooms should be interconnected with each other")
    public void allRoomsShouldBeInterconnectedWithEachOther() {
        maze.getRooms().each { room ->
            assert room.getNeighbors().size() == maze.getRooms().size() - 1 :
                    "Each room should be connected to every other room"
        }
    }


    @And("food items should be placed across multiple rooms")
    public void foodItemsShouldBePlacedAcrossMultipleRooms() {
        long roomsWithFood = maze.getRooms().count { room -> room.hasFood() }
        assert roomsWithFood > 1 : "Food items should be present in more than one room"
    }


    @And("a room named {string} with no neighbors")
    public void aRoomNamedWithNoNeighbors(String arg0) {
        this.room_count = 1;
    }

    @And("all characters are in room {string}")
    public void allCharactersAreInRoom(String arg0) {
        if (room_count == 1){
            world.maze = Maze.getNewBuilder().createRoom(arg0).addCreatures(world.creatures).addAdventurers(world.adventurers).build()
        }
    }

    @Given("I have a {int}x{int} grid layout maze")
    public void iHaveAXGridLayoutMaze(int arg0, int arg1) {
        if (arg0 == 2){
            mazeBuilder = Maze.getNewBuilder().create2x2Grid();
        }
        else if (arg0 == 3){
            mazeBuilder = Maze.getNewBuilder().create3x3Grid();
        }
    }

    @And("both are in the same room")
    public void bothAreInTheSameRoom() {
        mazeBuilder = Maze.getNewBuilder().createConnectedRooms(1, "Room1", "Room2")
        mazeBuilder = mazeBuilder.addToRoom("Room1", world.adventurers.get(0)).addToRoom("Room1", world.creatures.get(0))
        maze = mazeBuilder.build()
        world.maze = maze
    }

    @And("all three are in the same room")
    public void allThreeAreInTheSameRoom() {
        mazeBuilder = Maze.getNewBuilder().createConnectedRooms(1, "Room1", "Room2")
        mazeBuilder = mazeBuilder.addToRoom("Room1", world.adventurers.get(0)).addToRoom("Room1", world.creatures.get(0)).addToRoom("Room1", world.foodItems.get(0))
        maze = mazeBuilder.build()
        world.maze = maze
    }

    @Given("a maze with an observer tracking death events")
    public void aMazeWithAnObserverTrackingDeathEvents() {
        // Initialize mazeBuilder and set up rooms and characters
        mazeBuilder = Maze.getNewBuilder(characterFactory, foodFactory)
                .createConnectedRooms(1, 2);  // Create a simple connected maze with at least 2 rooms

        // Build the maze and assign the result to world.maze
        world.maze = mazeBuilder.build();

        // Optional: Set maze variable for use in other steps
        maze = world.maze;
    }

    @And("the observer should log one death event")
    public void theObserverShouldLogOneDeathEvent() {
        int deathEvents = world.observer.getEventCount(EventType.Death);
        assert deathEvents == 1 : "Expected exactly one death event, but found " + deathEvents;
    }

    @And("the observer should log the death of the Creature")
    public void theObserverShouldLogTheDeathOfTheCreature() {
        int deathEvents = world.observer.getEventCount(EventType.Death);
        assert deathEvents > 0 : "Expected the observer to log the Creature's death, but it was not logged.";
    }

    @And("the observer should log the death of the Adventurer")
    public void theObserverShouldLogTheDeathOfTheAdventurer() {
        int deathEvents = world.observer.getEventCount(EventType.Death);
        assert deathEvents > 0 : "Expected the observer to log the Adventurer's death, but it was not logged.";
    }
}

*/