package team5.game.model;

/**
 * Room is a class that represents a room in the dungeon.
 */
public class Room {
    /** The number of directions in a room */
    private static final int NUM_DIRECTIONS = Direction.values().length;
    /** If there is a door on some face of the room */
    private boolean[] myDoors;
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
        myDoors = new boolean[NUM_DIRECTIONS];
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
     * The string representation of the room. The format is "R[D|W]*" where D
     * represents a door and W represents a wall.
     * 
     * @return the symbol
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("R[");

        for (int i = 0; i < NUM_DIRECTIONS; i++) {
            if (myDoors[i]) {
                // Door or wall
                sb.append(myDoors[i] ? "D" : "W");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public void removeDoor(final Direction thDirection) {
        myDoors[thDirection.ordinal()] = false;
    }
}
