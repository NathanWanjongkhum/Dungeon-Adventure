package team5.game.model;

/**
 * The Priestess Hero class. Used by player in battles.
 */
public class Priestess extends Hero {
    /** The windup time for the special ability for Priestess */
    private static final int TURNS = 1;

    /**
     * Constructor for Priestess class
     * 
     * @param theName the name of the hero
     */
    protected Priestess(final String theName) {
        super(theName, 100, 20, 25, new SpecialAttack(TURNS, 5));
    }

    /**
     * {@inheritDoc}. Priestess heals for the special attack
     */
    @Override
    public int useSpecialAttack(final DungeonCharacter theOther) {
        if (this.getSpecialAttack().getTurns() == 0) {
            heal(this.getSpecialAttack().getDamage());
            this.getStatusEffects().setRegenDuration(2);
            this.getStatusEffects().setRegenAmount(this.getSpecialAttack().getDamage());
            this.getSpecialAttack().setTurns(this.getSpecialAttack().getTurns() - 1);
        }
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.getStats());
        builder.append("\nSpecial Move: Charges for 1 turn and heals 5 HP " +
                "then heals an additional 5 HP for 2 turns.\n");
        return builder.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void resetTurns() {
        getSpecialAttack().setTurns(TURNS);
    }

    /** {@inheritDoc} */
    @Override
    public String specialAttackText() {
        return String.format("%s casted a healing and restored %d HP!\n",
                getName(), getSpecialAttack().getDamage());
    }
}