package team5.game.model;

public interface Consumable extends Item{
    /**
     * Get the count of consumables
     * 
     * @return the count
     */
    public int getCount();
    /**
     * Set the count of consumables
     * 
     * @param count the count
     */
    public void setCount(final int theCount);
    public void useItem(DungeonCharacter theCharacter);
}
