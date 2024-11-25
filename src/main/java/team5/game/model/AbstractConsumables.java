package team5.game.model;

/**
 * Consumables is an item that can be collected and consumed by the player.
 */
public abstract class AbstractConsumables implements Consumable {
    /** The count of consumables */
    private int myCount;

    /**
     * Consumables constructor
     */
    protected AbstractConsumables() {
        myCount = 0;
    }
    @Override
    public int getCount() {
        return myCount;
    }
    @Override
    public void setCount(int count) {
        myCount = count;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    @Override
    public boolean isConsumable() {
        return true;
    }
    @Override
    public boolean isPillar() {
        return false;
    }
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
