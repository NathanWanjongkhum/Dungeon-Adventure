package team5.game.model;

import team5.game.model.Room.RoomType;

/**
 * Dungeon is a class that represents a dungeon.
 */
public class Dungeon {
    /** The dungeon */
    Room[][] myDungeon;
    /** The width of the dungeon */
    int myWidth;
    /** The height of the dungeon */
    int myHeight;

    /**
     * Dungeon constructor
     * 
     * @param width
     * @param height
     */
    public Dungeon(int width, int height) {
        myWidth = width;
        myHeight = height;
        myDungeon = new Room[width][height];
    }

    /**
     * Add a room to the dungeon
     * 
     * @param theRoomType the type of room to add
     * @param theX        the x coordinate of the room
     * @param theY        the y coordinate of the room
     * 
     * @return
     */
    public void addRoom(RoomType theRoomType, int theX, int theY) {
        if (theX < 0 || theX >= myWidth || theY < 0 || theY >= myHeight) {
            System.out.println("Invalid Room Location");
            return;
        }

        Room room = new Room(theRoomType);
        myDungeon[theX][theY] = room;
    }

    /**
     * Pick a random room from the dungeon
     * 
     * @return the room
     */
    public Room pickRandomRoom() {
        Room room = null;

        while (room == null) {
            int x = (int) (Math.random() * myWidth);
            int y = (int) (Math.random() * myHeight);
            room = myDungeon[x][y];
        }

        return room;
    }
}
