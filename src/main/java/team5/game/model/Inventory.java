package team5.game.model;

import java.io.Serializable;

/**
 * Inventory is a class that holds and handles items.
 */
public class Inventory implements Serializable {
    /** The maximum size of the inventory */
    private int myInventorySize;
    /** The items in the inventory */
    private Item[] myItems;

    public Inventory() {
    }

    private static final long serialVersionUID = 1L;

    /**
     * Create an empty inventory
     * 
     * @param theSize the size of the inventory
     */
    public Inventory(final int theSize) {
        myInventorySize = theSize;
        myItems = new Item[theSize];
    }

    /**
     * Get the size of the inventory
     * 
     * @param theSize the size of the inventory
     */
    public int getSize() {
        return myInventorySize;
    }

    /**
     * Add the item to the first open slot in the inventory
     * 
     * @param item the item to add
     * @return true if the item was added
     */
    public boolean addItem(final Item theItem) {
        if (isFull()) {
            return false;
        }

        for (int i = 0; i < myInventorySize; i++) {
            if (myItems[i] == null) {
                myItems[i] = theItem;
                return true;
            }

            if (myItems[i].getName().equals(theItem.getName()) && myItems[i].isConsumable()) {
                Consumable consumableItem = (Consumable) myItems[i];
                int totalCount = consumableItem.getCount() + theItem.getCount();
                consumableItem.setCount(totalCount);

                return true;
            }
        }

        return false;
    }

    /**
     * Remove the item at the specified slot
     * 
     * @param theSlot the position in the inventory
     */
    public void removeItem(final int theSlot) {
        if (inBounds(theSlot)) {
            myItems[theSlot] = null;
        }
    }

    /**
     * Get the items in the inventory
     * 
     * @return the items in the inventory
     */
    public Item[] getItems() {
        Item[] filledItems = new Item[myInventorySize];
        System.arraycopy(myItems, 0, filledItems, 0, myInventorySize);
        return filledItems;
    }

    /**
     * Get the item at the specified slot
     * 
     * @param theSlot the position in the inventory
     * @return the item at the slot
     */
    public Item getItem(final int theSlot) {
        if (inBounds(theSlot)) {
            return myItems[theSlot];
        } else {
            return null;
        }
    }

    public int getItem(final Item theItem) {
        int index = 0;
        while (!myItems[index].getName().equals(theItem.getName())) {
            index++;
        }
        return index;
    }

    /**
     * Check if the inventory is full
     * 
     * @return true if the inventory is full
     */
    public boolean isFull() {
        for (final Item item : getItems()) {
            if (item == null) {
                return false;
            }
        }

        return true;
    }

    public boolean isEmpty() {
        return getItem(0) == null;
    }

    /**
     * Check if the index is in the inventory
     * 
     * @param index the index
     * @return true if the index is in the inventory
     */
    private boolean inBounds(final int index) {
        return index >= 0 && index < myInventorySize;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Inventory: ");
        for (Item item : getItems()) {
            String itemString = item == null ? "null" : item.getName();
            builder.append(itemString);
            builder.append(", ");
        }
        return builder.toString();
    }
}
