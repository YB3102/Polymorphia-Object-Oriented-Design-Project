package csci.ooad.polymorphia.characters;

import csci.ooad.polymorphia.maze.Room;

import java.util.ArrayList;
import java.util.List;

public class ArmoredCharacter extends Character {
    private static final double ARMOR_ABSORPTION_RATE = 1.0;
    private static final double WEIGHT_PENALTY_FACTOR = 0.1;
    private static final double MIN_DAMAGE = 0;


    private final Character wrappedCharacter;
    private final List<Armor> armors = new ArrayList<>();

    public ArmoredCharacter(Character wrappedCharacter) {
        super(wrappedCharacter.getName(), wrappedCharacter.getHealth(), wrappedCharacter.getStrategy());
        this.wrappedCharacter = wrappedCharacter;
    }

    public void addArmor(Armor armor) {
        armors.add(armor);
    }

    @Override
    public void loseFightDamage(Number healthPoints) {
        double absorbed = ARMOR_ABSORPTION_RATE * armors.size();
        double damage = Math.max(MIN_DAMAGE, healthPoints.doubleValue() - absorbed);
        wrappedCharacter.loseFightDamage(damage);
    }

    @Override
    public void loseMovingHealth(Number healthPoints) {
        double extraWeightPenalty = WEIGHT_PENALTY_FACTOR * armors.size();
        double adjustedLoss = healthPoints.doubleValue() + extraWeightPenalty;
        wrappedCharacter.loseMovingHealth(adjustedLoss);
    }

    @Override
    public Room getCurrentLocation() {
        return wrappedCharacter.getCurrentLocation();
    }

    @Override
    public String toString() {
        return armors.stream()
                .map(Armor::toString)
                .reduce("", (acc, armor) -> armor + " " + acc) + wrappedCharacter.toString();
    }
}
