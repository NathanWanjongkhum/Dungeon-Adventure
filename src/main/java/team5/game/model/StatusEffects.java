package team5.game.model;

public class StatusEffects {
    private boolean myVulnerable;
    private boolean myRegen;
    private int myVulDuration;
    private int myRegenDuration;
    private int myRegenAmount;

    public StatusEffects() {
        myVulnerable = false;
        myRegen = false;
        myVulDuration = 0;
        myRegenDuration = 0;
        myRegenAmount = 0;
    }
    public boolean isVulnerable() {
        if (myVulDuration > 0) {
            myVulnerable = true;
        } else {
            myVulnerable = false;
        }
        return myVulnerable;
    }
    public boolean isRegen() {
        if (myRegenDuration > 0) {
            myRegen = true;
        } else {
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
    public int getRegenAmount() {
        return myRegenAmount;
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
    public void setRegenAmount(final int theAmount) {
        myRegenAmount = theAmount;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Active Status Effects:\n");
        if(isVulnerable()) {
            String vul = String.format("Vulnerable for %d turns\n", getVulurableDuration());
            builder.append(vul);
        }
        if (isRegen()) {
            String regen = String.format("Regen for %d HP for %d turns\n", getRegenAmount(), getRegenDuration());
            builder.append(regen);
        }
        return builder.toString();

    }
}
