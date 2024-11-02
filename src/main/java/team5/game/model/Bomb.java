package team5.game.model;

public class Bomb extends AbstractConsumables {
    /** The damage of the bomb */
    private int myDamage = 10;
    /** The radius of the bomb */
    private int myRadius = 1;

    /**
     * Bomb constructor
     * 
     * @param count
     */
    public Bomb(int count) {
        super(count);
    }

    /**
     * Get the damage of the bomb
     * 
     * @return the damage
     */
    public int getDamage() {
        return myDamage;
    }

    /**
     * Get the radius of the bomb
     * 
     * @return the radius
     */
    public int getRadius() {
        return myRadius;
    }
}
