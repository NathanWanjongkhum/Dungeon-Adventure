package team5.game.model;

public abstract class AbstractDungeonCharacter implements DungeonCharacter {
    private String myName;
    private int myHealth;
    private int myMinDamage;
    private int myMaxDamage;
    private int mySpeed;

    protected AbstractDungeonCharacter(final String theName, final int theHealth, final int theDamage,
            final int theSpeed) {

    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public int getHealth() {
        return myHealth;
    }

    @Override
    public int getSpeed() {
        return mySpeed;
    }

    @Override
    public void setHealth(int theHealth) {
        myHealth = theHealth;
    }

    @Override
    public void setDamage(int theDamage) {
        myMinDamage = theDamage;
    }

    @Override
    public void setSpeed(int theSpeed) {
        mySpeed = theSpeed;
    }

}