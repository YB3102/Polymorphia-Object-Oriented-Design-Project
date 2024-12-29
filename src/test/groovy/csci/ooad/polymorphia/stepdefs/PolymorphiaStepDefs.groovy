/*package csci.ooad.polymorphia.stepdefs

import csci.ooad.polymorphia.EventType
import csci.ooad.polymorphia.characters.Coward
import csci.ooad.polymorphia.characters.Creature
import csci.ooad.polymorphia.characters.Demon
import csci.ooad.polymorphia.characters.Glutton
import io.cucumber.java.Before
import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class PolymorphiaStepDefs {

    World world


    PolymorphiaStepDefs(World aWorld) {
        world = aWorld
    }

    @Before
    void setUp(){
        world.attachObserver(List.of(
                EventType.Death,
                EventType.GameStart,
                EventType.GameOver,
                EventType.FightOutcome,
                EventType.Moved,
                EventType.AteSomething, EventType.TurnEnded, EventType.Moved, EventType.PickedUpSomething))
    }


    @When("I play the game in the created maze")
    void iPlayTheGame() {
        // Implement me
        world.playPolymorphia()
    }

    @Then("I should be told that either all the adventurers or all of the creatures have died")
    void iShouldBeToldThatEitherAllTheAdventurersOrAllOfTheCreaturesHaveDied() {
        // Implement me
        List<String> gameOverEvents = world.observer.getEventsByType(EventType.GameOver);

        // Check if any of the messages in the GameOver events matches the expected outcome
        boolean adventurersWon = gameOverEvents.stream()
                .anyMatch(event -> event.contains("The adventurers won!"));
        boolean creaturesWon = gameOverEvents.stream()
                .anyMatch(event -> event.contains("The creatures won!"));
        boolean everyoneDied = gameOverEvents.stream()
                .anyMatch(event -> event.contains("No team won! Everyone died!"));

        // Assert that one of the expected outcomes is true
        assert(adventurersWon || creaturesWon || everyoneDied);
    }

    @Then("the game should be over")
    void theGameShouldBeOver() {
        // Implement me
        assert(world.observer.getEventCount(EventType.GameOver) == 1)
    }

    @When("adventurer {string} executes his turn")
    public void adventurerExecutesHisTurn(String arg0) {
        world.playPolymorphia();
        world.observer.printEvents();

        assert(world.observer.getEventCount(EventType.GameStart) == 1)
    }

    @Then("a fight took place")
    public void aFightTookPlace() {
        //List<String> fightEvents = world.observer.getReceivedEvents();
        //assert(fightEvents.stream().anyMatch(event -> event.contains("won a battle against")));
        assert(world.observer.getEventCount(EventType.FightOutcome) >= 1)
    }

    @And("creature {string} lost some health")
    public void creatureLostSomeHealth(String arg0) {
        Creature ogre = world.creatures.get(0);
        assert(ogre.getHealth() < 3.0)
    }

    @When("the game starts")
    public void theGameStarts() {
        world.playPolymorphia()
    }

    @Then("Ogre should be dead")
    public void ogreShouldBeDead() {
        Creature ogre = world.creatures.get(0);
        assert(!ogre.isAlive())
    }

    @And("Game should be over")
    public void gameShouldBeOver() {
        assert(world.observer.getEventCount(EventType.GameOver) == 1)
    }

    @And("no movement events should be recorded")
    public void noMovementEventsShouldBeRecorded() {
        assert(world.observer.getEventCount(EventType.Moved) == 0)
    }

    @And("Coward should be dead")
    public void cowardShouldBeDead() {
        Coward henry = world.adventurers.get(0);
        assert(!henry.isAlive())
    }

    @And("Ogre should be alive")
    public void ogreShouldBeAlive() {
        Creature ogre = world.creatures.get(0);
        assert(ogre.isAlive())
    }

    @And("no fight events should be recorded")
    public void noFightEventsShouldBeRecorded() {
        assert(world.observer.getEventCount(EventType.FightOutcome) == 0)
    }

    @And("Glutton should be dead")
    public void gluttonShouldBeDead() {
        Glutton henry = world.adventurers.get(0);
        assert(!henry.isAlive())
    }

    @And("Demon should be alive")
    public void demonShouldBeAlive() {
        Demon ogre = world.creatures.get(0);
        assert(ogre.isAlive())
    }

    @And("no eating events should be recorded")
    public void noEatingEventsShouldBeRecorded() {
        assert(world.observer.getEventCount(EventType.AteSomething) == 0)
    }

    @And("the creature lost some health")
    public void theCreatureLostSomeHealth() {
        assert(world.observer.getEventCount(EventType.FightOutcome) >= 1)
    }
}*/