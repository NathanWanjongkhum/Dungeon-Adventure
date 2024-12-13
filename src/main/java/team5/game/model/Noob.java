package team5.game.model;

/**
 * The Noob Class. Used for debugging losing state.
 */
public class Noob extends Hero {
    /**
     * The default constructor for Noob
     * 
     * @param theName the name of the Noob
     */
    protected Noob(final String theName) {
        super(theName, 1, 0, 1, new SpecialAttack(0, 0));
    }

    /**
     * {@inheritDoc}. Noob does nothing
     */
    @Override
    public int useSpecialAttack(final DungeonCharacter theOther) {
        int damage = this.getSpecialAttack().getDamage();
        return damage;
    }

    /** {@inheritDoc} */
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Does nothing\n");
        return builder.toString();
    }

    /** {@inheritDoc} */
    @Override
    public String specialAttackText() {
        return "Awaiting death\n";
    }
}
