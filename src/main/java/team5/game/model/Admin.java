package team5.game.model;

/**
 * The Admin class. Used for debugging win states.
 */
public class Admin extends Hero {
    /**
     * Admin constructor
     * 
     * @param theName the name of the admin
     */
    protected Admin(final String theName) {
        super(theName, 100000, 10000, 20000, new SpecialAttack(0, 0));
    }

    /**
     * {@inheritDoc}. Admin does damage equal to the other character's max health
     */
    @Override
    public int useSpecialAttack(final DungeonCharacter theOther) {
        int damage = theOther.getMaxHealth();
        theOther.getStatusEffects().setVulnerableDuration(10);
        damage = this.checkVulnerableDamage(theOther, damage);
        theOther.setHealth(theOther.getHealth() - damage);
        return damage;
    }

    /** {@inheritDoc} */
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Instakills\n");
        return builder.toString();
    }

    /** {@inheritDoc} */
    @Override
    public String specialAttackText() {
        return "Death\n";
    }
}
