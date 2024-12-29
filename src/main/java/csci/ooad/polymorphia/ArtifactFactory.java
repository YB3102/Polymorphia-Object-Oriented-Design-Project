package csci.ooad.polymorphia;

import csci.ooad.polymorphia.characters.Armor;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ArtifactFactory {
    public static final String[] FOOD_NAMES = new String[]{
            "cupcake", "apple", "banana", "steak", "salad", "fries", "burger", "pizza", "eggs",
            "bacon", "muffin", "donut", "chicken", "pasta", "rice", "sushi", "taco", "burrito", "nachos", "chips"
    };

    public static final String[] ARMOR_NAMES = new String[]{
            "Chainmail", "Leather Armor", "Iron Armor", "Steel Plate", "Dragon Scale"
    };

    private static final double MIN_NUTRITION_VALUE = 1.0;
    private static final double MAX_NUTRITION_VALUE = 2.0;
    private static final double MIN_PROTECTION_VALUE = 0.5;
    private static final double MAX_PROTECTION_VALUE = 1.5;

    private static final Random random = new Random();

    public Food create(String name) {
        return new Food(name, random.nextDouble(MIN_NUTRITION_VALUE, MAX_NUTRITION_VALUE));
    }

    public List<Food> createNumberOf(Integer numItems) {
        return IntStream.range(0, numItems)
                .mapToObj(i -> create(FOOD_NAMES[i % FOOD_NAMES.length]))
                .toList();
    }

    public Armor createArmor(String name) {
        return new Armor(name, random.nextDouble(MIN_PROTECTION_VALUE, MAX_PROTECTION_VALUE));
    }

    public List<Armor> createNumberOfArmors(Integer numItems) {
        return IntStream.range(0, numItems)
                .mapToObj(i -> createArmor(ARMOR_NAMES[i % ARMOR_NAMES.length]))
                .toList();
    }
}
