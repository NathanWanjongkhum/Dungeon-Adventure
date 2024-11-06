package team5.game.model;

/**
 * Room is a class that represents a room in the dungeon.
 */
public class Room {
    /** If there is a door on some face of the room */
    private boolean[] myDoors = new boolean[4];
    /** The item in the room */
    private Item myItem;
    /** The monster in the room */
    private Monster myMonster;

    /**
     * Room constructor
     * 
     * @param type
     */
    public Room() {

    }

    /**
     * Add an item to the room
     * 
     * @param theDirection the direction of the item
     */
    public void addDoor(Direction theDirection) {
        myDoors[theDirection.ordinal()] = true;
    }

    /**
     * Set the item in the room
     * 
     * @param item the item
     */
    public void setItem(Item item) {
        myItem = item;
    }

    /**
     * Get the item in the room
     * 
     * @return the item
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * Set the monster in the room
     * 
     * @param monster the monster
     */
    public void setMonster(Monster monster) {
        myMonster = monster;
    }

    /**
     * Get the monster in the room
     * 
     * @return the monster
     */
    public Monster getMonster() {
        return myMonster;
    }

    /**
     * Get the doors in the room
     * 
     * @return the doors
     */
    public boolean[] getDoors() {
        return myDoors;
    }

    /**
     * The symbol of the room
     * 
     * @return the symbol
     */
    @Override
    public String toString() {
        return "R";
    }
}
