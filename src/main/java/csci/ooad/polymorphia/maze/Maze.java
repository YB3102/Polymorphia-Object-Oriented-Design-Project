package csci.ooad.polymorphia.maze;

import csci.ooad.polymorphia.ArtifactFactory;
import csci.ooad.polymorphia.Food;
import csci.ooad.polymorphia.NoSuchRoomException;
import csci.ooad.polymorphia.characters.*;
import csci.ooad.polymorphia.characters.Character;

import java.util.*;


public class Maze {
    private final Random rand = new Random();

    private List<Room> rooms = new ArrayList<>();

    public Maze() {
    }

    public int size() {
        return rooms.size();
    }

    @Override
    public String toString() {
        return String.join("\n\n", rooms.stream().map(Object::toString).toList());
    }

    public Boolean hasLivingCreatures() {
        return rooms.stream().anyMatch(Room::hasLivingCreatures);
    }

    public Boolean hasLivingAdventurers() {
        return rooms.stream().anyMatch(Room::hasLivingAdventurers);
    }

    private Room getRandomRoom() {
        return rooms.get(rand.nextInt(rooms.size()));
    }

    public List<Adventurer> getLivingAdventurers() {
        List<Adventurer> adventurers = new ArrayList<>();
        for (Room room : rooms) {
            adventurers.addAll(room.getLivingAdventurers());
        }
        return adventurers;
    }

    public List<Creature> getLivingCreatures() {
        List<Creature> creatures = new ArrayList<>();
        for (Room room : rooms) {
            creatures.addAll(room.getLivingCreatures());
        }
        return creatures;
    }


    // Add to Room.java
    private final List<Armor> armors = new ArrayList<>();

    public void add(Armor armor) {
        armors.add(armor);
    }

    public boolean hasArmor() {
        return !armors.isEmpty();
    }

    public Armor getFirstArmor() {
        if (armors.isEmpty()) {
            return null; // Or throw an exception
        }
        return armors.remove(0);
    }

    public List<Armor> getArmors() {
        return List.copyOf(armors);
    }


    public List<Character> getLivingCharacters() {
        List<Character> characters = new ArrayList<>();
        for (Room room : rooms) {
            characters.addAll(room.getLivingCharacters());
        }
        return characters;
    }

    public void addToRandomRoom(Object item) {
        Room randomRoom = getRandomRoom();

        if (item instanceof Character character) {
            randomRoom.add(character);
        } else if (item instanceof Armor armor) {
            randomRoom.add(armor);
        } else if (item instanceof Food food) {
            randomRoom.add(food);
        } else {
            throw new IllegalArgumentException("Unsupported item type: " + item.getClass().getName());
        }
    }








    public static Builder getNewBuilder() {
        return new Builder();
    }

    public boolean hasDemon() {
        return rooms.stream().anyMatch(Room::hasDemon);
    }

    public Room getRoom(String roomName) throws NoSuchRoomException {
        Optional<Room> namedRoom = rooms.stream().filter(r -> r.getName().equals(roomName)).findFirst();
        if (namedRoom.isEmpty()) {
            throw new NoSuchRoomException(roomName);
        }
        return namedRoom.get();
    }

    Room connect(Room base, Room neighbor) {
        base.addNeighbor(neighbor);
        neighbor.addNeighbor(base);
        return base;
    }

    public static class Builder {
        public static String[] NAMES = new String[]{
                "Rivendell", "Mordor", "BagEnd", "Swamp", "Crystal Palace", "Pool of Lava",
                "Stalactite Cave", "Goblin's Fountain", "Dragon's Den", "Troll Bridge",
                "Dungeon", "Pit of Despair", "Sanctuary",
                "Den of Souls", "Map Room", "Fangorn Forest", "Room of Horrors",
                "Misty Mountains", "Shire", "Hobbiton",
                "Gondor", "Chamber of Secrets", "Room of Doom", "Room of Gloom",
                "Titan's Lair", "Room of Shadows", "Room of Echoes", "Room of Whispers"
        };


        private final CharacterFactory characterFactory;
        final private ArtifactFactory foodFactory;

        private Boolean distributeSequentially = false;
        private int currentRoomIndex = -1;  // This is incremented before any use

        private Room nextRoom() {
            if (distributeSequentially) {
                currentRoomIndex = (currentRoomIndex + 1) % maze.getRooms().size();
                return maze.getRooms().get(currentRoomIndex);
            }
            return maze.getRandomRoom();
        }

        Builder() {
            this(new CharacterFactory(), new ArtifactFactory());
        }

        private final Maze maze = new Maze();
        Map<String, Room> roomMap = new HashMap<>();

