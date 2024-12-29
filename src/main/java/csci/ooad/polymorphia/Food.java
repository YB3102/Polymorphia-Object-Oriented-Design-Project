package csci.ooad.polymorphia;

import java.text.DecimalFormat;

public class Food {
    public static int DEFAULT_FOOD_HEALTH_VALUE = 1;
    private static final DecimalFormat formatter = new DecimalFormat("0.0");

    private final String name;
    private final double healthValue;

    public Food(String name) {
        this.name = name;
        this.healthValue = DEFAULT_FOOD_HEALTH_VALUE;
    }

    public Food(String name, double healthValue) {
        this.name = name;
        this.healthValue = healthValue;
    }

    @Override
    public String toString() {
        return name + "(" + formatter.format(healthValue) +")";
    }

    public double getHealthValue() {
        return healthValue;
    }

}
