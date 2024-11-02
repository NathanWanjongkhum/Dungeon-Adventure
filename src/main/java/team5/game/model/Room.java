package team5.game.model;

/**
 * Room is a class that represents a room in the dungeon.
 */
public class Room {
    /** The type of room */
    enum RoomType {
        EMPTY, MONSTER, TREASURE, ENTERANCE, EXIT
    }

    /** The type of tile */
    enum TileType {
        FLOOR, WALL, DOOR
    }

    /** The type of room */
    private RoomType myType;

    /**
     * Room constructor
     * 
     * @param type
     */
    public Room(RoomType type) {
        myType = type;
    }

    /**
     * Get the type of room
     * 
     * @return the type
     */
    public RoomType getType() {
        return myType;
    }
}
