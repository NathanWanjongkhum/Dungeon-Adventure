package team5.game.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

import javafx.scene.image.Image;

/**
 * Abstract class for dungeon characters. Handles common methods for all dungeon
 * characters.
 */
public abstract class AbstractDungeonCharacter implements DungeonCharacter, Serializable {
    /** The serial version UID for serialization */
    private static final long serialVersionUID = 1L;

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
        if (theDamage - DAMAGE_DIF < 0) {
            myMinDamage = 0;
        } else {
            myMinDamage = theDamage - DAMAGE_DIF;
        }
        myMaxDamage = theDamage + DAMAGE_DIF;
        if (theSpeed < 0) {
            mySpeed = 0;
        } else {
            mySpeed = theSpeed;
        }
        myEffects = new StatusEffects();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return myName;
    }

    /** {@inheritDoc} */
    @Override
    public int getHealth() {
        return myHealth;
    }

    /** {@inheritDoc} */
    @Override
    public int getMaxHealth() {
        return myMaxHealth;
    }

    /** {@inheritDoc} */
    @Override
    public int getMinDamage() {
        return myMinDamage;
    }

    /** {@inheritDoc} */
    @Override
    public int getMaxDamage() {
        return myMaxDamage;
    }

    /** {@inheritDoc} */
    @Override
    public int getSpeed() {
        return mySpeed;
    }

    /** {@inheritDoc} */
    @Override
    public Image getImage() {
        final StringBuilder builder = new StringBuilder();
        builder.append("/team5/game/");
        builder.append(getClass().getSimpleName().toLowerCase());
        builder.append(".png");
        Image temp = new Image(getClass().getResource(builder.toString()).toString());
        return temp;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAlive() {
        return myHealth > 0;
    }

    /** {@inheritDoc} */
    @Override
    public StatusEffects getStatusEffects() {
        return myEffects;
    }

    /** {@inheritDoc} Can only set health to a positive number */
    @Override
    public void setHealth(final int theHealth) {
        if (theHealth < 0) {
            myHealth = 0;
        } else {
            myHealth = theHealth;
        }
    }

    /** {@inheritDoc} Can only set min damage to a positive number */
    @Override
    public void setMinDamage(final int theDamage) {
        if (theDamage < 0) {
            myMinDamage = 0;
        } else {
            myMinDamage = theDamage;
        }
    }

    /** {@inheritDoc} Can only set max damage to a positive number */
    @Override
    public void setMaxDamage(final int theDamage) {
        myMaxDamage = theDamage;
    }

    /** {@inheritDoc} Can only add min damage to a positive number */
    @Override
    public void addMinDamage(final int theAddedDamage) {
        if (myMinDamage + theAddedDamage < 0) {
            myMinDamage = 0;
        } else {
            myMinDamage += theAddedDamage;
        }
    }

    /** {@inheritDoc} Can only add max damage to a positive number */
    @Override
    public void addMaxDamage(final int theAddedDamage) {
        myMaxDamage += theAddedDamage;
    }

    /** {@inheritDoc} Can only set speed to a positive number */
    @Override
    public void setSpeed(final int theSpeed) {
        if (theSpeed < 0) {
            mySpeed = 0;
        } else {
            mySpeed = theSpeed;
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getStats() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(myName);
        builder.append("\nClass: ");
        builder.append(getClass().getSimpleName());
        String hp = String.format("\nHP: %d/%d", myHealth, myMaxHealth);
        builder.append(hp);
        String damage = String.format("\nDamage Range: %d - %d\n", myMinDamage, myMaxDamage);
        builder.append(damage);
        builder.append("Speed: ");
        builder.append(mySpeed);
        return builder.toString();
    }

    /**
     * {@inheritDoc}. Checks for vulnerable damage and subtracts it from the other
     * character.
     */
    @Override
    public int attack(final DungeonCharacter theOther) {
        Random rand = new Random();
        int damage = rand.nextInt(myMaxDamage - myMinDamage + 1) + myMinDamage;
        damage = checkVulnerableDamage(theOther, damage);
        theOther.setHealth(theOther.getHealth() - damage);
        return damage;
    }

    /** {@inheritDoc}. Heals can at most heal to max health */
    @Override
    public void heal(final int theHeal) {
        if (getHealth() + theHeal >= getMaxHealth()) {
            setHealth(getMaxHealth());
        } else {
            setHealth(getHealth() + theHeal);
        }
    }

    /** {@inheritDoc}. Damage is multiplied by 2 if vulnerable */
    @Override
    public int checkVulnerableDamage(final DungeonCharacter theOther, final int theDamage) {
        int damage = theDamage;
        if (theOther.getStatusEffects().isVulnerable()) {
            damage *= 2;
        }
        return damage;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }
        if (theOther == null) {
            return false;
        }
        if (!(theOther instanceof DungeonCharacter)) {
            return false;
        }
        final DungeonCharacter character = (DungeonCharacter) theOther;
        return Objects.equals(myName, character.getName()) &&
                Objects.equals(myHealth, character.getHealth()) &&
                Objects.equals(myMinDamage, character.getMinDamage()) &&
                Objects.equals(myMaxDamage, character.getMaxDamage()) &&
                Objects.equals(mySpeed, character.getSpeed());
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myHealth, myMinDamage, myMaxDamage, mySpeed);
    }

}