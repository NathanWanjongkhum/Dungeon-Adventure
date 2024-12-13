package team5.game.model;

public class Bomb extends AbstractConsumables {
    /** The damage of the bomb */
    private static final int BOMB_DAMAGE = 1000;
    /** The radius of the bomb */
    private static final int BOMB_RADIUS = 1;

    /** The damage of the bomb */
    private int myDamage;
    /** The radius of the bomb */
    private int myRadius;

    /**
     * The default constructor for bomb
     */
    public Bomb() {
        super();
        myDamage = BOMB_DAMAGE;
        myRadius = BOMB_RADIUS;
    }

    /**
     * Copy constructor for bomb
     * 
     * @param theOther the other bomb
     */
    private Bomb(final Bomb theOther) {
        super(theOther);
        myDamage = theOther.getDamage();
        myRadius = theOther.getRadius();
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

    /**
     * {@inheritDoc}. Bomb deals damage to the enemy. Including vulnerable
     * multiplier.
     */
    @Override
    public int useItem(DungeonCharacter theCharacter) {
        final int damage = theCharacter.checkVulnerableDamage(theCharacter, myDamage);
        theCharacter.setHealth(theCharacter.getHealth() - damage);
        return damage;
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return String.format("When uses, deals %s damage to the enemy.", myDamage);
    }

    /** {@inheritDoc} */
    @Override
    public Bomb copy() {
        return new Bomb(this);
    }
}