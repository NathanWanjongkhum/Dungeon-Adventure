package team5.game.model;

import team5.game.model.Dungeon.Difficulty;
import team5.game.model.Item.ItemType;
import team5.game.model.PillarOfOO.PillarType;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class DungeonBuilder {

    /** The dungeon */
    private Room[][] myDungeon;
    /** The width of the dungeon */
    private int myWidth;
    /** The height of the dungeon */
    private int myHeight;
    /** The difficulty of the dungeon */
    private Difficulty myDifficulty;

    // Default constructor
    protected DungeonBuilder() {
    }

    private enum Quadrant {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    /**
     * Set the width of the dungeon
     * 
     * @param theWidth the width
     * @return dungeon builder
     */
    protected final DungeonBuilder setWidth(final int theWidth) {
        myWidth = theWidth;
        return this;
    }

    /**
     * Set the height of the dungeon
     * 
     * @param theHeight the height
     * 
     * @return dungeon builder
     */
    protected final DungeonBuilder setHeight(final int theHeight) {
        myHeight = theHeight;
        return this;
    }

    /**
     * Set the difficulty of the dungeon
     * 
     * @param theDifficulty the difficulty
     * 
     * @return dungeon builder
     */
    protected final DungeonBuilder setDifficulty(final Difficulty theDifficulty) {
        myDifficulty = theDifficulty;
        return this;
    }

    /**
     * Builds the dungeon with guaranteed traversability and item separation
     */
    protected final Room[][] build() {
        myDungeon = new Room[myWidth][myHeight];

        // Calculate center point
        int centerX = myWidth / 2;
        int centerY = myHeight / 2;
        SimpleEntry<Integer, Integer> startPos = new SimpleEntry<>(centerX, centerY);

        // Add start room
        addRoom(startPos);

        // Generate base maze structure for each quadrant
        for (Quadrant quadrant : Quadrant.values()) {
            generateQuadrantMaze(quadrant, startPos);
        }

        // Place items in each quadrant
        placeQuadrantItems();

        // Ensure connectivity by adding additional passages
        ensureConnectivity();

        return myDungeon;
    }

    /**
     * Generates maze structure for a specific quadrant
     */
    private void generateQuadrantMaze(Quadrant quadrant, SimpleEntry<Integer, Integer> startPos) {
        int centerX = startPos.getKey();
        int centerY = startPos.getValue();

        // Define quadrant boundaries
        int startX, endX, startY, endY;
        switch (quadrant) {
            case TOP_LEFT:
                startX = 0;
                endX = centerX;
                startY = centerY;
                endY = myHeight;
                break;
            case TOP_RIGHT:
                startX = centerX;
                endX = myWidth;
                startY = centerY;
                endY = myHeight;
                break;
            case BOTTOM_LEFT:
                startX = 0;
                endX = centerX;
                startY = 0;
                endY = centerY;
                break;
            case BOTTOM_RIGHT:
                startX = centerX;
                endX = myWidth;
                startY = 0;
                endY = centerY;
                break;
            default:
                throw new IllegalStateException("Invalid quadrant");
        }

        // Generate maze in quadrant using recursive backtracking
        carveQuadrantPassages(new SimpleEntry<>(centerX, centerY), startX, endX, startY, endY);
    }

    private void carveQuadrantPassages(SimpleEntry<Integer, Integer> currentPos,
            int startX, int endX, int startY, int endY) {
        // Shuffle the directions to reduce bias
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        // Get current coordinates
        int currentX = currentPos.getKey();
        int currentY = currentPos.getValue();

        // Carve passages in each direction
        for (Direction dir : directions) {
            int newX = dir.calculateNewX(currentX);
            int newY = dir.calculateNewY(currentY);

            // Check if the new position is within quadrant bounds and unvisited
            if (newX >= startX && newX < endX &&
                    newY >= startY && newY < endY &&
                    isValidLocation(newX, newY) &&
                    myDungeon[newX][newY] == null) {

                // Create new room
                Room newRoom = addRoom(currentPos, dir);

                if (newRoom != null) {
                    // Continue carving from the new position
                    carveQuadrantPassages(
                            new SimpleEntry<>(newX, newY),
                            startX, endX, startY, endY);
                }
            }
        }
    }

    /**
     * Places item rooms in each quadrant, ensuring some minimum distance from
     * center
     */
    private void placeQuadrantItems() {
        // Shuffle the pillars
        List<PillarType> items = Arrays.asList(
                PillarType.ABSTRACTION, PillarType.ENCAPSULATION, PillarType.INHERITANCE, PillarType.POLYMORPHISM);
        Collections.shuffle(items);

        int centerX = myWidth / 2;
        int centerY = myHeight / 2;

        // Place the pillars in each quadrant
        for (int i = 0; i < items.size(); i++) {
            Quadrant quadrant = Quadrant.values()[i % Quadrant.values().length];
            placePillar(quadrant, items.get(i), centerX, centerY);
        }
    }

    /**
     * Places a pillar in a specific quadrant
     * 
     * @param quadrant   The quadrant to place the pillar in
     * @param pillarType The type of pillar to place
     * @param centerX    The center X coordinate of the dungeon
     * @param centerY    The center Y coordinate of the dungeon
     */
    private void placePillar(Quadrant quadrant, PillarType pillarType, int centerX, int centerY) {
        // TODO:: Place pillar in quadrant
    }

    /**
     * Ensures connectivity between quadrants by adding additional passages
     */
    private void ensureConnectivity() {
        int centerX = myWidth / 2;
        int centerY = myHeight / 2;

        // Add cross-shaped connections from center
        Direction[] crossDirections = Direction.values().clone();
        for (Direction dir : crossDirections) {
            int x = dir.calculateNewX(centerX);
            int y = dir.calculateNewY(centerY);

            if (isValidLocation(x, y) && myDungeon[x][y] != null) {
                Room centerRoom = myDungeon[centerX][centerY];
                Room adjacentRoom = myDungeon[x][y];

                centerRoom.addDoor(dir);
                adjacentRoom.addDoor(dir.getOpposite());
            }
        }
    }

    /**
     * Check if the location is valid
     * 
     * @param theX the x coordinate
     * @param theY the y coordinate
     * 
     * @return true if the location is valid
     */
    protected final boolean isValidLocation(int theX, int theY) {
        return theX >= 0 && theX < myWidth && theY >= 0 && theY < myHeight;
    }

    /**
     * Add a room to the dungeon using coordinates
     * 
     * @param theRoomType    the type of room to add
     * @param theCoordinates the coordinates of the room
     * 
     * @return the new room
     */
    protected final Room addRoom(SimpleEntry<Integer, Integer> theCoordinates) {
        // Get the coordinates
        int theX = theCoordinates.getKey();
        int theY = theCoordinates.getValue();

        // Check if the location is valid
        if (!isValidLocation(theX, theY)) {
            System.out.println("Invalid Room Location at " + theX + ", " + theY);
            System.out.println("Width: " + myWidth + ", Height: " + myHeight);
            return null;
        }

        // Create the room
        Room room = new Room();
        myDungeon[theX][theY] = room;

        return room;
    }

    /**
     * Add a room to the dungeon using directions relative to the previous room
     * 
     * @param thePreviousPos the coordinate previous room
     * @param theDirection   the direction to add the room
     * 
     * @return the new room or null if invalid location
     */
    protected final Room addRoom(
            final SimpleEntry<Integer, Integer> thePreviousPos,
            final Direction theDirection) {
        // Deconstruct the coordinates of the previous room
        int previousX = thePreviousPos.getKey();
        int previousY = thePreviousPos.getValue();

        // Get the coordinates of the new room
        int newX = theDirection.calculateNewX(previousX);
        int newY = theDirection.calculateNewY(previousY);

        if (!isValidLocation(newX, newY) || myDungeon[newX][newY] != null) {
            return null;
        }

        // Create the room
        Room newRoom = new Room();
        myDungeon[newX][newY] = newRoom;

        Room previousRoom = getRoom(thePreviousPos);

        // Connect the rooms
        previousRoom.addDoor(theDirection);
        newRoom.addDoor(theDirection.getOpposite());

        return newRoom;
    }

    /**
     * Get the room at the given coordinates
     * 
     * @param theCoordinates the coordinates of the room
     * 
     * @return the room at the coordinates
     */
    public final Room getRoom(SimpleEntry<Integer, Integer> theCoordinates) {
        // Get the coordinates
        int theX = theCoordinates.getKey();
        int theY = theCoordinates.getValue();

        return myDungeon[theX][theY];
    }
}