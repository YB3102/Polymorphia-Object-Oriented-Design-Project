# Polymorphia Object-Oriented Design Project

## Overview
Polymorphia is an Object-Oriented Design (OOD) project created to showcase principles of software engineering, including encapsulation, abstraction, inheritance, and polymorphism. The project simulates a game where various characters navigate through a maze and interact with elements such as food, artifacts, and other characters.

This project is implemented in Java and uses tests to validate the functionality of its components.

---

## Project Structure

### Main Codebase (`src/main/java`)
The primary codebase is organized into the following packages:

#### 1. **characters**
Contains classes and interfaces related to the various character types in the game, each with unique behaviors and interactions.

#### 2. **maze**
Includes classes representing the maze structure, rooms, and interactions within the maze, such as:
- **Maze.java**: The maze object that handles the structure of same namesake.
- **Room.java**: The room object class that constitutes the maze.

#### 3. **polymorphia**
The main code runner. This is where the entirety of the game logic plays out.

#### 3. **observer**
Implements the observer pattern to track game events, such as deaths and character actions, through interfaces and concrete observers like:
- **EventBus.java**: Manages event notifications.
- **IObservable.java**: Interface for observable entities.
- **IObserver.java**: Interface for observers.

#### 4. **miscellaneous classes**
Additional classes include:
- **Die.java**: Represents a game die.
- **EventType.java**: Enum for event types.
- **Food.java**: Represents food items in the maze.

---

### Test Codebase (`src/test/java`)
Unit tests are provided for each major class to ensure functionality and maintain code quality. Tests include:

- **MazeTest.java**: Tests for the maze and its configurations.
- **RoomTest.java**: Tests for room interactions and behaviors.
- **PolymorphiaTest.java**: High-level tests for overall project functionality.
- **DieTest.java**: Tests for randomness and behavior of the game die.
- **FoodTest.java**: Tests for interactions with food items.
- **GameObserverTest.java**: Validates event tracking and logging by observers.

Additionally, behavior-driven development (BDD) scenarios are implemented using Groovy for complex interactions within the maze.

---

## Getting Started

### Prerequisites
1. **Java Development Kit (JDK):** Ensure you have specifically JDK 21.
2. **Gradle:** Used for managing project dependencies and builds.
3. **IntelliJ IDEA:** Recommended IDE for development and running tests.

---

### Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Polymorphia-Object-Oriented-Design-Project
   ```
2. Open the project in IntelliJ IDEA.
3. Ensure all dependencies are resolved using Gradle.
4. Run the test suite to verify the setup:
   - Navigate to `src/test/java` in IntelliJ.
   - Right-click on a test file (e.g., `MazeTest.java`) and select **Run Tests**.

---

## How to Run
This project is primarily executed through its tests. Use IntelliJ IDEA to:
1. Open any test file.
2. Run the desired tests to verify functionality or behavior.
3. Debug tests if necessary to trace issues.

---

## Key Features
- **Event Logging:** Tracks key game events using the observer pattern.
- **Encapsulation:** Prevents unauthorized modifications to the maze and its components.
- **Polymorphic Behaviors:** Characters exhibit unique actions based on their type.
- **Error Handling:** Uses custom exceptions to handle invalid game scenarios.

---

