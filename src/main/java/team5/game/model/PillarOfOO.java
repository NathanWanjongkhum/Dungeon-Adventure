package team5.game.model;

public class PillarOfOO extends Consumables {
    enum PillarType {
        ENCAPSULATION, INHERITANCE, ABSTRACTION, POLYMORPHISM
    }

    private PillarType myType;

    public PillarOfOO(int count, PillarType type) {
        super(count);
        myType = PillarType.ENCAPSULATION;
    }
}
