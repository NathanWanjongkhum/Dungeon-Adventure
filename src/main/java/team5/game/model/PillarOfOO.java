package team5.game.model;

import team5.game.model.PillarOfOO.PillarType;

/**
 * PillarOfOO is an item that can be collected by the player.
 */
public class PillarOfOO implements Item {
    /** The type of the pillar */
    enum PillarType {
        ENCAPSULATION, INHERITANCE, ABSTRACTION, POLYMORPHISM
    }

    private int myCount;
    private boolean myEncapsulation;
    private boolean myInheritance;
    private boolean myAbstraction;
    private boolean myPolymorphism;
    public PillarOfOO() {
        myCount = 0;
        myAbstraction = false;
        myInheritance = false;
        myAbstraction = false;
        myPolymorphism = false;
    }
    public void setCollected(final PillarType theType) {
        if(!myEncapsulation && theType == PillarType.ENCAPSULATION) {
            myCount++;
            myEncapsulation = true;
        } else if (!myInheritance && theType == PillarType.INHERITANCE) {
            myCount++;
            myInheritance = true;
        } else if (!myAbstraction && theType == PillarType.ABSTRACTION) {
            myCount++;
            myAbstraction = true;
        } else if (!myPolymorphism && theType == PillarType.POLYMORPHISM) {
            myCount++;
            myPolymorphism = true;
        }
    }
    public boolean isAllCollected() {
        return myCount == PillarType.values().length;
    }
    public boolean isEncapsulationCollected() {
        return myEncapsulation;
    }
    public boolean isInheritanceCollected() {
        return myInheritance;
    }
    public boolean isAbstractionCollected() {
        return myAbstraction;
    }
    public boolean isPolymorphismCollected() {
        return myPolymorphism;
    }
    @Override
    public boolean isConsumable() {
        return false;
    }
    @Override
    public boolean isPillar() {
        return true;
    }
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
    @Override
    public int getCount() {
        return myCount;
    }
    @Override
    public void setCount(int count) {
        myCount = count;
    }
}
