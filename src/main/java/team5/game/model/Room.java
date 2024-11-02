package team5.game.model;

/**
 * Room is a class that represents a room in the dungeon.
 */
public class Room {
    /** The type of room */
    enum RoomType {
        EMPTY, MONSTER, START, EXIT, PILLAR_A, PILLAR_E, PILLAR_I, PILLAR_P;
    }

    /** The type of tile */
    enum TileType {
        FLOOR, WALL, DOOR
    }

    /** The type of room */
    private RoomType myType;
    /** If there is a door on some face of the room */
    private boolean[] myDoors = new boolean[4];

    /**
     * Room constructor
     * 
     * @param type
     */
    public Room(RoomType theType) {
        myType = theType;
    }

    /**
     * Get the type of room
     * 
     * @return the type
     */
    public RoomType getType() {
        return myType;
    }

    /**
     * Set the type of room
     * 
     * @param type the type
     */
    public void setType(RoomType type) {
        myType = type;
    }

    /**
     * Add an item to the room
     * 
     * @param theDirection the direction of the item
     */
    public void addDoor(Direction theDirection) {
        myDoors[theDirection.ordinal()] = true;
    }

    public void addItem(Item item) {
        // TODO: Add item to room
    }

    @Override
    public String toString() {
        return "R";
    }
}
