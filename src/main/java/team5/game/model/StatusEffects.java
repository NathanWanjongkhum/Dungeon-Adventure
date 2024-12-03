package team5.game.model;

import java.io.Serializable;

/**
 * The status effect class
 * 
 * @author Holden Tsang
 * @version
 */
public class StatusEffects implements Serializable {
    private static final long serialVersionUID = 1L;

    /** The boolean if vulnerable is active */
    private boolean myVulnerable;
    /** The boolean if regen is active */
    private boolean myRegen;
    private boolean myDamageIncrease;
    /** The vulnerable duration */
    private int myVulDuration;
    /** The regen duration */
    private int myRegenDuration;
    private int myDamageDuration;
    /** The regen healing amount */
    private int myRegenAmount;
    private int myDamageAmount;
    /**
     * Constructor for status effect
     */
    public StatusEffects() {
        myVulnerable = false;
        myRegen = false;
        myDamageIncrease = false;
        myVulDuration = 0;
        myRegenDuration = 0;
        myDamageDuration = 0;
        myRegenAmount = 0;
        myDamageAmount = 0;
    }

    /**
     * Checks if vulnerable is active
     * 
     * @return true if vulnerable is active, false otherwise
     */
    public boolean isVulnerable() {
        myVulnerable = myVulDuration > 0;
        return myVulnerable;
    }

    /**
     * Checks if regen is active
     * 
     * @return true if regen is active, false otherwise
     */
    public boolean isRegen() {
        myRegen = myRegenDuration > 0;
        return myRegen;
    }
    public boolean isDamageIncrease() {
        myDamageIncrease = myDamageDuration > 0;
        return myDamageIncrease;
    }
    public boolean hasEffect() {
        return isDamageIncrease() || isRegen() || isVulnerable();
    }
    public boolean hasMultipleStatus() {
        boolean debuff = myVulnerable;
        boolean buff = myRegen || myDamageIncrease;
        return debuff && buff;
    }
    /**
     * Gets the vulnerable duration
     * 
     * @return the vulnerable duration
     */
    public int getVulnerableDuration() {
        return myVulDuration;
    }

    /**
     * Gets the regen duration
     * 
     * @return the regen duration
     */
    public int getRegenDuration() {
        return myRegenDuration;
    }
    public int getDamageIncreaseDuration() {
        return myDamageDuration;
    }
    /**
     * Gets the regen amount
     * 
     * @return the regen amount
     */
    public int getRegenAmount() {
        return myRegenAmount;
    }
    public int getDamageAmount() {
        return myDamageAmount;
    }
    /**
     * Sets the Vulnerable duration
     * 
     * @param theDuration how long vulnerable last
     */
    public void setVulnerableDuration(final int theDuration) {
        myVulDuration = theDuration;
    }

    /**
     * Sets the regen duration
     * 
     * @param theDuration how long regen lasts
     */
    public void setRegenDuration(final int theDuration) {
        myRegenDuration = theDuration;
    }
    public void setDamageDuration(final int theDuration) {
        myDamageDuration = theDuration;
    }
    /**
     * Sets the regen amount
     * 
     * @param theAmount how much regen heals for
     */
    public void setRegenAmount(final int theAmount) {
        myRegenAmount = theAmount;
    }
    public void setDamageIncrease(final int theAmount) {
        myDamageAmount = theAmount;
    }
    //Could just be like getStatusDescriptions instead of toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Active Status Effects:\n");
        if (isVulnerable()) {
            String vul = String.format("Vulnerable for %d turns\n", getVulnerableDuration());
            builder.append(vul);
        }
        if (isRegen()) {
            String regen = String.format("Regen for %d HP for %d turns\n", getRegenAmount(), getRegenDuration());
            builder.append(regen);
        }
        if (isDamageIncrease()) {
            String damage = String.format("Increase Damage by %d for %d turns\n", getDamageAmount(), getDamageIncreaseDuration());
            builder.append(damage);
        }
        return builder.toString();

    }
}
