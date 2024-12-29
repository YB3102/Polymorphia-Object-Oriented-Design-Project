/*package csci.ooad.polymorphia.stepdefs

import csci.ooad.polymorphia.Food
import csci.ooad.polymorphia.NoFoodException
import csci.ooad.polymorphia.characters.*
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class CharacterStepDefs {
    World world
    CharacterFactory characterFactory


    CharacterStepDefs(World aWorld) {
        this.world = aWorld
        this.characterFactory = new CharacterFactory()  // Initialize characterFactory here if not done elsewhere
    }


    @Given("a Coward {string}")
    public void aCoward(String arg0) {
        world.adventurers.add(characterFactory.createCoward(arg0))
    }

    @And("a Creature {string}")
    public void aCreature(String arg0) {
        world.creatures.add(characterFactory.createCreature(arg0))
    }

    @Given("a Knight named {string} with {int} health")
    public void aKnightNamedWithHealth(String arg0, int arg1) {
        world.adventurers.add(characterFactory.createKnight(arg0))
    }

    @And("a Creature named {string} with {int} health")
    public void aCreatureNamedWithHealth(String arg0, int arg1) {
        world.creatures.add(characterFactory.createCreature(arg0, arg1))
    }

    @Given("a Coward named {string} with {int} health")
    public void aCowardNamedWithHealth(String arg0, int arg1) {
        world.adventurers.add(characterFactory.createCoward(arg0))
    }

    @Given("a Glutton named {string} with {int} health")
    public void aGluttonNamedWithHealth(String arg0, int arg1) {
        world.adventurers.add(characterFactory.createGlutton(arg0))
    }

    @And("a Demon named {string} with {int} health")
    public void aDemonNamedWithHealth(String arg0, int arg1) {
        world.creatures.add(characterFactory.createDemon(arg0))
    }

    @And("a Food named {string} with {int} health value")
    public void aFoodNamedWithHealthValue(String arg0, int arg1) {
        world.foodItems.add(new Food(arg0, arg1))
    }

    @And("an Adventurer named {string} with {int} health point")
    public void anAdventurerNamedWithHealthPoint(String name, int health) {
        Adventurer adventurer = characterFactory.createAdventurer(name);
        double currentHealth = adventurer.getHealth();

        if (currentHealth > health) {
            adventurer.loseHealth(currentHealth - health);  // Reduce health to the desired level
        } else if (currentHealth < health) {
            throw new UnsupportedOperationException("Cannot increase health beyond the default.");
        }

        world.maze.addToRandomRoom(adventurer);  // Add to a random room in the maze
    }

    @And("a Creature named {string} with high attack damage")
    public void aCreatureNamedWithHighAttackDamage(String name) {
        Creature creature = characterFactory.createCreature(name, 20);
        world.maze.addToRandomRoom(creature);  // Add to a random room; high attack is assumed default
    }

    @When("the Chimera fights the Adventurer")
    public void theChimeraFightsTheAdventurer() {
        Creature chimera = world.maze.findCreatureByName("Chimera");
        Adventurer adventurer = world.maze.findAdventurerByName("Bill");  // Replace with the actual name if needed
        if (chimera != null && adventurer != null) {
            chimera.fight(adventurer);  // Engage them in a fight
        } else {
            throw new NullPointerException("Chimera or Adventurer not found in the maze.");
        }
    }

    @Then("the Adventurer should die")
    public void theAdventurerShouldDie() {
        Adventurer adventurer = world.maze.findAdventurerByName("Lucas");
        assert adventurer == null || !adventurer.isAlive() : "Expected the Adventurer to be dead, but they are still alive.";
    }

    @Then("the Creature should die")
    public void theCreatureShouldDie() {
        Creature creature = world.maze.findCreatureByName("Troll");
        assert creature == null || !creature.isAlive() : "Expected the Creature to be dead, but they are still alive.";
    }

    @And("two Creatures named {string} and {string}")
    public void twoCreaturesNamedAnd(String name1, String name2) {
        Creature creature1 = characterFactory.createCreature(name1);
        Creature creature2 = characterFactory.createCreature(name2);
        world.maze.addToRandomRoom(creature1);
        world.maze.addToRandomRoom(creature2);
    }

    @And("{string} has low health")
    public void hasLowHealth(String name) {
        Creature creature = world.maze.findCreatureByName(name);
        if (creature != null && creature.getHealth() > 10) {
            creature.loseHealth(creature.getHealth() - 10);  // Reduce to low health
        }
    }

    @And("an Adventurer named {string} with low health")
    public void anAdventurerNamedWithLowHealth(String name) {
        Adventurer adventurer = characterFactory.createAdventurer(name);
        if (adventurer.getHealth() > 10) {
            adventurer.loseHealth(adventurer.getHealth() - 10);  // Set to low health
        }
        world.maze.addToRandomRoom(adventurer);
    }

    @When("both the Creature and Adventurer fight")
    public void bothTheCreatureAndAdventurerFight() {
        Creature troll = world.maze.findCreatureByName("Troll");
        Adventurer lucas = world.maze.findAdventurerByName("Lucas");
        if (troll != null && lucas != null) {
            troll.fight(lucas);  // Engage them in a fight
        } else {
            throw new NullPointerException("Troll or Lucas not found in the maze.");
        }
    }

    @Then("both take damage")
    public void bothTakeDamage() {
        Creature troll = world.maze.findCreatureByName("Troll");
        Adventurer lucas = world.maze.findAdventurerByName("Lucas");
        assert troll != null && troll.getHealth() < Character.DEFAULT_INITIAL_HEALTH : "Expected the Creature 'Troll' to take damage.";
        assert lucas != null && lucas.getHealth() < Character.DEFAULT_INITIAL_HEALTH : "Expected the Adventurer 'Lucas' to take damage.";
    }



    @Given("a Glutton named {string} with low health")
    public void aGluttonNamedWithLowHealth(String name) {
        Creature glutton = characterFactory.createGlutton(name);
        if (glutton.getHealth() > 10) {
            glutton.loseHealth(glutton.getHealth() - 10);  // Set to low health
        }
        world.maze.addToRandomRoom(glutton);
    }

    @When("the Glutton attempts to consume food and finds none")
    public void theGluttonAttemptsToConsumeFoodAndFindsNone() {
        Creature glutton = world.maze.findCreatureByName("Ravenous Glutton");
        if (glutton != null) {
            try {
                glutton.consumeFood();  // This will throw NoFoodException if no food is available
            } catch (NoFoodException e) {
                System.out.println("Caught NoFoodException: " + e.getMessage());
            }
        } else {
            throw new NullPointerException("Ravenous Glutton not found in the maze.");
        }
    }

    @Then("the Glutton should die of starvation")
    public void theGluttonShouldDieOfStarvation() {
        Creature glutton = world.maze.findCreatureByName("Ravenous Glutton");
        assert glutton == null || !glutton.isAlive() : "Expected the Glutton to be dead due to starvation, but it is still alive.";
    }




    @And("the observer should log the Glutton's death")
    public void theObserverShouldLogTheGluttonSDeath() {
        boolean loggedDeath = world.observer.hasLoggedDeathEvent("Glutton");  // Assuming the observer has a method to check for specific death logs
        assert loggedDeath : "Expected the observer to log the Glutton's death, but it was not logged.";
    }


    @When("the Creature attacks the Adventurer")
    public void theCreatureAttacksTheAdventurer() {
        Creature creature = world.maze.findCreatureByName("Troll");  // Replace with the creature's name if different
        Adventurer adventurer = world.maze.findAdventurerByName("Bill");  // Replace with the adventurer's name if different
        if (creature != null && adventurer != null) {
            creature.fight(adventurer);
        } else {
            throw new NullPointerException("Creature or Adventurer not found in the maze.");
        }
    }

    @When("both the Creature and Adventurer take lethal damage simultaneously")
    public void bothTheCreatureAndAdventurerTakeLethalDamageSimultaneously() {

        Creature troll = world.maze.findCreatureByName("Troll");
        Adventurer lucas = world.maze.findAdventurerByName("Lucas");
        if (troll != null && lucas != null) {
            troll.loseHealth(100);  // Apply 100 damage to the Creature
            lucas.loseHealth(100);  // Apply 100 damage to the Adventurer
        } else {
            throw new NullPointerException("Troll or Lucas not found in the maze.");
        }
    }
}
*/