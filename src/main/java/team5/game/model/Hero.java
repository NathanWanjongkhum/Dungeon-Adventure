package team5.game.model;

public abstract class Hero extends AbstractDungeonCharacter implements Special{
    private static final int ITEM_COUNT = 3;
    /** The direction the hero is facing */
    private Direction myDirection;
    /** The inventory of the hero */
    private Inventory myInventory;
    /**Special Attack of hero */
    private SpecialAttack mySpecial;
    /**
     * The Hero constructor
     * 
     * @param theName the name of the hero
     * @param theHealth the health of the hero 
     * @param theDamage the damage of the hero
     * @param theSpeed the speed of the hero
     * @param theSpecial the special attack of the hero
     */
    protected Hero(final String theName, final int theHealth, final int theDamage, final int theSpeed, final SpecialAttack theSpecial) {
        super(theName, theHealth, theDamage, theSpeed);
        myDirection = Direction.NORTH;
        myInventory = new Inventory(ITEM_COUNT);
        mySpecial = theSpecial;
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
     * Get the inventory of the hero
     * 
     * @return the inventory
     */
    public Inventory getInventory() {
        return myInventory.clone();
    }
    @Override
    public SpecialAttack getSpecialAttack() {
        return mySpecial;
    }

    /**
     * Set the direction the hero is facing
     * 
     * @param direction the direction
     */
    public void setDirection(final Direction direction) {
        myDirection = direction;
    }
    @Override
    public void resetTurns() {
        mySpecial.setTurns(0);
    }

}