package team5.game.model;

import java.io.Serializable;

/**
 * The status effect class
 * 
 * @author Holden Tsang
 */
public class StatusEffects implements Serializable {
    /** The serial version UID for serialization */
    private static final long serialVersionUID = 1L;

    /** The boolean if vulnerable is active */
    private boolean myVulnerable;
    /** The boolean if regen is active */
    private boolean myRegen;
    /** The boolean if damage increase is active */
    private boolean myDamageIncrease;
    /** The vulnerable duration */
    private int myVulDuration;
    /** The regen duration */
    private int myRegenDuration;
    /** The damage increase duration */
    private int myDamageDuration;
    /** The regen healing amount */
    private int myRegenAmount;
    /** The damage increase amount */
    private int myDamageAmount;

    /**
     * Constructor for status effect
     */
    protected StatusEffects() {
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

    /**
     * Checks if damage increase is active
     * 
     * @return true if damage increase is active, false otherwise
     */
    public boolean isDamageIncrease() {
        myDamageIncrease = myDamageDuration > 0;
        return myDamageIncrease;
    }

    /**
     * Checks if any status effect is active
     * 
     * @return true if any status effect is active, false otherwise
     */
    public boolean hasEffect() {
        return isDamageIncrease() || isRegen() || isVulnerable();
    }

    /**
     * Checks if any status effect is active
     * 
     * @return true if any status effect is active, false otherwise
     */
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

    /**
     * Gets the damage increase duration
     * 
     * @return the damage increase duration
     */
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

    /**
     * Gets the damage increase amount
     * 
     * @return the damage increase amount
     */
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

    /**
     * Sets the damage increase duration
     * 
     * @param theDuration how long damage increase lasts
     */
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

    /**
     * Sets the damage increase amount
     * 
     * @param theAmount how much damage increase heals for
     */
    public void setDamageIncrease(final int theAmount) {
        myDamageAmount = theAmount;
    }

    /**
     * Gets the all active status effects
     * 
     * @return the string of all active status effects
     */
    public String getEffects() {
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
            String damage = String.format("Increase Damage by %d for %d turns\n", getDamageAmount(),
                    getDamageIncreaseDuration());
            builder.append(damage);
        }

        return builder.toString();

    }
}
