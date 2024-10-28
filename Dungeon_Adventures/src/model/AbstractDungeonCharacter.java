package model;

public abstract class AbstractDungeonCharacter implements DungeonCharacter{
    private String myName;
    private int myHealth;
    private int myMinDamage;
    private int myMaxDamage;
    private int mySpeed;

    protected AbstractDungeonCharacter(final String theName, final int theHealth, final int theDamage, final int theSpeed) {
        
    }
}