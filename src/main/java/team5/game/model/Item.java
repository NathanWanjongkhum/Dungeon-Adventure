
package team5.game.model;

/**
 * Item is an interface that all items must implement.
 */
public interface Item {
    /** The type of item */
    enum ItemType {
        HEALTH_POTION, VISION_POTION;
    }

    /** The name of the item */
    public String myName = "";
}
