package team5.game.model;

import java.io.Serializable;

/**
 * PillarOfOO is an item that can be collected by the player.
 */
public class PillarOfOO implements Item, Serializable {
    /** The type of the pillar */
    public enum PillarType {
        ENCAPSULATION, INHERITANCE, ABSTRACTION, POLYMORPHISM
    }

    /**
     * The type of the pillar
     */
    private int myCount;
    /** The type of the pillar */
    private final PillarType myPillar;
    /** The serial version UID for serialization */
    private static final long serialVersionUID = 1L;

    /**
     * The default constructor for pillars
     */
    protected PillarOfOO(final PillarType thePillarType) {
        myCount = 1;
        myPillar = thePillarType;
    }

    /**
     * Copy constructor for pillars
     * 
     * @param theOther the other pillar
     */
    private PillarOfOO(final PillarOfOO theOther) {
        this.myCount = theOther.getCount();
        this.myPillar = theOther.getPillar();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /** {@inheritDoc} */
    @Override
    public int getCount() {
        return myCount;
    }

    /** {@inheritDoc} */
    @Override
    public void setCount(int count) {
        myCount = count;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isConsumable() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPillar() {
        return true;
    }

    /** {@inheritDoc} */
    public PillarType getPillar() {
        return myPillar;
    }

    /** {@inheritDoc} */
    @Override
    public PillarOfOO copy() {
        return new PillarOfOO(this);
    }
}
