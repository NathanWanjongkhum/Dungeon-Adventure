package team5.game.model;

/**
 * Hero is an abstract class that represents the player's character. It is
 * extended by the Archer, Ogre, and Skeleton classes. It holds the inventory,
 * the special attack, and the consumable. It also holds the direction the
 * character is facing. The hero is a part of the game state.
 */
public abstract class Hero extends AbstractDungeonCharacter implements Special {
    /** The max number of items in the inventory */
    private static final int ITEM_COUNT = 7;
    /** The direction the hero is facing */
    private Direction myDirection;
    /** The inventory of the hero */
    private final Inventory myInventory;
    /** Special Attack of hero */
    private final SpecialAttack mySpecial;
    /** The consumable item */
    private Consumable myConsumable;
    /** Indicator for consumable usage */
    private boolean myUsed;
    /** The x coordinate of the hero */
    private int myX;
    /** The y coordinate of the hero */
    private int myY;

    /**
     * The Hero constructor
     * 
     * @param theName    the name of the hero
     * @param theHealth  the health of the hero
     * @param theDamage  the damage of the hero
     * @param theSpeed   the speed of the hero
     * @param theSpecial the special attack of the hero
     */
    public Hero(final String theName, final int theHealth, final int theDamage, final int theSpeed,
            final SpecialAttack theSpecial) {
        super(theName, theHealth, theDamage, theSpeed);

        myDirection = Direction.NORTH;
        myInventory = new Inventory(ITEM_COUNT);
        mySpecial = theSpecial;
        myConsumable = null;
        myUsed = false;
        myX = 0;
        myY = 0;
    }

    /**
     * Get the direction the hero is facing
     * 
     * @return the direction
     */
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Set the direction the hero is facing
     * 
     * @param direction the direction
     */
    public void setDirection(final Direction direction) {
        myDirection = direction;
    }

    /**
     * Get the x coordinate of the hero
     * 
     * @return the x coordinate
     */
    public int getX() {
        return myX;
    }

    /**
     * Get the y coordinate of the hero
     * 
     * @return the y coordinate
     */
    public int getY() {
        return myY;
    }

    /**
     * Set the x coordinate of the hero
     * 
     * @param theX the x coordinate
     */
    public void setX(final int theX) {
        myX = theX;
    }

    /**
     * Set the y coordinate of the hero
     * 
     * @param theY the y coordinate
     */
    public void setY(final int theY) {
        myY = theY;
    }

    /**
     * Get the coordinates of the location relative to the hero in some direction
     * 
     * @param theDirection the direction
     * @return the coordinates
     */
    public int[] getDirectionsLocation(final Direction theDirection) {
        final int dx = theDirection.calculateNewX(getX());
        final int dy = theDirection.calculateNewY(getY());
        return new int[] { dx, dy };
    }

    /**
     * Set the x and y location of the hero
     * 
     * @param theLocation the new location
     */
    public void moveTo(final Direction theDirection) {
        setDirection(myDirection);

        final int dx = theDirection.calculateNewX(getX());
        final int dy = theDirection.calculateNewY(getY());

        setX(dx);
        setY(dy);
    }

    /**
     * Get the inventory of the hero
     * 
     * @return the inventory
     */
    public Inventory getInventory() {
        return myInventory;
    }

    @Override
    public SpecialAttack getSpecialAttack() {
        return mySpecial;
    }

    /** {@inheritDoc} */
    @Override
    public void resetTurns() {
        mySpecial.setTurns(0);
    }

    /**
     * Use the consumable on the hero
     * 
     * @param theConsumable the consumable being used
     */
    public void setConsumable(Consumable theConsumable) {
        myConsumable = theConsumable;
        myUsed = true;
    }

    /**
     * Use the consumable on the hero
     * 
     * @return the consumable being used
     */
    public Consumable useConsumable() {
        myUsed = false;
        return myConsumable;
    }

    /**
     * Get if the consumable is used
     * 
     * @return true if consumable is used, false otherwise
     */

    public boolean isConUsed() {
        return myUsed;
    }

    /** {@inheritDoc} */
    @Override
    public void addMinDamage(final int theAddedDamage) {
        super.addMinDamage(theAddedDamage);
        mySpecial.addDamage(theAddedDamage);
    }

}