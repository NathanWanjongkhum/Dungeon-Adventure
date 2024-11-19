package team5.game.model;

import java.util.Random;

public class Warrior extends Hero {
    private String mySpecialText;

    public Warrior(String theName) {
        super(theName, 500, 20, 8, new SpecialAttack(0, 15));
        mySpecialText = "";
    }
    public int useSpecialAttack(DungeonCharacter theOther) {
        int damage = getSpecialAttack().getDamage();
        double multiplier = 1 + Math.random();
        Random rand = new Random();
        int value = rand.nextInt(10);
        switch(value) {
            case 1:
                multiplier += 2;
                mySpecialText = "\nTriple Damage!\n";
                break;
            case 2:
                theOther.getStatusEffects().setVulnerableDuration(2);
                mySpecialText = "\nVulnerabled the Enemy for 2 turns!\n";
                break;
            case 3:
                theOther.getStatusEffects().setRegenAmount(10);
                theOther.getStatusEffects().setRegenDuration(3);
                mySpecialText = "\nSomehow healed the enemy 10 HP for 3 turns!\n";
                break;
            case 4:
                getStatusEffects().setRegenAmount(10);
                getStatusEffects().setRegenDuration(2);
                mySpecialText = "\nGained Regeneration for 10 HP for 2 turns!\n";
                break;

            case 5:
                multiplier += 1;
                mySpecialText = "\nDouble damage!\n";
                break;
            case 6:
                multiplier += 0;
                mySpecialText = "Normal Attack";
                break;
            case 7:
                getStatusEffects().setVulnerableDuration(3);
                mySpecialText = "\nTripped and became vulnerable for 2 turns!\n";
                break;
            case 8:
                multiplier = 0;
                mySpecialText = "\nDropped their sword!\n";
                break;
            case 9:
                multiplier -= 1;
                mySpecialText = "\nAttacked with a dull blade!\n";
                break;
            default:
                multiplier += 100;
                mySpecialText = "\nSomething good happened!\n";
                break;
        }
        damage = (int) (damage * multiplier);
        damage = super.checkVulnerableDamage(theOther, damage);
        theOther.setHealth(theOther.getHealth() - damage);
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("Special Move: Does average damage, but has " + 
                        "a chance to do something special (or not)!\n");
        return builder.toString();
    }
    @Override
    public String specialAttackText() {
        return mySpecialText;
    }
}