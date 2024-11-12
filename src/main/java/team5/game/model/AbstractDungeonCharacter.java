package team5.game.model;

public abstract class AbstractDungeonCharacter implements DungeonCharacter {
    /** Constant for Damage difference */
    private static final int DAMAGE_DIF = 5;
    /** The name of the character. */
    private String myName;
    /** The health of the character. */
    private int myHealth;
    /** The minimum damage of the character. */
    private int myMinDamage;
    /** The maximum damage of the character. */
    private int myMaxDamage;
    /** The speed of the character. */
    private int mySpeed;

    /** Creates a new AbstractDungeonCharacter. */
    protected AbstractDungeonCharacter(final String theName, final int theHealth, final int theDamage,
            final int theSpeed) {
        myName = theName;
        myHealth = theHealth;
        myMinDamage = theDamage - DAMAGE_DIF;
        myMaxDamage = theDamage + DAMAGE_DIF;
        mySpeed = theSpeed;
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
    public int getMinDamage() {
        return myMinDamage;
    }

    @Override
    public int getMaxDamage() {
        return myMaxDamage;
    }

    @Override
    public int getSpeed() {
        return mySpeed;
    }
    @Override
    public boolean isAlive() {
        return myHealth > 0;
    }
    @Override
    public void setHealth(int theHealth) {
        myHealth = theHealth;
    }

    @Override
    public void setMinDamage(int theDamage) {
        myMinDamage = theDamage;
    }

    @Override
    public void setMaxDamage(int theDamage) {
        myMaxDamage = theDamage;
    }

    @Override
    public void setSpeed(int theSpeed) {
        mySpeed = theSpeed;
    }

}