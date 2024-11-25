
package team5.game.model;

/**
 * Item is an interface that all items must implement.
 */
public interface Item {
    public boolean isConsumable();
    public boolean isPillar();
    public String getName();
}