        public Builder(CharacterFactory characterFactory, ArtifactFactory foodFactory) {
            this.characterFactory = characterFactory;
            this.foodFactory = foodFactory;
        }

        public Builder createRoom(String name) {
            Room initialRoom = new Room(name);
            maze.addRoom(initialRoom);
            return this;
        }


        public Builder addToRandomRoom(Object item) {
            this.maze.addToRandomRoom(item);
            return this;
        }


        public Builder addFood(String foodName, double nutritionValue) {
            Food food = new Food(foodName, nutritionValue);
            maze.addToRandomRoom(food);
            return this;
        }

        public Builder addArmor(String armorName, double protectionValue) {
            Armor armor = new Armor(armorName, protectionValue);
            maze.addToRandomRoom(armor);
            return this;
        }

        public Builder createAndAddHumanPlayer(String humanPlayerName) {
            Adventurer humanPlayer = characterFactory.createAdventurer(humanPlayerName, new HumanStrategy());
            this.addAdventurers(List.of(humanPlayer)); // Add the human player to a random room
            return this;
        }


        public Builder createGridOfRooms(int rows, int columns, String[][] roomNames) {
            Room[][] roomGrid = new Room[rows][columns];
            maze.rooms = new ArrayList<>();
            // Notice -- don't use i and j. Use row and column -- they are better
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    Room newRoom = new Room(roomNames[row][column]);
                    roomGrid[row][column] = newRoom;
                    maze.rooms.add(newRoom);
                }
            }

