package team5.game.model;

public class Bomb extends AbstractConsumables {
    private static final int BOMB_DAMAGE = 10;
    private static final int BOMB_RADIUS = 1;
    /** The damage of the bomb */
    private int myDamage;
    /** The radius of the bomb */
    private int myRadius;


    public Bomb() {
        super();
        myDamage = BOMB_DAMAGE;
        myRadius = BOMB_RADIUS;
    }
    /**
     * Bomb constructor
     * 
     * @param count
     */
    public Bomb(int count) { //Don't know if need a constructor with count as we create bomb first and should default to 0 items
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
