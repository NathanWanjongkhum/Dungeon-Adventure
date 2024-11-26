package team5.game.model;

import java.util.Random;

import javafx.scene.image.Image;

public abstract class AbstractDungeonCharacter implements DungeonCharacter {
    /** Constant for Damage difference */
    private static final int DAMAGE_DIF = 5;
    /** The name of the character. */
    private final String myName;
    /** The health of the character. */
    private int myHealth;
    /** The max health of the character */
    private final int myMaxHealth;
    /** The minimum damage of the character. */
    private int myMinDamage;
    /** The maximum damage of the character. */
    private int myMaxDamage;
    /** The speed of the character. */
    private int mySpeed;
    /** The status effect on character */
    private final StatusEffects myEffects;

    /** Creates a new AbstractDungeonCharacter. */
    protected AbstractDungeonCharacter(final String theName, final int theHealth, final int theDamage,
            final int theSpeed) {
        myName = theName;
        myHealth = theHealth;
        myMaxHealth = theHealth;
        myMinDamage = theDamage - DAMAGE_DIF;
        myMaxDamage = theDamage + DAMAGE_DIF;
        mySpeed = theSpeed;
        myEffects = new StatusEffects();
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
    public int getMaxHealth() {
        return myMaxHealth;
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
    public Image getImage() {
        final StringBuilder builder = new StringBuilder();
        builder.append("/team5/game/");
        builder.append(getClass().getSimpleName().toLowerCase());
        builder.append(".png");
        Image temp = new Image(getClass().getResource(builder.toString()).toString());
        return temp;
    }
    @Override
    public boolean isAlive() {
        return myHealth > 0;
    }
    @Override
    public StatusEffects getStatusEffects() {
        return myEffects;
    }
    @Override
    public void setHealth(final int theHealth) {
        myHealth = theHealth;
    }

    @Override
    public void setMinDamage(final int theDamage) {
        if (theDamage > 0) {
            myMinDamage = theDamage;
        } else {
            myMinDamage = 1;
        }
        
    }

    @Override
    public void setMaxDamage(final int theDamage) {
        myMaxDamage = theDamage;
    }

    @Override
    public void setSpeed(final int theSpeed) {
        mySpeed = theSpeed;
    }
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(myName);
        String hp = String.format("\nHP: %d/%d", myHealth, myMaxHealth);
        builder.append(hp);
        String damage = String.format("\nDamage Range: %d - %d\n", myMinDamage, myMaxDamage);
        builder.append(damage);
        builder.append("Speed: ");
        builder.append(mySpeed);
        builder.append("\n");
        return builder.toString();
    }
    @Override
    public int attack(final DungeonCharacter theOther) {
        Random rand = new Random();
        int damage = rand.nextInt(myMaxDamage - myMinDamage + 1) + myMinDamage;
        damage = checkVulnerableDamage(theOther, damage);
        theOther.setHealth(theOther.getHealth() - damage);
        return damage;
    }
    @Override
    public void heal(final int theHeal) {
        if (getHealth() + theHeal >= getMaxHealth()) {
            setHealth(getMaxHealth());
        } else {
            setHealth(getHealth() + theHeal);
        }
    }
    @Override
    public int checkVulnerableDamage(final DungeonCharacter theOther, final int theDamage) {
        int damage = theDamage;
        if(theOther.getStatusEffects().isVulnerable()) {
            damage *= 2;
        }
        return damage;
    }

}