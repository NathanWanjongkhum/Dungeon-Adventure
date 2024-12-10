package team5.game.model;

import java.util.Random;

public class Warrior extends Hero {
    /** Different special attack text for the different outcomes */
    private String mySpecialText;
    /**
     * Warrior Hero constructor
     * 
     * @param theName the name of the hero
     */
    public Warrior(final String theName) {
        super(theName, 500, 20, 8, new SpecialAttack(0, 15));
        mySpecialText = "";
    }
    /**
     * The Warrior's special attack is random effects
     */
    @Override
    public int useSpecialAttack(DungeonCharacter theOther) {
        int damage = this.getSpecialAttack().getDamage();
        double multiplier = 1 + Math.random();
        Random rand = new Random();
        int value = rand.nextInt(10);
        switch(value) {
            case 1:
                multiplier += 2;
                mySpecialText = "Triple Damage!\n";
                break;
            case 2:
                theOther.getStatusEffects().setVulnerableDuration(2);
                mySpecialText = "Vulnerabled the Enemy for 2 turns!\n";
                break;
            case 3:
                theOther.getStatusEffects().setRegenAmount(10);
                theOther.getStatusEffects().setRegenDuration(3);
                mySpecialText = "Somehow healed the enemy 10 HP for 3 turns!\n";
                break;
            case 4:
                this.getStatusEffects().setRegenAmount(10);
                this.getStatusEffects().setRegenDuration(2);
                mySpecialText = "Gained Regeneration for 10 HP for 2 turns!\n";
                break;

            case 5:
                multiplier += 1;
                mySpecialText = "Double damage!\n";
                break;
            case 6:
                multiplier += 0;
                mySpecialText = "Normal Attack\n";
                break;
            case 7:
                this.getStatusEffects().setVulnerableDuration(3);
                mySpecialText = "Tripped and became vulnerable for 2 turns!\n";
                break;
            case 8:
                multiplier = 0;
                mySpecialText = "Dropped their sword!\n";
                break;
            case 9:
                multiplier -= 1;
                mySpecialText = "Attacked with a dull blade!\n";
                break;
            default:
                multiplier += 100;
                mySpecialText = "Something good happened!\n";
                break;
        }
        damage = (int) (damage * multiplier);
        damage = this.checkVulnerableDamage(theOther, damage);
        theOther.setHealth(theOther.getHealth() - damage);
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Does average damage, but has " + 
                        "a chance to do something special (or not)!\n");
        return builder.toString();
    }
    @Override
    public String specialAttackText() {
        return mySpecialText;
    }
}