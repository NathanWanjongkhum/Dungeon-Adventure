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
    protected AbstractConsumables(int theCount) { // Not sure if we want a consumable constructor with a count as we
                                                  // update counts during the game
        myCount = theCount;
    }
    //Shallow copy of consumables
    protected AbstractConsumables(AbstractConsumables theOther) {
        this.myCount = theOther.getCount();
    }

    @Override
    public int getCount() {
        return myCount;
    }
    @Override
    public void setCount(int theCount) {
        myCount = theCount;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    @Override
    public boolean isConsumable() {
        return true;
    }
    @Override
    public boolean isPillar() {
        return false;
    }
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
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
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
