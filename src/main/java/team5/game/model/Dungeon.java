package team5.game.model;

/**
 * Dungeon is a class that represents a dungeon.
 */
public class Dungeon {

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    /** The dungeon */
    private Room[][] myDungeon;
    /** The width of the dungeon */
    private int myWidth;
    /** The height of the dungeon */
    private int myHeight;
    /** The difficulty of the dungeon */
    private Difficulty myDifficulty;

    /**
     * Dungeon constructor
     * 
     * @param width  the width of the dungeon
     * @param height the height of the dungeon
     */
    public Dungeon(final int theWidth, final int theHeight, final Difficulty theDifficulty) {
        myDungeon = new Room[theWidth][theHeight];
        myWidth = theWidth;
        myHeight = theHeight;
        myDifficulty = theDifficulty;
    }

    /**
     * Uses a DungeonBuilder to initialize the dungeon.
     */
    public final void init() {
        myDungeon = new DungeonBuilder()
                .setDifficulty(myDifficulty)
                .setWidth(myWidth)
                .setHeight(myHeight)
                .build();
    }

    /**
     * Get the width of the dungeon
     * 
     * @return the width
     */
    public int getWidth() {
        return myWidth;
    }

    /**
     * Get the height of the dungeon
     * 
     * @return the height
     */
    public int getHeight() {
        return myHeight;
    }

    /**
     * Get the difficulty of the dungeon
     * 
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return myDifficulty;
    }

    /**
     * Get the dungeon
     * 
     * @return the dungeon
     */
    public Room[][] getDungeon() {
        return myDungeon;
    }

    /**
     * Pick a random room from the dungeon
     * 
     * @return the random room
     */
    public final Room getRandomRoom() {
        Room room = null;

        // Pick a random room until we find one that isn't empty
        while (room == null) {
            int x = (int) (Math.random() * myWidth);
            int y = (int) (Math.random() * myHeight);
            room = myDungeon[x][y];
        }

        return room;
    }

    /**
     * Get the start room of the dungeon
     * 
     * @return the start room
     */
    public final Room getStartRoom() {
        // Avoids an IndexOutOfBoundsException
        return (myDungeon == null) ? myDungeon[getWidth() / 2][getHeight() / 2] : null;
    }
}
