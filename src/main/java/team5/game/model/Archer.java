package team5.game.model;

/**
 * The Archer Class
 * 
 * @author
 * @version
 */
public class Archer extends Hero {
    /**
     * The Archer Constructor
     * 
     * @param theName the name of the archer
     */
    public Archer(final String theName) {
        super(theName, 150, 10, 20, new SpecialAttack(0, 5));
    }
    @Override
    public int useSpecialAttack(final DungeonCharacter theOther) {
        int damage = 0;
        if(this.getSpecialAttack().getTurns() == 0) {
            theOther.getStatusEffects().setVulnerableDuration(2);
            damage = this.getSpecialAttack().getDamage();
            damage = this.checkVulnerableDamage(theOther, damage);
            theOther.setHealth(theOther.getHealth() - damage);
        } else {
            this.getSpecialAttack().setTurns(getSpecialAttack().getTurns() - 1);
        }
        return damage;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Does low damage, but makes " + 
                        "enemies take double damage on the next attack\n");
        return builder.toString();
    }
    @Override
    public String specialAttackText() {
        return "Vulnerabled the enemy for the next attack!\n";
    }
}