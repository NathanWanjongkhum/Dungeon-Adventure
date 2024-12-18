package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * Tests for inventory
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class InventoryTest {
    /** The size of the inventory */
    private static final int TEST_SIZE = 2;
    /** The inventory */
    private static Inventory myInventory;

    /** Set up the inventory */
    @BeforeAll
    public static void setUp() {
        myInventory = new Inventory(TEST_SIZE);
    }

    /** Test for Inventory Constructor */
    @Test
    public void testInventoryConstructor() {
        myInventory = new Inventory(TEST_SIZE);

        final int expectedLength = TEST_SIZE;
        final int actualLength = myInventory.getItems().length;

        assertEquals(expectedLength, actualLength);
    }

    /** Test for Inventory getSize method */
    @Test
    public void testInventoryGetSize() {
        myInventory = new Inventory(TEST_SIZE);

        final int expectedSize = TEST_SIZE;
        final int actualSize = myInventory.getSize();

        assertEquals(expectedSize, actualSize);
    }

    /** Test for Inventory addItem method */
    @Test
    public void testInventoryAddItem() {
        myInventory = new Inventory(TEST_SIZE);

        final Bomb expectedItem = new Bomb();
        boolean actualIsAdded = myInventory.addItem(expectedItem);
        final Bomb expectedItem2 = new Bomb();
        actualIsAdded = myInventory.addItem(expectedItem2);

        final Item actualItems = myInventory.getItem(0);
        assertTrue(myInventory.getItem(0).getCount() == 2);
        assertTrue(actualIsAdded);
        assertEquals(expectedItem, actualItems);
    }

    /**
     * Test for Inventory addItem method. Confirms does not add if inventory is full
     */
    @Test
    public void testInventoryAddItemFull() {
        myInventory = new Inventory(0);

        final Bomb expectedItem = new Bomb();
        final boolean actualIsAdded = myInventory.addItem(expectedItem);

        assertFalse(actualIsAdded);
    }

    /** Test for Inventory removeItem method */
    @Test
    public void testInventoryRemoveItem() {
        myInventory = new Inventory(TEST_SIZE);

        final Bomb item = new Bomb();
        myInventory.addItem(item);
        myInventory.removeItem(0);

        final Item expectedItem = null;
        final Item actualItem = myInventory.getItem(0);

        assertEquals(expectedItem, actualItem);
    }

    /** Test for Inventory getItems method */
    @Test
    public void testInventoryGetItems() {
        myInventory = new Inventory(TEST_SIZE);

        Item[] expectedItems = new Item[TEST_SIZE];

        final Bomb item1 = new Bomb();
        myInventory.addItem(item1);
        expectedItems[0] = item1;

        final HealingPotion item2 = new HealingPotion();
        myInventory.addItem(item2);
        expectedItems[1] = item2;

        final Item[] actualItems = myInventory.getItems();

        assertArrayEquals(expectedItems, actualItems);
    }

    /** Test for Inventory getItem method. Confirms item exists */
    @Test
    public void testInventoryGetItemExists() {
        myInventory = new Inventory(TEST_SIZE);

        final Bomb expectedItem = new Bomb();
        myInventory.addItem(expectedItem);

        final Item actualItem = myInventory.getItem(0);

        assertEquals(expectedItem, actualItem);
    }

    /** Test for Inventory getItem method. Confirms item does not exist */
    @Test
    public void testInventoryGetItemDoesNotExist() {
        myInventory = new Inventory(TEST_SIZE);

        final Item expectedItem = null;
        final Item actualItem = myInventory.getItem(0);

        assertEquals(expectedItem, actualItem);
    }

    /** Test for Inventory isFull method */
    @Test
    public void testInventoryIsFull() {
        myInventory = new Inventory(TEST_SIZE);

        final boolean expectedIsFull = false;
        final boolean actualIsFull = myInventory.isFull();

        assertEquals(expectedIsFull, actualIsFull);
    }

    /** Test for Inventory isFull method. Confirms inventory is full */
    @Test
    public void testInventoryIsFullInventory() {
        myInventory = new Inventory(TEST_SIZE);

        final Bomb item1 = new Bomb();
        myInventory.addItem(item1);

        final HealingPotion item2 = new HealingPotion();
        myInventory.addItem(item2);

        final boolean expectedIsFull = true;
        final boolean actualIsFull = myInventory.isFull();

        assertEquals(expectedIsFull, actualIsFull);
    }
}
