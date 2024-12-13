package team5.game.model;

public class AttackPotion extends AbstractConsumables {
    /** The amount of attack increase */
    private static final int ATTACK_INCREASE = 20;
    /** The number of turns the potion lasts */
    private static final int TURNS = 2;

    /** The amount of attack increase */
    private int myAttackIncrease;
    /** The number of turns the potion lasts */
    private int myTurns;

    /**
     * Default constructor for healing potion
     */
    public AttackPotion() {
        super();

        myAttackIncrease = ATTACK_INCREASE;
        myTurns = TURNS;
    }

    /**
     * Copy constructor for healing potion
     * 
     * @param theOther the other healing potion
     */
    private AttackPotion(final AttackPotion theOther) {
        super(theOther);
        this.myAttackIncrease = theOther.getAttackIncrease();
        this.myTurns = theOther.getEffectTurns();

    }

    /**
     * Gets the amount of health a potion retores
     * 
     * @return the amount fo health restored
     */
    public int getAttackIncrease() {
        return myAttackIncrease;
    }

    /**
     * Gets the number of turns the potion lasts
     * 
     * @return the number of turns the potion lasts
     */
    public int getEffectTurns() {
        return myTurns;
    }

    /**
     * Sets the amount of health a potion heals
     * 
     * @param theHealthRestore the health restored
     */
    public void setAttackIncrease(int theAttackIncrease) {
        myAttackIncrease = theAttackIncrease;
    }

    /**
     * Sets the number of turns the potion lasts
     * 
     * @param theTurns the number of turns the potion lasts
     */
    public void setTurns(final int theTurns) {
        myTurns = theTurns;
    }

    /**
     * {@inheritDoc}. Attack potions increase attack by 20 for 2 turns. +1 turn to
     * include the current turn.
     */
    @Override
    public int useItem(DungeonCharacter theCharacter) {
        theCharacter.getStatusEffects().setDamageIncrease(myAttackIncrease);
        theCharacter.getStatusEffects().setDamageDuration(myTurns + 1);
        return myAttackIncrease;
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return String.format("Increase attack by %s for %s turns.\n" +
                "This also increase special moves potency.", myAttackIncrease, myTurns);
    }

    /** {@inheritDoc} */
    @Override
    public AttackPotion copy() {
        return new AttackPotion(this);
    }
}
