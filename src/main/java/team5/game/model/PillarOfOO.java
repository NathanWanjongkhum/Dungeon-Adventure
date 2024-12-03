package team5.game.model;

import java.io.Serializable;

import team5.game.model.PillarOfOO.PillarType;

/**
 * PillarOfOO is an item that can be collected by the player.
 */
public class PillarOfOO implements Item , Serializable{

    /** The type of the pillar */
    enum PillarType {
        ENCAPSULATION, INHERITANCE, ABSTRACTION, POLYMORPHISM
    }

    /**
     * The type of the pillar
     */
    private PillarType myType;
    private int myCount;
    private static final long serialVersionUID = 1L;

    /**
     * PillarOfOO constructor
     * 
     * @param count
     * @param type
     */
    public PillarOfOO(PillarType theType) {
        myType = theType;
        myCount = 1;
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
}
