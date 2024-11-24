package team5.game.model;

import java.util.Random;

/**
 * Dungeon is a class that represents a dungeon.
 */
public class Dungeon {
    /** The dungeon */
    private Room[][] myDungeon;
    /** The width of the dungeon */
    private int myWidth;
    /** The height of the dungeon */
    private int myHeight;
    /** The difficulty of the dungeon */
    private Difficulty myDifficulty;
    /** A random number generator */
    private Random random = new Random();

    /**
     * Dungeon constructor. Initializes a new dungeon.
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
     * Dungeon constructor. Clones another dungeon.
     * 
     * @param theDungeon the dungeon to copy
     */
    public Dungeon(final Dungeon theDungeon) {
        myDungeon = new Room[theDungeon.getWidth()][theDungeon.getHeight()];
        myWidth = theDungeon.getWidth();
        myHeight = theDungeon.getHeight();
        myDifficulty = theDungeon.getDifficulty();
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

        // Places items in the dungeon
        placeItems();
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
     * Check if the location is valid
     * 
     * @param theX the x coordinate
     * @param theY the y coordinate
     * @return true if the location is valid
     */
    public boolean isValidLocation(final int theX, final int theY) {
        return myDungeon != null && theX >= 0 && theX < myWidth && theY >= 0 && theY < myHeight;
    }

    /**
     * Check if there's a valid connection from the current position to the new room
     * inthe given direction.
     * 
     * @param thePos       the position of the current room
     * @param theDirection the direction to check
     * 
     * @return true if the current room an new room are connected
     */
    public boolean isConnected(final int theX, final int theY, final Direction theDirection) {
        final int newX = theDirection.calculateNewX(theX);
        final int newY = theDirection.calculateNewY(theY);

        // Check bounds first
        if (!isValidLocation(newX, newY)) {
            return false;
        }

        final Room currentRoom = myDungeon[theX][theY];
        final Room newRoom = myDungeon[newX][newY];

        if (currentRoom == null || newRoom == null) {
            return false;
        }

        // Check if both rooms have doors connecting them
        final boolean currentRoomConnects = currentRoom.getDoors()[theDirection.ordinal()];
        final boolean newRoomConnects = newRoom.getDoors()[theDirection.getOpposite().ordinal()];

        return currentRoomConnects && newRoomConnects;
    }

    /**
     * Get the room at the given coordinates
     * 
     * @param theX the x coordinate
     * @param theY the y coordinate
     * @return the room at the coordinates
     */
    public Room getRoom(final int theX, final int theY) {
        return myDungeon[theX][theY];
    }

    /**
     * Pick a random room from the dungeon
     * 
     * @return the random room
     */
    public final Room getRandomRoom() {
        int x, y;
        do {
            x = (int) (Math.random() * myWidth);
            y = (int) (Math.random() * myHeight);
        } while (!isValidLocation(myWidth, myHeight));

        final Room room = myDungeon[x][y];

        return room;
    }

    /**
     * Get the start room of the dungeon
     * 
     * @return the start room
     */
    public final Room getStartRoom() {
        // Avoids an IndexOutOfBoundsException
        return (myDungeon != null) ? myDungeon[getWidth() / 2][getHeight() / 2] : null;
    }

    private void placeItems() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (random.nextInt(100) < 5) {
                    Room room = getRoom(i, j);
                    room.setItem(getRandomItem());
                }
            }
        }
    }

    private Item getRandomItem() {
        int itemType = random.nextInt(2);

        Item item = null;
        // TODO: Add more items
        switch (itemType) {
            case 0 -> item = new HealingPotion();
            case 1 -> item = new Bomb();
            // case 2 -> new VisionPotion();
            default -> throw new IllegalStateException("Unexpected itemType: " + itemType);
        }

        return item;
    }

    /**
     * The string representation of the dungeon. Which is the accumulation of
     * the string representation of each room. IF the room is null, the string
     * "X" is used. Each row is separated by a newline.
     * 
     * @return the symbol
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                final Room room = myDungeon[i][j];
                if (room != null) {
                    sb.append(room.toString());
                } else {
                    sb.append("X");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
