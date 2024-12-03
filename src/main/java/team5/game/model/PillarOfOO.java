package team5.game.model;

/**
 * PillarOfOO is an item that can be collected by the player.
 */
public class PillarOfOO extends AbstractConsumables {

    /** The type of the pillar */
    enum PillarType {
        ENCAPSULATION, INHERITANCE, ABSTRACTION, POLYMORPHISM
    }

    /**
     * The type of the pillar
     */
    private PillarType myType;

    /**
     * PillarOfOO constructor
     * 
     * @param count
     * @param type
     */
    public PillarOfOO(int count, PillarType type) {
        super(count);
        myType = PillarType.ENCAPSULATION;
    }
}
