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
    private final PillarType myPillar;
    private static final long serialVersionUID = 1L;

    /**
     * PillarOfOO constructor
     */
    public PillarOfOO(final PillarType thePillarType) {
        myCount = 1;
        myPillar = thePillarType;
    }
    private PillarOfOO(final PillarOfOO theOther) {
        this.myCount = theOther.getCount();
        this.myPillar = theOther.getPillar();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getCount() {
        return myCount;
    }

    @Override
    public void setCount(int count) {
        myCount = count;
    }

    @Override
    public boolean isConsumable() {
        return false;
    }
    @Override
    public boolean isPillar() {
        return true;
    }
    public PillarType getPillar() {
        return myPillar;
    }
    @Override
    public PillarOfOO copy() {
        return new PillarOfOO(this);
    }
}
