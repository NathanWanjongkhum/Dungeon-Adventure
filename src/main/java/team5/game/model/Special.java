package team5.game.model;

public interface Special{
    /**
     * Returns the special attack object
     * 
     * @return the special attack object
     */
    public SpecialAttack getSpecialAttack();
    /**
     * The special attack of the hero
     * 
     * @param theOther the other DungeonCharacter taking the attack
     * @return the damage the special attack does
     */
    public int useSpecialAttack(final DungeonCharacter theOther);
    /**
     * The text when doing special attack
     * 
     * @return a string of special attack dialogue
     */
    public String specialAttackText();
    /**
     * Resets the windup time of special attack (if any)
     */
    public void resetTurns();

}
