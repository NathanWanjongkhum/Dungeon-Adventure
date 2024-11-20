package team5.game.model;
/**
 * The status effect class
 * 
 * @author Holden Tsang
 * @version
 */
public class StatusEffects {
    /** The boolean if vulnerable is active */
    private boolean myVulnerable;
    /** The boolean if regen is active */
    private boolean myRegen;
    /** The vulnerable duration */
    private int myVulDuration;
    /** The regen duration */
    private int myRegenDuration;
    /** The regen healing amount */
    private int myRegenAmount;
    /**
     * Constructor for status effect
     */
    public StatusEffects() {
        myVulnerable = false;
        myRegen = false;
        myVulDuration = 0;
        myRegenDuration = 0;
        myRegenAmount = 0;
    }
    /**
     * Checks if vulnerable is active
     * 
     * @return true if vulnerable is active, false otherwise
     */
    public boolean isVulnerable() {
        if (myVulDuration > 0) {
            myVulnerable = true;
        } else {
            myVulnerable = false;
        }
        return myVulnerable;
    }
    /**
     * Checks if regen is active
     * 
     * @return true if regen is active, false otherwise
     */
    public boolean isRegen() {
        if (myRegenDuration > 0) {
            myRegen = true;
        } else {
            myRegen = false;
        }
        return myRegen;
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
     * Gets the regen amount
     * 
     * @return the regen amount
     */
    public int getRegenAmount() {
        return myRegenAmount;
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
     * Sets the regen amount
     * 
     * @param theAmount how much regen heals for
     */
    public void setRegenAmount(final int theAmount) {
        myRegenAmount = theAmount;
    }
    //Could just be like getStatusDescriptions instead of toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Active Status Effects:\n");
        if(isVulnerable()) {
            String vul = String.format("Vulnerable for %d turns\n", getVulnerableDuration());
            builder.append(vul);
        }
        if (isRegen()) {
            String regen = String.format("Regen for %d HP for %d turns\n", getRegenAmount(), getRegenDuration());
            builder.append(regen);
        }
        return builder.toString();

    }
}
