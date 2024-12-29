package csci.ooad.polymorphia.characters;

import java.text.DecimalFormat;

public record Armor(String name, double protectionValue) {
    private static final DecimalFormat formatter = new DecimalFormat("0.0");

    @Override
    public String toString() {
        return name + " Armor (protection: " + formatter.format(protectionValue) + ")";
    }

    public String getName() {
        return name;
    }

}
