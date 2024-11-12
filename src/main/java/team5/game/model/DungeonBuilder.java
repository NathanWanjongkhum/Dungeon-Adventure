package team5.game.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * DungeonBuilder is a builder class for Dungeon. Using the builder pattern, it
 * uses the parameters to create a dungeon.
 */
final class DungeonBuilder {
    /** The dungeon */
    private Room[][] myDungeon;
    /** The width of the dungeon */
    private int myWidth;
    /** The height of the dungeon */
    private int myHeight;
    /** The difficulty of the dungeon */
    private Difficulty myDifficulty;

    /**
     * DungeonBuilder no args constructor does nothing
     */
    protected DungeonBuilder() {
    }

    /**
     * Get the height of the dungeon
     * 
     * @return the height
     */
    public final int getHeight() {
        if (myDungeon == null) {
            return 0;
        }

        return myDungeon.length;
    }

    /**
     * Get the width of the dungeon
     * 
     * @return the width
     */
    public final int getWidth() {
        if (myDungeon == null) {
            return 0;
        }

        return myDungeon[0].length;
    }

    /**
     * Get the difficulty of the dungeon
     * 
     * @return the difficulty
     */
    public final Difficulty getDifficulty() {
        return myDifficulty;
    }

    /**
     * Set the width of the dungeon
     * 
     * @param theWidth the width
     * @return the builder
     */
    protected final DungeonBuilder setWidth(final int theWidth) {
        myWidth = theWidth;
        myDungeon = new Room[myWidth][myHeight];
        return this;
    }

    /**
     * Set the height of the dungeon
     * 
     * @param theHeight the height
     * @return the builder
     */
    protected final DungeonBuilder setHeight(final int theHeight) {
        myHeight = theHeight;
        myDungeon = new Room[myWidth][myHeight];
        return this;
    }

    /**
     * Set the difficulty of the dungeon
     * 
     * @param theDifficulty the difficulty
     * @return the builder
     */
    protected final DungeonBuilder setDifficulty(final Difficulty theDifficulty) {
        myDifficulty = theDifficulty;
        myDungeon = new Room[myWidth][myHeight];
        return this;
    }

    /**
     * Build the dungeon
     * 
     * @return the dungeon
     */
    protected final Room[][] build() {
        // Validate parameters before building
        if (myWidth <= 0 || myHeight <= 0) {
            throw new IllegalStateException("Width and height must be positive values");
        }

        myDungeon = new Room[myWidth][myHeight];

        // Initialize all rooms first
        for (int x = 0; x < myWidth; x++) {
            for (int y = 0; y < myHeight; y++) {
                myDungeon[x][y] = new Room();
            }
        }

        // Start from center for better distribution
        SimpleEntry<Integer, Integer> startPos = new SimpleEntry<>(0, 0);

        generateMaze(startPos);

        return myDungeon;
    }

    /**
     * Generate the maze using a recursive backtracker
     * 
     * @param startPos the start position
     */
    private final void generateMaze(final SimpleEntry<Integer, Integer> startPos) {
        Stack<SimpleEntry<Integer, Integer>> stack = new Stack<>();
        boolean[][] visited = new boolean[myWidth][myHeight];

        stack.push(startPos);
        visited[startPos.getKey()][startPos.getValue()] = true;

        while (!stack.isEmpty()) {
            SimpleEntry<Integer, Integer> current = stack.peek();
            List<Direction> unvisitedNeighbors = getUnvisitedNeighbors(current, visited);

            if (unvisitedNeighbors.isEmpty()) {
                stack.pop();
            } else {
                Direction randomDir = unvisitedNeighbors.get(
                        (int) (Math.random() * unvisitedNeighbors.size()));

                SimpleEntry<Integer, Integer> next = new SimpleEntry<>(
                        randomDir.calculateNewX(current.getKey()),
                        randomDir.calculateNewY(current.getValue()));

                // Carve passage between current and next
                Room currentRoom = myDungeon[current.getKey()][current.getValue()];
                Room nextRoom = myDungeon[next.getKey()][next.getValue()];

                carveEdge(randomDir, nextRoom, currentRoom);

                visited[next.getKey()][next.getValue()] = true;
                stack.push(next);
            }
        }
    }

    /**
     * Get the unvisited neighbors of the given position
     * 
     * @param pos     the position
     * @param visited the visited array
     * @return the unvisited neighbors
     */
    private final List<Direction> getUnvisitedNeighbors(
            SimpleEntry<Integer, Integer> pos,
            boolean[][] visited) {
        List<Direction> unvisited = new ArrayList<>();

        int newX, newY;
        for (Direction dir : Direction.values()) {
            newX = dir.calculateNewX(pos.getKey());
            newY = dir.calculateNewY(pos.getValue());

            if (isValidLocation(newX, newY) && !visited[newX][newY]) {
                unvisited.add(dir);
            }
        }

        Collections.shuffle(unvisited);

        return unvisited;
    }

    /**
     * Creates a passage between the adjacent rooms
     * 
     * @param theDirection the direction of the passage from current to next
     * @param newRoom      the room next to the previous room
     * @param previousRoom the room that is adjacent to the new room
     */
    private final void carveEdge(final Direction theDirection,
            final Room newRoom,
            final Room previousRoom) {
        previousRoom.addDoor(theDirection);
        newRoom.addDoor(theDirection.getOpposite());
    }

    /**
     * Check if the location is valid. Checks if the coordinates are within the
     * bounds of the dungeon.
     * 
     * @param theX
     * @param theY
     * @return
     */
    public final boolean isValidLocation(final int theX, final int theY) {
        return theX >= 0 && theX < myWidth && theY >= 0 && theY < myHeight;
    }

    /**
     * Get the room at the given coordinates
     * 
     * @param theCoordinates the coordinates
     * @return the room at the coordinates
     */
    public final Room getRoom(final SimpleEntry<Integer, Integer> theCoordinates) {
        int theX = theCoordinates.getKey();
        int theY = theCoordinates.getValue();
        return myDungeon[theX][theY];
    }
}