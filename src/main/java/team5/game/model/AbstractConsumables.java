package team5.game.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Consumables is an item that can be collected and consumed by the player.
 */
public abstract class AbstractConsumables implements Consumable, Serializable {
    /** The count of consumables */
    private int myCount;

    private static final long serialVersionUID = 1L;

    /**
     * Consumables constructor
     */
    protected AbstractConsumables() {
        myCount = 1;
    }

    /**
     * Consumables constructor with count
     * 
     * @param count
     */
    protected AbstractConsumables(int theCount) {
        myCount = theCount;
    }

    /**
     * Shallow copy of consumables
     * 
     * @param theOther
     */
    protected AbstractConsumables(AbstractConsumables theOther) {
        this.myCount = theOther.getCount();
    }

    /**
     * Gets the count of consumables
     * 
     * @return the count
     */
    @Override
    public int getCount() {
        return myCount;
    }

    /**
     * Sets the count of consumables
     * 
     * @param theCount
     */
    @Override
    public void setCount(int theCount) {
        myCount = theCount;
    }

    /**
     * The string representation of consumables is the name of the class of the
     * consumable
     * 
     * @return the string representation
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    /**
     * Ensures Consumables are consumables
     * 
     * @return true
     */
    @Override
    public boolean isConsumable() {
        return true;
    }

    /**
     * Ensures consumables are not pillars
     * 
     * @return false
     */
    @Override
    public boolean isPillar() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getClass().getSimpleName();
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
        if (!(theOther instanceof Consumable)) {
            return false;
        }
        final Consumable consumable = (Consumable) theOther;
        return Objects.equals(this.getName(), consumable.getName());
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
