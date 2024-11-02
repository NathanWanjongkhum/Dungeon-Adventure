package team5.game.model;

public class Bomb extends AbstractConsumables {
    private static final int BOMB_DAMAGE = 10;
    private static final int BOMB_RADIUS = 1;
    /** The damage of the bomb */
    private int myDamage;
    /** The radius of the bomb */
    private int myRadius;

    /**
     * Bomb constructor
     * 
     * @param count
     */
    public Bomb(int count) {
        super(count);
        myDamage = BOMB_DAMAGE;
        myRadius = BOMB_RADIUS;
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
    /**
     * Set the damage of the bomb
     * 
     * @param theDamage the damage
     */
    public void setDamage(int theDamage) {
        myDamage = theDamage;
    }
    /**
     * Set the radius of the bomb
     * 
     * @param theRadius the radius
     */
    public void setRadius(int theRadius) {
        myRadius = theRadius;
    }
}
