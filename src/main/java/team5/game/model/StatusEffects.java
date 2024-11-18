package team5.game.model;

public class StatusEffects {
    private boolean myVulnerable;
    private boolean myRegen;
    private int myVulDuration;
    private int myRegenDuration;

    public StatusEffects() {
        myVulnerable = false;
        myRegen = false;
        myVulDuration = 0;
        myRegenDuration = 0;
    }
    public boolean isVulnerable() {
        if (myVulDuration == 0) {
            myVulnerable = false;
        }
        return myVulnerable;
    }
    public boolean isRegen() {
        if (myRegenDuration == 0) {
            myRegen = false;
        }
        return myRegen;
    }
    public int getVulurableDuration() {
        return myVulDuration;
    }
    public int getRegenDuration() {
        return myRegenDuration;
    }
    public void setVulnerable(final boolean theStatus) {
        myVulnerable = theStatus;
    }
    public void setRegen(final boolean theStatus) {
        myRegen = theStatus;
    }
    public void setVulnerableDuration(final int theDuration) {
        myVulDuration = theDuration;
    }
    public void setRegenDuration(final int theDuration) {
        myRegenDuration = theDuration;
    }
}
