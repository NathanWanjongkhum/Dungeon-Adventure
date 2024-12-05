package team5.game.model;

public class Bomb extends AbstractConsumables {
    private static final int BOMB_DAMAGE = 1000;
    private static final int BOMB_RADIUS = 1;
    /** The damage of the bomb */
    private int myDamage;
    /** The radius of the bomb */
    private int myRadius;

    /**
     * The bomb constructor
     */
    public Bomb() {
        super();
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
    @Override
    public int useItem(DungeonCharacter theCharacter) {
        final int damage = theCharacter.checkVulnerableDamage(theCharacter, this.getDamage());
        theCharacter.setHealth(theCharacter.getHealth() - damage);
        return damage;
    }
    @Override
    public String getDescription() {
        return String.format("When uses, deals %s damage to the enemy", myDamage);
    }
}