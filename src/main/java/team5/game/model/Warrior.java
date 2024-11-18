package team5.game.model;

import java.util.Random;

public class Warrior extends Hero {
    public Warrior(String theName) {
        super(theName, 500, 20, 8, new SpecialAttack(0, 15));
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        int damage = getSpecialAttack().getDamage();
        double multiplier = 1 + Math.random();
        Random rand = new Random();
        int value = rand.nextInt(10);
        System.out.println(value);
        if (value == 5) {
            multiplier += 2;
        } else if (value == 8) {
            multiplier = 0;
        }
        damage = (int) (damage * multiplier);
        theOther.setHealth(theOther.getHealth() - damage);
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Does average damage, but has " + 
                        "a chance to do no damage or triple damage");
        return builder.toString();
    }
}