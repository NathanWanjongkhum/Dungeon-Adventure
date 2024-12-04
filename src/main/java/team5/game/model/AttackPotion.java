package team5.game.model;

public class AttackPotion extends AbstractConsumables {
    private static final int ATTACK_INCREASE = 20;
    private static final int TURNS = 2;

    private int myAttackIncrease;
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
     * Gets the amount of health a potion retores
     * 
     * @return the amount fo health restored
     */
    public int getAttackIncrease() {
        return myAttackIncrease;
    }
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
    public void setTurns(final int theTurns) {
        myTurns = theTurns;
    }
    @Override
    public int useItem(DungeonCharacter theCharacter) {
        theCharacter.getStatusEffects().setDamageIncrease(myAttackIncrease);
        theCharacter.getStatusEffects().setDamageDuration(myTurns + 1);
        return myAttackIncrease;
    }
}