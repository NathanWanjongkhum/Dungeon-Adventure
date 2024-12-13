package team5.game.model;

/**
 * The HealingPotion class. Used by player in battles and picked up in the
 * dungeon.
 */
public class HealingPotion extends AbstractConsumables {
    /** The amount of healing potion */
    private static final int HEALTH_RESTORE = 20;
    /** The number of turns the potion lasts */
    private int myHealthRestore;

    /**
     * Default constructor for healing potion
     */
    public HealingPotion() {
        super();
        myHealthRestore = HEALTH_RESTORE;
    }

    /**
     * Copy constructor for healing potion
     * 
     * @param theOther the other healing potion
     */
    public HealingPotion(final HealingPotion theOther) {
        super(theOther);
        this.myHealthRestore = theOther.getHealthRestore();
    }

    /**
     * Gets the amount of health a potion retores
     * 
     * @return the amount fo health restored
     */
    public int getHealthRestore() {
        return myHealthRestore;
    }

    /**
     * Sets the amount of health a potion heals
     * 
     * @param theHealthRestore the health restored
     */
    public void setHealthRestore(int theHealthRestore) {
        myHealthRestore = theHealthRestore;
    }

    /**
     * {@inheritDoc}. Healing potions heals the character.
     * 
     * @param theCharacter the character to heal
     */
    @Override
    public int useItem(DungeonCharacter theCharacter) {
        theCharacter.heal(getHealthRestore());
        return getHealthRestore();
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return String.format("Heals %s hp when used", myHealthRestore);
    }

    /** {@inheritDoc} */
    @Override
    public HealingPotion copy() {
        return new HealingPotion(this);
    }
}