            // Now connect the rooms
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    Room currentRoom = roomGrid[row][column];
                    if (row > 0) {
                        maze.connect(currentRoom, roomGrid[row - 1][column]);
                    }
                    if (column > 0) {
                        maze.connect(currentRoom, roomGrid[row][column - 1]);
                    }
                }
            }
            return this;
        }

        public Builder create2x2Grid() {
            String[][] roomNames = new String[][]{{"Northwest", "Northeast"}, {"Southwest", "Southeast"}};
            return createGridOfRooms(2, 2, roomNames);
        }

        public Builder create3x3Grid() {
            String[][] roomNames = new String[][]{{"Northwest", "North", "Northeast"}, {"West", "Center", "East"}, {"Southwest", "South", "Southeast"}};
            return createGridOfRooms(3, 3, roomNames);
        }

        public Builder createFullyConnectedRooms(Integer numRooms) {
            String[] roomNames = createRoomNames(numRooms);
            return createFullyConnectedRooms(roomNames);
        }

        private static String[] createRoomNames(Integer numRooms) {
            // Room names must be unique, as the maze drawer uses them as keys to connect rooms
            String[] roomNames = new String[numRooms];
            int roomNameIndex = (new Random()).nextInt(NAMES.length);
            for (int i = 0; i < numRooms; i++) {
                roomNames[i] = NAMES[(roomNameIndex + i) % NAMES.length];
            }
            return roomNames;
        }

        public Builder createFullyConnectedRooms(String... roomNames) {
            maze.rooms = new ArrayList<>();
            for (String roomName : roomNames) {
                Room currentRoom = new Room(roomName);
                roomMap.put(roomName, currentRoom);
                for (Room otherRoom : maze.rooms) {
                    maze.connect(currentRoom, otherRoom);
                }
                maze.rooms.add(currentRoom);
            }
            return this;
        }

        public Builder createConnectedRooms(Integer minConnections, Integer numRooms) {
            return createConnectedRooms(minConnections, createRoomNames(numRooms));
        }

        public Builder createConnectedRooms(Integer minConnections, String... roomNames) {
            maze.rooms = new ArrayList<>();
            for (String roomName : roomNames) {
                Room currentRoom = new Room(roomName);
                roomMap.put(roomName, currentRoom);
                maze.rooms.add(currentRoom);
            }

            Boolean oldDistributionSetting = this.distributeSequentially;
            distributeSequentially();
            int realMinimumConnections = Math.min(minConnections, Math.max(maze.rooms.size() - 1, 1));
            for (Room room : maze.rooms) {
                while (room.numberOfNeighbors() < realMinimumConnections) {
                    Room neighbor = nextRoom();
                    if (!room.equals(neighbor) && !room.hasNeighbor(neighbor)) {
                        maze.connect(room, neighbor);
                    }
                }
            }
            this.distributeSequentially = oldDistributionSetting;
            return this;
        }

        public Builder createAndAddAdventurers(String... adventurerNames) {
            for (String adventurerName : adventurerNames) {
                nextRoom().add(characterFactory.createAdventurer(adventurerName));
            }
            return this;
        }

        public Builder addAdventurers(List<Adventurer> adventurers) {
            for (Adventurer adventurer : adventurers) {
                nextRoom().add(adventurer);
            }
            return this;
        }

        public Builder createAndAddAdventurers(Integer numAdventurers) {
            return addAdventurers(characterFactory.createNumberOfAdventurers(numAdventurers));
        }

        public Builder createAndAddKnights(Integer numKnights) {
            addAdventurers(characterFactory.createNumberOfKnights(numKnights));
            return this;
        }

        public Builder createAndAddGluttons(Integer numGluttons) {
            addAdventurers(characterFactory.createNumberOfGluttons(numGluttons));
            return this;
        }

        public Builder createAndAddCowards(Integer numCowards) {
            addAdventurers(characterFactory.createNumberOfCowards(numCowards));
            return this;
        }

        public Builder addAdventurers(Adventurer... adventurers) {
            for (Adventurer adventurer : adventurers) {
                nextRoom().add(adventurer);
            }
            return this;
        }

        public Builder addCreatures(List<Creature> creatures) {
            for (Creature creature : creatures) {
                nextRoom().add(creature);
            }
            return this;
        }

        public Builder createAndAddCreatures(Integer numCreatures) {
            return addCreatures(characterFactory.createNumberOfCreatures(numCreatures));
        }


        public Builder createAndAddCreatures(String... names) {
            for (String name : names) {
                nextRoom().add(characterFactory.createCreature(name));
            }
            return this;
        }

        public Builder createAndAddDemons(Integer numDemons) {
            addCreatures(characterFactory.createNumberOfDemons(numDemons));
            return this;
        }

        public Builder createAndAddDemons(String... names) {
            for (String name : names) {
                nextRoom().add(characterFactory.createDemon(name));
            }
            return this;
        }

        public Maze build() {
            if (maze.rooms.isEmpty()) {
                throw new IllegalStateException("Maze cannot be built without any rooms.");
            }
            return maze;
        }

        public Builder createAndAddFoodItems(String... foodNames) {
            for (String foodName : foodNames) {
                nextRoom().add(foodFactory.create(foodName));
            }
            return this;
        }

        public Builder createAndAddFoodItems(Integer numItems) {
            List<Food> foodItems = foodFactory.createNumberOf(numItems);
            for (Food food : foodItems) {
                nextRoom().add(food);
            }
            return this;
        }

        // Add the armor-related methods here
        public Builder createAndAddArmors(String... armorNames) {
            for (String armorName : armorNames) {
                nextRoom().add(foodFactory.createArmor(armorName));
            }
            return this;
        }

        public Builder createAndAddArmors(Integer numItems) {
            List<Armor> armorItems = foodFactory.createNumberOfArmors(numItems);
            for (Armor armor : armorItems) {
                nextRoom().add(armor);
            }
            return this;
        }


        public Builder addCreatures(Creature... creatures) {
            for (Creature creature : creatures) {
                nextRoom().add(creature);
            }
            return this;
        }

        public Builder addToRoom(String roomName, Adventurer adventure) {
            roomMap.get(roomName).add(adventure);
            return this;
        }

        public Builder addToRoom(String roomName, Creature creature) {
            roomMap.get(roomName).add(creature);
            return this;
        }

        public Builder addToRoom(String roomName, Food foodItem) {
            roomMap.get(roomName).add(foodItem);
            return this;
        }

        public Builder createAndAddKnights(String... names) {
            for (String name : names) {
                nextRoom().add(characterFactory.createKnight(name));
            }
            return this;
        }

        public Builder createAndAddGluttons(String... names) {
            for (String name : names) {
                nextRoom().add(characterFactory.createGlutton(name));
            }
            return this;
        }

        public Builder createAndAddCowards(String... names) {
            for (String name : names) {
                nextRoom().add(characterFactory.createCoward(name));
            }
            return this;
        }

        public Builder distributeSequentially() {
            distributeSequentially = true;
            return this;
        }

        public Builder distributeRandomly() {
            distributeSequentially = false;
            return this;
        }

    }

    private void addRoom(Room aRoom) {
        rooms.add(aRoom);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    // New method to find an Adventurer by name
    /*public Adventurer findAdventurerByName(String name) {
        for (Room room : rooms) {
            for (Adventurer adventurer : room.getLivingAdventurers()) {
                if (adventurer.getName().equals(name)) {
                    return adventurer;
                }
            }
        }
        return null;  // Return null if no adventurer with the given name is found
    }

    // New method to find a Creature by name
    public Creature findCreatureByName(String name) {
        for (Room room : rooms) {
            for (Creature creature : room.getLivingCreatures()) {
                if (creature.getName().equals(name)) {
                    return creature;
                }
            }
        }
        return null;  // Return null if no creature with the given name is found
    }*/
    //Privatized Room Add/Remove Methods
}
