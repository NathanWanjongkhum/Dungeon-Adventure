package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class InventoryTest {
    /** Test for Inventory Constructor */
    @Test
    public void testInventoryConstructor() {
        final Inventory inventory = new Inventory();

        final int expectedLength = 2;
        final int actualLength = inventory.getItems().length;

        assertTrue(actualLength == expectedLength);
    }

    /** Test for Inventory addItem method */
    @Test
    public void testInventoryAddItem() {
        final Inventory i = new Inventory();
        final Bomb expectedItem = new Bomb();
        i.addItem(expectedItem);
        final Item actualItem = i.getItem(0);

        assertTrue(actualItem == expectedItem);
    }

    /** Test for Inventory removeItem method */
    @Test
    public void testInventoryRemoveItem() {
        final Inventory i = new Inventory();
        final Bomb item = new Bomb();
        i.addItem(item);
        i.removeItem(0);

        final Item expectedItem = null;
        final Item actualItem = i.getItem(0);

        assertTrue(actualItem == expectedItem);
    }

    /** Test for Inventory getItems method */
    @Test
    public void testInventoryGetItems() {
        final Inventory inventory = new Inventory();
        final Item[] expectedItems = new Item[2];

        for (int i = 0; i < inventory.getItems().length; i++) {
            final Bomb item = new Bomb();
            inventory.addItem(item);
            expectedItems[i] = item;
        }

        final Item[] actualItems = inventory.getItems();

        assertTrue(Arrays.equals(expectedItems, actualItems));

    }

    /** Test for Inventory getItem method */
    @Test
    public void testInventoryGetItem() {
        final Inventory i = new Inventory();
        final Bomb expectedItem = new Bomb();
        i.addItem(expectedItem);
        final Item returnedItem = i.getItem(0);

        assertTrue(expectedItem == returnedItem);
    }
}
