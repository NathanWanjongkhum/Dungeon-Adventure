package team5.game.model;

import java.io.Serializable;

/**
 * Exit is an item that is used to exit the dungeon. The reason it is an item is
 * because we can easily check its collision with the hero without any extra
 * logic.
 */
public class Exit implements Item, Serializable {
    /** The serial version UID for serialization */
    private static final long serialVersionUID = 1L;
    /** The count of exits */
    private int myCount;

    /**
     * Exit constructor
     */
    protected Exit() {
        myCount = 1;
    }

    /**
     * Copy constructor for exit
     * 
     * @param theOther the other exit
     */
    protected Exit(final Exit theOther) {
        this.myCount = theOther.getCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getCount() {
        return myCount;
    }

    /** {@inheritDoc} */
    @Override
    public void setCount(final int theCount) {
        myCount = theCount;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isConsumable() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPillar() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "E";
    }

    /**
     * {@inheritDoc}. Exits are not copyable
     */
    @Override
    public Exit copy() {
        return new Exit(this);
    }
}
