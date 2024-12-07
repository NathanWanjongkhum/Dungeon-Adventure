package team5.game.model;
/**
 * The Mage Hero Class
 * 
 * @author
 * @version
 */
public class Mage extends Hero {
    /** The windup time for the special ability for Priestess */
    private static final int TURNS = 2;
    /**
     * The Mage constructor
     * 
     * @param theName the name of the mage hero
     */
    public Mage(final String theName) {
        super(theName, 250, 50, 10, new SpecialAttack(TURNS, 300));
    }
    /**
     * The mage's special attack 
     */
    @Override
    public int useSpecialAttack(final DungeonCharacter theOther) {
        int damage = 0;
        if(this.getSpecialAttack().getTurns() == 0) {
            damage = this.getSpecialAttack().getDamage();
            damage = super.checkVulnerableDamage(theOther, damage);
            theOther.setHealth(theOther.getHealth() - damage);
            this.getSpecialAttack().setTurns(this.getSpecialAttack().getTurns() - 1);
        } else {
            this.getStatusEffects().setVulnerableDuration(this.getSpecialAttack().getTurns() + 1);
        }
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("Special Move: Takes double damage for 3 turns, then does massive damage to enemies " +
                        "on the third attack!\n");
        return builder.toString();
    }
    @Override
    public void resetTurns() {
        getSpecialAttack().setTurns(TURNS);
    }
    @Override
    public String specialAttackText() {
        return "Unleashed their magic!\n";
    }
}