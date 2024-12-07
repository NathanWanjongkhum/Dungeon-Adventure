package team5.game.model;

import java.io.Serializable;

/**
 * Room is a class that represents a room in the dungeon.
 */
public class Room implements Serializable {
    /** The number of directions in a room */
    private static final int NUM_DIRECTIONS = Direction.values().length;
    /** If there is a door on some face of the room */
    private boolean[] myDoors;
    /** The item in the room */
    private Item myItem;
    /**
     * The monster in the room.
     * 
     * If time allows refactor this to be a reference to the monster in a seperate
     * manager class as it is currently stored twice.
     */
    private Monster myMonster;

    private static final long serialVersionUID = 1L;

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
    public void addDoor(final Direction theDirection) {
        myDoors[theDirection.ordinal()] = true;
    }

    /**
     * Set the item in the room
     * 
     * @param item the item
     */
    public void setItem(final Item theItem) {
        myItem = theItem;
    }

    /** Remove the item in the room */
    public void removeItem() {
        myItem = null;
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
     * @param theMonster the monster
     */
    public void setMonster(final Monster theMonster) {
        myMonster = theMonster;
    }
    public void removeMonster() {
        myMonster = null;
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
            sb.append(myDoors[i] ? "D" : "W");
        }

        sb.append("]");
        return sb.toString();
    }

    public void removeDoor(final Direction thDirection) {
        myDoors[thDirection.ordinal()] = false;
    }
}
