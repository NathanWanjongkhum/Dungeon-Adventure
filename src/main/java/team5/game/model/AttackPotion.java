package team5.game.model;

public class AttackPotion extends AbstractConsumables {
    private static final int ATTACK_INCREASE = 20;
    private int myAttackIncrease;
    
    /**
     * Default constructor for healing potion
     */
    public AttackPotion() {
        super();
        myAttackIncrease = ATTACK_INCREASE;
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
     * Sets the amount of health a potion heals 
     * 
     * @param theHealthRestore the health restored
     */
    public void setAttackIncrease(int theAttackIncrease) {
        myAttackIncrease = theAttackIncrease;
    }
    public void useItem(DungeonCharacter theCharacter) {
        theCharacter.setMaxDamage(theCharacter.getMaxDamage() + getAttackIncrease());
        theCharacter.setMinDamage(theCharacter.getMinDamage() + getAttackIncrease());
    }
}
